package com.example.tfmagazine.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.tfmagazine.domain.Client;
import com.example.tfmagazine.domain.enums.TypeCustomer;
import com.example.tfmagazine.dto.ClientNewDTO;
import com.example.tfmagazine.repositories.ClientRepository;
import com.example.tfmagazine.resources.exception.FieldMessage;
import com.example.tfmagazine.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

	@Autowired
	private ClientRepository repo;
	
	@Override
	public void initialize(ClientInsert ann) {
		
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getType().equals(TypeCustomer.PESSOAFISICA.getCod())
				&& !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
			
		}
		
		if (objDto.getType().equals(TypeCustomer.PESSOAJURIDICA.getCod())
				&& !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
			
		}
		
		Client aux = repo.findByEmail(objDto.getEmail()); 
		if (aux != null) {
			list.add(new FieldMessage("Email", "Email já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
		
	}
	
}
