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
@RequestMapping("/gmp/admin/")
@PreAuthorize("hasRole('ADMIN')")
public class SecurityRoleAdminDemoController {
   

    

    @GetMapping("read")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<String> read()
    {
        return ResponseEntity.ok("SecurityRoleAdminDemoController::read()");
    }

    @PostMapping("create")
    @PreAuthorize("hasAuthority('admin:create')")
    public ResponseEntity<String> create()
    {
        return ResponseEntity.ok("SecurityRoleAdminDemoController::create()");
    }
      @PutMapping("update")
     @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<String> update()
    {
        return ResponseEntity.ok("SecurityRoleAdminDemoController::update()");
    }
   @DeleteMapping("delete")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<String> delete()
    {
        return ResponseEntity.ok("SecurityRoleAdminDemoController::delete()");
    }

}
