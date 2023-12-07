package com.hook.form.backend.org.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hook.form.backend.org.repo.TokenRepo;
import com.hook.form.backend.org.util.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
// @Order(1)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtService jwtService;

	@Autowired
    TokenRepo tokenRepo;

	private UserDetails userDetails;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken;
		final String expiredToken = "TOKEN_EXPIRED";
		// Boolean isExpiredtoken = false;
		if (authHeader != null) {
			jwtToken = extractJwtFromRequest(request);
			try {
			// 	// check if the token is expired and also make sure it's not refresh call
			// 	try {
			// 		isExpiredtoken = jwtService.isTokenExpired(jwtToken);
			// 	} catch (Exception e) {
			// 		System.out.print(e);
			// 		isExpiredtoken = false;

			// 	}

				username = jwtService.getUsernameFromToken(jwtToken);
                // Check if the TOKen is Valid in DB
			    // Boolean isValidToken = tokenRepo.findByToken(jwtToken)
				// 	.map(t -> !t.getIs_expired() && !t.getIs_revoked())
			    //       .orElse(false);
				// Once we get the token validate it.
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
				// && isValidToken) {

					userDetails = this.userDetailsService.loadUserByUsername(username);

					// Check if the TOKen is Valid in DB
					Boolean isValidToken = tokenRepo.findByToken(jwtToken)
							.map(t -> !t.getIs_expired() && !t.getIs_revoked())
					          .orElse(false);

					// if token is valid configure Spring Security to manually set
					// authentication
					//this line might throw ExpiredJwtException

					if (jwtService.isTokenValid(jwtToken, userDetails)
					&& isValidToken
					) {

						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						// After setting the Authentication in the context, we specify
						// that the current user is authenticated. So it passes the
						// Spring Security Configurations successfully.
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				}

			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException ex) {
				String isRefreshToken = request.getHeader("isRefreshToken");
				String requestURL = request.getRequestURL().toString();
				// allow for Refresh Token creation if following conditions are true.
				if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken")) {
					allowForRefreshToken(ex, request);
				} else {
					// request.setAttribute("exception", ex);

					throw new InsufficientAuthenticationException(expiredToken);

				}

			} catch (NullPointerException e) {
				logger.warn("JWT Token does not begin with Bearer String");
			} catch (BadCredentialsException ex) {
				// request.setAttribute("exception", ex);
				throw ex;
			} catch (MalformedJwtException ex) {
				throw ex;
			}

			catch (Exception ex) {
				System.out.println(ex);
			}
		}
		chain.doFilter(request, response);

	}
	//not used
	private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {

		// create a UsernamePasswordAuthenticationToken with null values.
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				null, null, null);
		// After setting the Authentication in the context, we specify
		// that the current user is authenticated. So it passes the
		// Spring Security Configurations successfully.
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		// Set the claims so that in controller we will be using it to create
		// new JWT
		request.setAttribute("claims", ex.getClaims());

	}
	private String extractJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
