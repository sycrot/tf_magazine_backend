package com.example.tfmagazine.services;

import org.springframework.mail.SimpleMailMessage;

import com.example.tfmagazine.domain.Client;
import com.example.tfmagazine.domain.Request;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Request obj);
	void sendEmail(SimpleMailMessage msg);
	void sendNewPasswordEmail(Client client, String newPass);

}
