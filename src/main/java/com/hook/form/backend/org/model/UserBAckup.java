// package com.hook.form.backend.org.model;


// import java.util.Collection;
// import java.util.List;
// import java.util.stream.Collectors;

// import javax.persistence.ElementCollection;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.JoinTable;
// import javax.persistence.ManyToMany;
// import javax.persistence.Table;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// @Entity
// @Table(name = "base_user") //Not able to Create table name User;not sure why
// public class UserBAckup implements UserDetails {
//     @Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;
//     private String username;
//     private String email;
//     private String firstName;
//     private String lastName;
//     private String password;
 
    
//     // @Enumerated(EnumType.STRING)
//     // private Role role;
//     // @OneToMany(mappedBy = "user")
//     //  If the below line is not given,
//     //  it was  creatig  a new table with two columns to save the id of the two tables in the demo application
//     @JoinColumn(name = "user_id", referencedColumnName = "id")
//     @ElementCollection(targetClass=Token.class)
//     private List<Token> tokens;

//        @ManyToMany 
//     @JoinTable( 
//         name = "users_roles", 
//         joinColumns = @JoinColumn(
//           name = "user_id", referencedColumnName = "id"), 
//         inverseJoinColumns = @JoinColumn(
//           name = "role_id", referencedColumnName = "id")) 
//         private Collection<Role> roles;
//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {

//       List<SimpleGrantedAuthority> authorities =  getRoles().stream()
//       .flatMap(role->role.getPrivileges().stream())
//       .map(priv ->new SimpleGrantedAuthority(priv.getName()))
//               .collect(Collectors.toList());
      
//       authorities.addAll(getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
//               .collect(Collectors.toList()));
            
//      return authorities;

//         // return Arrays.asList(new SimpleGrantedAuthority(role.name()));
//         // return role.getAuthorities();
//         // throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
//     }
//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }
//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }
//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }
//     @Override
//     public boolean isEnabled() {
//        return true;
//     }
//     @Override
//     public String getPassword() {
//         return password;
//     }


    
// }
