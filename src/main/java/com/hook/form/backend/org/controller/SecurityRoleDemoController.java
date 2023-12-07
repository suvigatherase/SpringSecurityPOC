package com.hook.form.backend.org.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/gmp/default/")
public class SecurityRoleDemoController {
    @GetMapping("read")
    public ResponseEntity<String> read()
    {
        return ResponseEntity.ok("SecurityRoleDemoController::read()");
    }
    @PostMapping("create")
    public ResponseEntity<String> create()
    {
        return ResponseEntity.ok("SecurityRoleDemoController::create()");
    }
    @PutMapping("update")
    public ResponseEntity<String> update()
    {
        return ResponseEntity.ok("SecurityRoleDemoController::update()");
    }
    @DeleteMapping("delete")
    @PreAuthorize("hasAuthority('default:delete')")
    public ResponseEntity<String> delete()
    {
        return ResponseEntity.ok("SecurityRoleDemoController::delete()");
    }
    


}
