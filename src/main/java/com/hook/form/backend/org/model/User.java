package com.hook.form.backend.org.model;


import java.util.Collection;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "base_user") //Not able to Create table name User;not sure why
public class User implements UserDetails {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user")
    //  If the below line is not given,
    //  it was  creatig  a new table with two columns to save the id of the two tables in the demo application
    // @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ElementCollection(targetClass=Token.class)
    private List<Token> tokens;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        // return Arrays.asList(new SimpleGrantedAuthority(role.name()));
        return role.getAuthorities();
        // throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
       return true;
    }
    @Override
    public String getPassword() {
        return password;
    }


    
}
