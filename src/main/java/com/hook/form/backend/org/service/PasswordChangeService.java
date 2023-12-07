package com.hook.form.backend.org.service;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hook.form.backend.org.exception.CustomExceptions.PasswordChangeException;
import com.hook.form.backend.org.model.PasswordChangeRequest;
import com.hook.form.backend.org.model.User;
import com.hook.form.backend.org.repo.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordChangeService 
{
    private final BCryptPasswordEncoder encoder;
    private final TokenService tokenService; 
    private final UserRepository userRepository;
    public void changePassword(PasswordChangeRequest request, Principal connectedUser) throws PasswordChangeException {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!encoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new PasswordChangeException("the password entered doesn't match with the user records");

        }
        if (!(request.getNewPasword().equals(request.getConfirmPassword())))
        {
            throw new PasswordChangeException("New  Password and the Confirm New Password doesn't match");
        }
        user.setPassword(encoder.encode(request.getNewPasword()));
        userRepository.save(user);

        //After saving the password successfully, The current token needs to be invalidated
        tokenService.revokeAllUserTokens(user);

    }
}
