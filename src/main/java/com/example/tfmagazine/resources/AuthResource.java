package com.example.tfmagazine.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tfmagazine.dto.EmailDTO;
import com.example.tfmagazine.security.JWTUtil;
import com.example.tfmagazine.security.UserSS;
import com.example.tfmagazine.services.AuthService;
import com.example.tfmagazine.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
		
	}
	
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDTO) {
		
		service.sendNewPassword(objDTO.getEmail());
		
		return ResponseEntity.noContent().build();
		
	}

}