package com.hook.form.backend.org.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hook.form.backend.org.exception.CustomExceptions.TokenException;
import com.hook.form.backend.org.model.Token;
import com.hook.form.backend.org.repo.TokenRepo;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

     @Autowired
	//@Qualifier(CustomUserInfoRepo)
    TokenRepo tokenRepo;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        // TODO Auto-generated method stub
        String jwtToken = extractJwtFromRequest(request);
        Token token = tokenRepo.findByToken(jwtToken).get();
        if (null != token)
        
        {
            if (token.getIs_expired() && token.getIs_revoked())
    
            token.setIs_expired(true);
            token.setIs_revoked(true);
            tokenRepo.save(token);
        }
    
    }
    
    	private String extractJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
                            
}
