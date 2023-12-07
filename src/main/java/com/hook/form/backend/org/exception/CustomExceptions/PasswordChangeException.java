package com.hook.form.backend.org.exception.CustomExceptions;

import org.hibernate.annotations.GenericGenerators;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PasswordChangeException  extends Exception{
    String message;

    public PasswordChangeException(String message) {
        this.message = message;
    }
    
}
