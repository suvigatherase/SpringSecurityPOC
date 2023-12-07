package com.hook.form.backend.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hook.form.backend.org.model.Token;
import com.hook.form.backend.org.model.Tokentype;
import com.hook.form.backend.org.model.User;
import com.hook.form.backend.org.repo.TokenRepo;

@Service
public class TokenService {
    @Autowired
	//@Qualifier(CustomUserInfoRepo)
    TokenRepo tokenRepo;
   public Token saveToken(String  token , User base_user)
   {
       
       Token savedToken = tokenRepo.save(new Token(token, Tokentype.BEARER, false, false, base_user));
       return savedToken;
   }

    public void revokeAllUserTokens(User base_user)
    {
        List<Token> validUserTokens = tokenRepo.findallValidtokenByUser(base_user.getId());
        if(validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setIs_expired(true);
            t.setIs_revoked(true);
        });
        tokenRepo.saveAll(validUserTokens);

    }
    
}
