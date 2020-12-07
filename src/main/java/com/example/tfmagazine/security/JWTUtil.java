package com.example.tfmagazine.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.tfmagazine.domain.Client;
import com.example.tfmagazine.repositories.ClientRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_ROLE = "role";
	static final String CLAIM_KEY_AUDIENCE = "audience";
	static final String CLAIM_KEY_CREATED = "created";

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Autowired
	private ClientRepository clientRepository;
	
	// gets the username (email) contained in the JWT token
	public String getUsernameFromToken(String token) {
		
		String username;
		
		try {
			Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch(Exception e) {
			username = null;
		}
		
		return username;
		
	}
	
	// Returns the expiration date
	public Date getExpirationDateFromToken(String token) {
		
		Date expiration;
		
		try {
			Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch(Exception e) {
			expiration = null;
			
		}
		
		return expiration;
		
	}
	
	public String refreshToken(String token) {
		
		String refreshedToken;
		
		try {
			Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = getToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		
		return refreshedToken;
		
	}
	
	public boolean tokenValid(String token) {
		return !tokenExpired(token);
	}
	
	public String getToken(UserDetails userDetails) {
		
		Client client = clientRepository.findByEmail(userDetails.getUsername());
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
		claims.put(CLAIM_KEY_CREATED, new Date());
		
		if (client != null) {
			claims.put("id", client.getId());
		}
		
		return getToken(claims);
		
	}
	
	
	private Date getDateExpiration() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}
	
	private boolean tokenExpired(String token) {
		
		Date dateExpiration = this.getExpirationDateFromToken(token);
		if (dateExpiration == null) {
			return false;
		}
		
		return dateExpiration.before(new Date());
		
	}
	
	private String getToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(getDateExpiration()).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	
	private Claims getClaimsFromToken(String token) {
		
		Claims claims;
		
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch(Exception e) {
			claims = null;
		}
		
		return claims;
		
	}
	
	/* public String generateToken(String username) {
		
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
		
	}
	
	public boolean tokenValido(String token) {
		
		Claims claims = getClaims(token);
		
		if (claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date  now = new Date(System.currentTimeMillis());
			
			if (username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		} 
		
		return false;
		
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public String getUsername(String token) {
			
			Claims claims = getClaims(token);
			
			if (claims != null) {
				return claims.getSubject();
			}
			
			return null;
	} */
	
}
