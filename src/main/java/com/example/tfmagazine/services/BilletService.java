package com.example.tfmagazine.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.tfmagazine.domain.PaymentWithBillet;

@Service
public class BilletService {
	
	public void preencherPagamentoComBoleto(PaymentWithBillet pagto, Date instant) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(instant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
		
	}

}
