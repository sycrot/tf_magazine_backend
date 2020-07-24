package com.example.tfmagazine.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.example.tfmagazine.domain.Client;
import com.example.tfmagazine.dto.ClientNewDTO;
import com.example.tfmagazine.repositories.ClientRepository;
import com.example.tfmagazine.resources.exception.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientNewDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientRepository repo;
	
	@Override
	public void initialize(ClientUpdate ann) {
		
	}

	@Override
	public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Client aux = repo.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
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
