package com.hook.form.backend.org.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TOKEN")
public class Token implements Serializable {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "token")
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type")
    private Tokentype tokenType;

    @Column(name = "is_revoked")
    private Boolean is_revoked;
    @Column(name = "is_expired")
    private Boolean is_expired;
    //logininfo and token table uses Bidirectional mapping
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Token( String token,Tokentype tokenType, Boolean revoked, Boolean expired,User base_user) {
        this.token = token;
        this.tokenType = tokenType;
        this.is_revoked = revoked;
        this.is_expired = expired;
        this.user = base_user;
    }



}