package  com.hook.form.backend.org.util;

import java.io.Serializable;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.codec.Decoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtService implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	public static final String TAMPERED_TOKEN = "Invalid Token or Tampered Token";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.secret.jwt.expiration}")
	private Long jwtExpiration;
	@Value("${jwt.secret.jwt.refresh-token.expiration}")
	private Long jwtRefreshTokenExpiration;

	//get username/ get subject 
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	//retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
    //to get the a claim
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		try{
       final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	   }
	 catch ( MalformedJwtException | UnsupportedJwtException | IllegalArgumentException |BadCredentialsException  ex) {
			throw new BadCredentialsException(ex.getMessage(),ex);
		} 
		
	}
    //get all claims
	private Claims getAllClaimsFromToken(String token) {
		try {
			// Jwt token has not been tampered with
		 Claims claims =
					Jwts
		             .parserBuilder()
							.setSigningKey(getSigningKey())
							 .build().
							parseClaimsJws(token).getBody();
		 return claims;
		} catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException(TAMPERED_TOKEN, ex);
		} 
		// catch (ExpiredJwtException ex) {
		// 	throw new ex(header, claims, "Token has Expired", ex);
		// 	// throw new ExpiredJwtException( "Token has Expired", ex);
		// }

	}
   //Get the signingKey
	private Key getSigningKey()
   {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		return Keys.hmacShaKeyFor(keyBytes);
   }
	//check if the token has expired
	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

		//check if the token has expired
	public Boolean isTokenExpired2(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	//generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
		if (roles.contains(new SimpleGrantedAuthority("1"))) {
			claims.put("isDefault", true);
		}
		if (roles.contains(new SimpleGrantedAuthority("2"))) {
			claims.put("isUser", true);
		}
		if (roles.contains(new SimpleGrantedAuthority("3"))) {
			claims.put("isOrg", true);
		}
		if (roles.contains(new SimpleGrantedAuthority("4"))) {
			claims.put("isAdmin", true);
		}
		return doGenerateToken(claims, userDetails.getUsername());

	}


	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret key.
	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	//   compaction of the JWT to a URL-safe string 
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
				.signWith(getSigningKey(),SignatureAlgorithm.HS256).compact();
	}
	public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtRefreshTokenExpiration))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	//validate token
	public Boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
 //get roles
		public List<SimpleGrantedAuthority> getRolesFromToken(String authToken) {
		List<SimpleGrantedAuthority> roles = null;
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken).getBody();
		Boolean isAdmin = claims.get("isAdmin", Boolean.class);
		Boolean isUser = claims.get("isUser", Boolean.class);
		if (isAdmin != null && isAdmin == true) {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		if (isUser != null && isUser == true) {
			roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return roles;
	}

}