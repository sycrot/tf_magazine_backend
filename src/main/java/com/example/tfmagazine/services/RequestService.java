package com.example.tfmagazine.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tfmagazine.domain.Client;
import com.example.tfmagazine.domain.ItemRequest;
import com.example.tfmagazine.domain.PaymentWithBillet;
import com.example.tfmagazine.domain.Request;
import com.example.tfmagazine.domain.enums.StatePayment;
import com.example.tfmagazine.repositories.ItemRequestRepository;
import com.example.tfmagazine.repositories.PaymentRepository;
import com.example.tfmagazine.repositories.RequestRepository;
import com.example.tfmagazine.security.UserSS;
import com.example.tfmagazine.services.exception.AuthorizationException;
import com.example.tfmagazine.services.exception.ObjectNotFoundException;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository repo;
	
	@Autowired
	private BilletService billetService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductServices productService;
	
	@Autowired
	private ItemRequestRepository ipRepo;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private EmailService emailService;
	
	public Request find(Integer id) {
		
		Optional<Request> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objecto não encontrado! Id: "+id+", Tipo: "+Request.class.getName()
				));
		
	}
	
	@Transactional
	public Request insert(Request obj) {
		
		obj.setId(null);
		obj.setInstant(new Date());
		obj.setClient(clientService.find(obj.getClient().getId()));
		obj.getPayment().setState(StatePayment.PENDENTE);
		obj.getPayment().setRequest(obj);
		if (obj.getPayment() instanceof PaymentWithBillet) {
			
			PaymentWithBillet pagto = (PaymentWithBillet) obj.getPayment();
			billetService.preencherPagamentoComBoleto(pagto, obj.getInstant());
			
		}
		
		obj = repo.save(obj);
		
		paymentRepository.save(obj.getPayment());
		
		for (ItemRequest ip : obj.getItems()) {
			
			ip.setDiscount(0.0);
			ip.setProduct(productService.find(ip.getProduct().getId()));
			ip.setPrice(ip.getProduct().getPrice());
			ip.setRequest(obj);
			
		}
		
		ipRepo.saveAll(obj.getItems());
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
		
	}
	
	public Page<Request> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		
		UserSS user = UserService.authenticated();
		
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Client client = clientService.find(user.getId());
		return repo.findByClient(client, pageRequest);
		
	}

}
