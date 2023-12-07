package com.hook.form.backend.org.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hook.form.backend.org.exception.CustomExceptions.PasswordChangeException;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
  public static final String TAMPERED_TOKEN = "Invalid Token  or Tampered Token";

  
    @ExceptionHandler({ PasswordChangeException.class })
    @ResponseBody
    public ResponseEntity<RestError> handlePasswordChangeException(PasswordChangeException ex) {

        RestError re = new RestError(HttpStatus.NOT_ACCEPTABLE.toString(), 
          ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(re);
    }
    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<RestError> handleAuthenticationException(AuthenticationException ex) {

        RestError re = new RestError(HttpStatus.UNAUTHORIZED.toString(), 
          "Authentication failed at controller advice");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(re);
    }
    @ExceptionHandler({ BadCredentialsException.class })
    @ResponseBody
    public ResponseEntity<RestError> handleAuthenticationException(BadCredentialsException ex) {

        RestError re = new RestError(HttpStatus.UNAUTHORIZED.toString(), 
          ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(re);
    }
    
    @ExceptionHandler({ ExpiredJwtException.class })
    @ResponseBody
    public ResponseEntity<RestError> handleAuthenticationException(ExpiredJwtException ex) {

        RestError re = new RestError(HttpStatus.UNAUTHORIZED.toString(), 
          ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(re);
    }
    @ExceptionHandler({ InsufficientAuthenticationException.class })
    @ResponseBody
    public ResponseEntity<RestError> handleAuthenticationException(InsufficientAuthenticationException ex) {

        RestError re = new RestError(HttpStatus.EXPECTATION_FAILED.toString(), 
          TAMPERED_TOKEN);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(re);
    }

     @ExceptionHandler({Exception.class })
    @ResponseBody
    public ResponseEntity<RestError> handleAuthenticationException(Exception ex) {

        RestError re = new RestError(HttpStatus.UNAUTHORIZED.toString(), 
          ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(re);
    }
}