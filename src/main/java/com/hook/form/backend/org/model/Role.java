package com.hook.form.backend.org.model;

import static com.hook.form.backend.org.model.Permission.ADMIN_CREATE;
import static com.hook.form.backend.org.model.Permission.ADMIN_DELETE;
import static com.hook.form.backend.org.model.Permission.ADMIN_READ;
import static com.hook.form.backend.org.model.Permission.ADMIN_UPDATE;
import static com.hook.form.backend.org.model.Permission.DEFAULT_CREATE;
import static com.hook.form.backend.org.model.Permission.DEFAULT_DELETE;
import static com.hook.form.backend.org.model.Permission.DEFAULT_READ;
import static com.hook.form.backend.org.model.Permission.DEFAULT_UPDATE;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
  USER(Collections.emptySet()),
  ADMIN(
          EnumSet.of(
                  ADMIN_READ,
                  ADMIN_CREATE,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  DEFAULT_READ,
                  DEFAULT_CREATE,
                  DEFAULT_UPDATE,
                  DEFAULT_DELETE
                  
          )
  ),
  DEFAULT(EnumSet.of(DEFAULT_READ,
                  DEFAULT_UPDATE,
                //   DEFAULT_DELETE,
                  DEFAULT_CREATE) ),
  ;

 @Getter
 private final Set<Permission> permissions;
     public List<SimpleGrantedAuthority> getAuthorities()
    {
    List<SimpleGrantedAuthority> authorities=  getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());

    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
            
    return authorities;

                
    }
}