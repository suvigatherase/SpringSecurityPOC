package com.hook.form.backend.org.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
@RequestMapping("/gmp/security-poc")
public class SecurityDemoController {
    
    @GetMapping("/status-check")
    public ResponseEntity<String> status()
    {
        return ResponseEntity.ok("Message form Secured API");
    }
}
