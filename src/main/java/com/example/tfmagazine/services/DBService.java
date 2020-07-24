package com.example.tfmagazine.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tfmagazine.domain.Address;
import com.example.tfmagazine.domain.Category;
import com.example.tfmagazine.domain.City;
import com.example.tfmagazine.domain.Client;
import com.example.tfmagazine.domain.ItemRequest;
import com.example.tfmagazine.domain.Payment;
import com.example.tfmagazine.domain.PaymentWithBillet;
import com.example.tfmagazine.domain.PaymentWithCard;
import com.example.tfmagazine.domain.Product;
import com.example.tfmagazine.domain.Request;
import com.example.tfmagazine.domain.State;
import com.example.tfmagazine.domain.enums.Profile;
import com.example.tfmagazine.domain.enums.StatePayment;
import com.example.tfmagazine.domain.enums.TypeCustomer;
import com.example.tfmagazine.repositories.AddressRepository;
import com.example.tfmagazine.repositories.CategoryRepository;
import com.example.tfmagazine.repositories.CityRepository;
import com.example.tfmagazine.repositories.ClientRepository;
import com.example.tfmagazine.repositories.ItemRequestRepository;
import com.example.tfmagazine.repositories.PaymentRepository;
import com.example.tfmagazine.repositories.ProductRepository;
import com.example.tfmagazine.repositories.RequestRepository;
import com.example.tfmagazine.repositories.StateRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private ItemRequestRepository itemRequestRepository;
	
	@Autowired
	private BCryptPasswordEncoder bpe;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Category cat1 = new Category(null, "Eletrodomésticos");
		Category cat2 = new Category(null, "Informática");
		Category cat3 = new Category(null, "Esporte e lazer");
		Category cat4 = new Category(null, "Ferramentas");
		
		Category cat5 = new Category(null, "Moda");
		Category cat6 = new Category(null, "Cama, mesa e banho");
		Category cat7 = new Category(null, "Instrumentos musicais");
		Category cat8 = new Category(null, "Livros");
		
		Category cat9 = new Category(null, "Papelaria");
		Category cat10 = new Category(null, "Casa e Construção");
		Category cat11 = new Category(null, "Artigos para festas");
		Category cat12 = new Category(null, "Games");
		
		
		Product p1 = new Product(null, "Notebook", 2100.00);
		Product p2 = new Product(null, "Impressora", 1400.00);
		Product p3 = new Product(null, "Mouse", 40.00);
		Product p4 = new Product(null, "Bola futebol", 120.00);
		Product p5 = new Product(null, "Chave philips", 60.00);
		Product p6 = new Product(null, "Bola basquete", 140.00);
		Product p7 = new Product(null, "Kit ferraments", 400.00);
		
		Product p71 = new Product(null, "Kit Bota Coturno Causal", 64.00);
		Product p8 = new Product(null, "Colete Cinta Modeladora", 34.90);
		Product p9 = new Product(null, "Óculos de Grau Feminino", 249.04);
		Product p10 = new Product(null, "Kit Camiseta Básica c/5 Peças Masculinas", 89.99);
		
		Product p11= new Product(null, "Cobertor Casal Iury", 79.90);
		Product p12 = new Product(null, "Kit 2 Travesseiros fibra Siliconada", 22.40);
		Product p13 = new Product(null, "Jogo de Toalha Santista Royar Nut", 59.90);
		
		Product p14 = new Product(null, "Microfone de Lapela Sony Ecm-Cs3", 239.90);
		Product p15 = new Product(null, "Ukulele Tenor UK-30", 123.90);
		Product p16 = new Product(null, "Violão 5107 Cordas Nylon", 324.90);
		Product p17 = new Product(null, "Microfone Profissional Com Fio 3m High SM-58", 59.90);
		
		Product p18 = new Product(null, "Livro - As crônicas de Nárnia - Volume único", 69.90);
		Product p19 = new Product(null, "Livro - Quem mexeu no meu queijo", 9.90);
		
		Product p20 = new Product(null, "Lapis de Escrever Preto Escolar Graduado Caixa H Artools", 6.90);
		Product p21 = new Product(null, "Papel A4 Copimax 75g/m2 500 Folhas", 16.90);
		Product p22 = new Product(null, "Placas de Revestimentos 3D - Cairo Autoadesiva", 17.90);
		
		Product p23 = new Product(null, "Kit de Pilhas AA Pequena Alcalina 32 Unidades", 89.90);
		Product p24 = new Product(null, "Cimento Todas As Obras 50Kg", 20.90);
		Product p25 = new Product(null, "Torneira com Filtro Para Cozinha Acqubios", 49.90);
		Product p26 = new Product(null, "Lavadora de Alta Pressão K1 Karcher Black 1600lbs", 342.90);
		Product p27 = new Product(null, "Pia de Cozinha Inox 120x52cm Ghel Plus", 151.90);
		
		Product p28 = new Product(null, "Kit 2 Pijamas Monstros SA", 284.90);
		Product p29 = new Product(null, "Fantasia Homem Aranha", 311.90);
		
		Product p30 = new Product(null, "Playstation 4 1TB com 3 Jogos", 2699.90);
		Product p31 = new Product(null, "Fifa 20 PS4", 129.90);
		Product p32 = new Product(null, "Controle PS4 sem Fio Dualshock 4", 349.90);
		
		Product p33 = new Product(null, "Notebook Dell i15-3583 Intel Core i5 8GB", 3894.90);
		Product p34 = new Product(null, "Impressora Multifuncional Epson EcoTank", 1044.90);
		Product p35 = new Product(null, "HD Externo 1TB Toshiba Canvio", 399.90);
		
		Product p36 = new Product(null, "Bicicleta Caloi A18 Schwinn", 1367.90);
		Product p37 = new Product(null, "Tênis Air Jordan", 414.90);
		
		Product p38 = new Product(null, "Micro-ondas Electrolux", 539.90);
		Product p39 = new Product(null, "Lavadora de Roupas Brastemp", 1679.90);
		Product p40 = new Product(null, "Geladeira/Refrigerador Brastemp", 2384.90);	
		
		cat1.getProducts().addAll(Arrays.asList(p2, p3, p38, p39, p40));
		cat2.getProducts().addAll(Arrays.asList(p1, p2, p3, p33, p34, p35));
		cat3.getProducts().addAll(Arrays.asList(p4, p6, p36, p37));
		cat4.getProducts().addAll(Arrays.asList(p5, p7));
		
		cat5.getProducts().addAll(Arrays.asList(p71, p8, p9, p10));
		cat6.getProducts().addAll(Arrays.asList(p11, p12, p13));
		cat7.getProducts().addAll(Arrays.asList(p14, p15, p16, p17));
		cat8.getProducts().addAll(Arrays.asList(p18, p19));
		
		cat9.getProducts().addAll(Arrays.asList(p20, p21, p22));
		cat10.getProducts().addAll(Arrays.asList(p23, p24, p25, p26, p27));
		cat11.getProducts().addAll(Arrays.asList(p28, p29));
		cat12.getProducts().addAll(Arrays.asList(p30, p31, p32));
		
		
		p1.getCategories().addAll(Arrays.asList(cat1, cat2)); 	p2.getCategories().addAll(Arrays.asList(cat1, cat2)); 	p3.getCategories().addAll(Arrays.asList(cat1, cat2));
		p4.getCategories().addAll(Arrays.asList(cat3)); 	p5.getCategories().addAll(Arrays.asList(cat4)); 	p6.getCategories().addAll(Arrays.asList(cat3)); 
		p7.getCategories().addAll(Arrays.asList(cat4)); 	p8.getCategories().addAll(Arrays.asList(cat5)); 	p9.getCategories().addAll(Arrays.asList(cat5));
		p10.getCategories().addAll(Arrays.asList(cat5)); 	p11.getCategories().addAll(Arrays.asList(cat6)); 	p12.getCategories().addAll(Arrays.asList(cat6));
		p13.getCategories().addAll(Arrays.asList(cat6)); 	p14.getCategories().addAll(Arrays.asList(cat7, cat2)); 	p15.getCategories().addAll(Arrays.asList(cat7));
		p16.getCategories().addAll(Arrays.asList(cat7)); 	p17.getCategories().addAll(Arrays.asList(cat7, cat2)); 	p18.getCategories().addAll(Arrays.asList(cat8)); 
		p19.getCategories().addAll(Arrays.asList(cat8)); 	
		
		p20.getCategories().addAll(Arrays.asList(cat9)); 	p21.getCategories().addAll(Arrays.asList(cat9));
		p22.getCategories().addAll(Arrays.asList(cat9)); 	
		
		p23.getCategories().addAll(Arrays.asList(cat10, cat2)); 	p24.getCategories().addAll(Arrays.asList(cat10));
		p25.getCategories().addAll(Arrays.asList(cat10)); 	p26.getCategories().addAll(Arrays.asList(cat10, cat4));	 p27.getCategories().addAll(Arrays.asList(cat10));
		
		p28.getCategories().addAll(Arrays.asList(cat11)); 	p29.getCategories().addAll(Arrays.asList(cat11)); 	
		
		p30.getCategories().addAll(Arrays.asList(cat12, cat2)); p31.getCategories().addAll(Arrays.asList(cat12)); 	p32.getCategories().addAll(Arrays.asList(cat12)); 	
		
		p33.getCategories().addAll(Arrays.asList(cat2));	p34.getCategories().addAll(Arrays.asList(cat2)); 	p35.getCategories().addAll(Arrays.asList(cat2)); 	
		
		p36.getCategories().addAll(Arrays.asList(cat3));	p37.getCategories().addAll(Arrays.asList(cat3)); 	
		
		p38.getCategories().addAll(Arrays.asList(cat1)); 	p39.getCategories().addAll(Arrays.asList(cat1));
		p40.getCategories().addAll(Arrays.asList(cat1));	p71.getCategories().addAll(Arrays.asList(cat5));
		
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12));
		productRepository.saveAll(Arrays.asList(
				p1, p2, p3, p4, p5, p6, p7, p71, p8, p9, p10, 
				p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, 
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, 
				p31, p32, p33, p34, p35, p36, p37, p38, p39, p40));
		
		State est1 = new State(null, "Maranhão");
		State est2 = new State(null, "Rio de Janeiro");
		
		City c1 = new City(null, "Codó", est1);
		City c2 = new City(null, "Rio de Janeiro", est2);
		City c3 = new City(null, "Santos", est2);
		
		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));
		
		stateRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(
				null, 
				"Josefino", 
				"purrmageddon@gmail.com", 
				"32324100304",
				TypeCustomer.PESSOAFISICA,
				bpe.encode("123"));
		cli1.getPhones().addAll(Arrays.asList("987337788", "998828384"));
		
		Client cli2 = new Client(
				null,
				"Jack",
				"thiagofarias99@hotmail.com",
				"98481603708",
				TypeCustomer.PESSOAFISICA,
				bpe.encode("123")
				);
		cli2.addProfile(Profile.ADMIN);
		cli2.getPhones().addAll(Arrays.asList("976543243", "996543212"));
		
		Address a1 = new Address(
				null,
				"Rua Igui Ligs",
				"324",
				"Próximo ao Caixa",
				"São Paulo",
				"64320000",
				cli1, 
				c1
				);
		
		Address a2 = new Address(
				null, 
				"Avenida Magrão",
				"734",
				null,
				"São Caetano",
				"54678000",
				cli2, 
				c2
				);
		
		cli1.getAdresses().addAll(Arrays.asList(a1));
		cli2.getAdresses().addAll(Arrays.asList(a2));
		
		clientRepository.saveAll(Arrays.asList(cli1, cli2));
		addressRepository.saveAll(Arrays.asList(a1, a2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Request req1 = new Request(
				null,
				sdf.parse("24/07/2020 16:09"),
				cli1, 
				a1
				);
		
		Request req2 = new Request(
				null,
				sdf.parse("24/07/2020 16:11"),
				cli1,
				a1
				);
		
		Payment pay1 = new PaymentWithCard(
				null,
				StatePayment.QUITADO,
				req1,
				4
				);
		req1.setPayment(pay1);
		
		Payment pay2 = new PaymentWithBillet(
				null, 
				StatePayment.PENDENTE, 
				req2, 
				sdf.parse("28/07/2020 00:00"), 
				null);
		req2.setPayment(pay2);
		
		cli1.getRequests().addAll(Arrays.asList(req1, req2));
		
		requestRepository.saveAll(Arrays.asList(req1, req2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		
		ItemRequest ir1 = new ItemRequest(req1, p1, 0.00, 1, 2100.0);
		
		req1.getItems().addAll(Arrays.asList(ir1));
		
		p1.getItems().addAll(Arrays.asList(ir1));
		
		itemRequestRepository.saveAll(Arrays.asList(ir1));
		
		
	}

}
