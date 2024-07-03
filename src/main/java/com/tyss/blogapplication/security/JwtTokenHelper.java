package com.tyss.blogapplication.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private String secret = "jwtTokenKey";

	// retrive user from jwt token

	public String getuserNameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);

	}

	// get expiration date from jwt token

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);

	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for fetching any information from token we will need the secret key

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);

		return expiration.before(new java.util.Date());

	}

	// generate token for user

	public String genrateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenrateToken(claims, userDetails.getUsername());
	}

	private String doGenrateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new java.util.Date())
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 100))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// validate token

	public Boolean validateToken(String token, UserDetails userDetails) {

		final String username = getuserNameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}

}
