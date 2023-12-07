package com.hook.form.backend.org.exception.CustomExceptions;



import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenException  extends Exception{
    String message;

    public TokenException(String message) {
        this.message = message;
    }
    
}
