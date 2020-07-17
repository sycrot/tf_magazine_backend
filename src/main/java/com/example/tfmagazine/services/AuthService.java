package com.example.tfmagazine.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tfmagazine.domain.Client;
import com.example.tfmagazine.repositories.ClientRepository;
import com.example.tfmagazine.services.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private BCryptPasswordEncoder bpe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Client client = clientRepo.findByEmail(email);
		
		if (client == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		client.setSenha(bpe.encode(newPass));
		
		clientRepo.save(client);
		emailService.sendNewPasswordEmail(client, newPass);
		
	}
	
	private String newPassword() {
		
		char[] vet = new char[10];
		
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
		
	}
	
	private char randomChar() {
		
		int opt = rand.nextInt(3);
		
		if (opt == 0) {
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) {
			return (char) (rand.nextInt(26) + 65);
		} else  {
			return (char) (rand.nextInt(26) + 97);
		}
		
	}
	
}
