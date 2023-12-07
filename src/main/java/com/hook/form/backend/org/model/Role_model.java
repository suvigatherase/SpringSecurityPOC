// package com.hook.form.backend.org.model;

// import java.util.Collection;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.JoinTable;
// import javax.persistence.ManyToMany;

// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// @Entity
// public class Role {
 
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;

//     private String name;
//     @ManyToMany(mappedBy = "roles")
//     private Collection<User_backup> users;

//     @ManyToMany
//     @JoinTable(
//         name = "roles_privileges", 
//         joinColumns = @JoinColumn(
//           name = "role_id", referencedColumnName = "id"), 
//         inverseJoinColumns = @JoinColumn(
//           name = "privilege_id", referencedColumnName = "id"))
//     private Collection<Privilege> privileges;
// }