package com.hook.form.backend.org.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hook.form.backend.org.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
