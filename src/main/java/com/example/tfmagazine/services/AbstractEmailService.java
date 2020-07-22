package com.example.tfmagazine.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.example.tfmagazine.domain.Client;
import com.example.tfmagazine.domain.Request;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Request obj) {

		SimpleMailMessage sm = prepareSimpleMailMessageFromRequest(obj);
		sendEmail(sm);
		
		
	}
	
	protected SimpleMailMessage prepareSimpleMailMessageFromRequest(Request obj) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getClient().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! código: "+obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		
		return sm;
		
	}

	@Override
	public void sendNewPasswordEmail(Client client, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(client, newPass);
		sendEmail(sm);
		
	}
	
	protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPass) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(client.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: "+ newPass);
		
		return sm;
		
	}

}
