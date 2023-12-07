package com.hook.form.backend.org.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequest {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role role;

}
