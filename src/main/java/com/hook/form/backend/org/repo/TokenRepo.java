package com.hook.form.backend.org.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hook.form.backend.org.model.Token;

public interface TokenRepo extends JpaRepository<Token, Integer> {
    @Query("Select t from Token t inner join User u on t.user.id=u.id where u.id=:user_id and (t.is_expired=false or t.is_revoked=false)")
    List<Token> findallValidtokenByUser(Integer user_id);
    
    Optional<Token>  findByToken(String token);
}
