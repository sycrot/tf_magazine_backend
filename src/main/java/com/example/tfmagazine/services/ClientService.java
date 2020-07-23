package com.example.tfmagazine.services;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.tfmagazine.domain.Address;
import com.example.tfmagazine.domain.City;
import com.example.tfmagazine.domain.Client;
import com.example.tfmagazine.domain.enums.Profile;
import com.example.tfmagazine.domain.enums.TypeCustomer;
import com.example.tfmagazine.dto.ClientDTO;
import com.example.tfmagazine.dto.ClientNewDTO;
import com.example.tfmagazine.repositories.AddressRepository;
import com.example.tfmagazine.repositories.ClientRepository;
import com.example.tfmagazine.security.UserSS;
import com.example.tfmagazine.services.exception.AuthorizationException;
import com.example.tfmagazine.services.exception.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private BCryptPasswordEncoder bpe;
	
	//private S3Service s3service;
	
	//private ImageService imageService
	
	//@Value("${img.prefix.client.profile}")
	//private String prefix;
	
	//@Value("${img.profile.size}")
	//private Integer size;
	
	public Client find(Integer id) {
		
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Client> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: "+Client.class.getName()
				));
		
	}
	
	@Transactional
	public Client insert(Client obj) {
		
		obj.setId(null);
		obj = repo.save(obj);
		addressRepo.saveAll(obj.getAdresses());
		return obj;
	}
	
	public Client update(Client obj) {
		
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	public void delete(Integer id) {
		
		find(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir um cliente que tem outros dados");
		}
		
	}
	
	public List<Client> findAll() {
		
		return repo.findAll();
		
	}
	
	public Client findByEmail(String email) {
		
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername()) ) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Client obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: "+ user.getId());
		}
		
		return obj;
		
	}
	
	public Page<Client> findPage(
			Integer page,
			Integer linesPerPage,
			String orderBy,
			String direction
			) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);		
	}
	
	public Client fromDTO(ClientDTO obj) {
		return new Client(obj.getId(), obj.getName(), obj.getEmail(), null, null, null);
	}
	
	public Client fromDTO(ClientNewDTO obj) {
		
		Client cli = new Client(null, obj.getName(), obj.getEmail(), obj.getCpfOuCnpj(), TypeCustomer.toEnum(obj.getType()), bpe.encode(obj.getPassword()));
		City city = new City(obj.getCityId(), null,  null);
		Address end = new Address(null, obj.getPublicPlace(), obj.getNumber(), obj.getComplement(), obj.getNeighborhood(), obj.getCep(), cli, city);
		cli.getAdresses().add(end);
		cli.getPhones().add(obj.getTelefone1());
		if (obj.getTelefone2() != null) {
			cli.getPhones().add(obj.getTelefone2());
		}
		
		return cli;
		
	}
	
	public void updateData(Client newObj, Client obj) {
		
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
	
	/*public URI uploadProfilePicture(MultipartFile multipartFile) {
		
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = imageService.getJpjImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return null
		
	} */
	
}
