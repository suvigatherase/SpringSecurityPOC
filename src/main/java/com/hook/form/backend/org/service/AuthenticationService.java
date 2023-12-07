package com.hook.form.backend.org.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hook.form.backend.org.model.AuthenticationRequest;
import com.hook.form.backend.org.model.AuthenticationResponse;
import com.hook.form.backend.org.model.SignUpRequest;
import com.hook.form.backend.org.model.Token;
import com.hook.form.backend.org.model.User;
import com.hook.form.backend.org.repo.UserRepository;
import com.hook.form.backend.org.util.JwtService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor

public class AuthenticationService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

   public AuthenticationResponse register(SignUpRequest request)
    {
        User user= User.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .username(request.getUsername())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        // .roles(roleService.findByName(request.getRole()))
        .role(request.getRole())
                .build();
                userRepository.save(user);
                String token = jwtService.generateToken(user);

        Token savedtoken= tokenService.saveToken(token,user);

        return AuthenticationResponse.builder().token(token).build();
        

    }
   public AuthenticationResponse authenticate(AuthenticationRequest request)
   {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        //user is authenticated
        User user = userRepository.findByUsername(request.getUsername()).get();
        // .orElseThrow();
        String token = jwtService.generateToken(user);
        tokenService.revokeAllUserTokens(user);
        Token savedtoken= tokenService.saveToken(token,user);
        return AuthenticationResponse.builder().token(token).build();

    }

}
