package com.hook.form.backend.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hook.form.backend.org.config.WebSecurityConfig;
import com.hook.form.backend.org.model.AuthenticationRequest;
import com.hook.form.backend.org.model.AuthenticationResponse;
import com.hook.form.backend.org.model.SignUpRequest;
import com.hook.form.backend.org.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private WebSecurityConfig secuirty_config;
    @Autowired
	private AuthenticationService authenticationService;

	@PostMapping(value = "/signup")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody SignUpRequest request) throws Exception {
		return ResponseEntity.ok(authenticationService.register(request));
	}
    @PostMapping(value = "/authenticate")
	private ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        try {
			return ResponseEntity.ok(authenticationService.authenticate(request));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
