package com.hook.form.backend.org.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hook.form.backend.org.exception.CustomExceptions.PasswordChangeException;
import com.hook.form.backend.org.model.PasswordChangeRequest;
import com.hook.form.backend.org.service.PasswordChangeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/password-change")
@RequiredArgsConstructor
@CrossOrigin
public class PasswordChangeController
{
    private final PasswordChangeService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequest request, Principal connectedUser) throws PasswordChangeException

    {
        service.changePassword(request,connectedUser);
        return ResponseEntity.ok().build();
    }
}