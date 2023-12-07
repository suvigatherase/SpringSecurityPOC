package com.hook.form.backend.org.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hook.form.backend.org.model.User;

public class ApplicationAuditAware implements AuditorAware<String>{


    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null
                || !auth.isAuthenticated()
                || auth instanceof AnonymousAuthenticationToken)
            return Optional.empty();
        User user = (User) auth.getPrincipal();
        return Optional.ofNullable(user.getUsername());
   
    }
    
}
