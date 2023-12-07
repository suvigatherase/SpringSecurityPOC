// package com.hook.form.backend.org.model;

// import java.util.Collection;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
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
// public class Privilege {
 
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;

//     private String name;

//     @ManyToMany(mappedBy = "privileges")
//     private Collection<Role> roles;
// }