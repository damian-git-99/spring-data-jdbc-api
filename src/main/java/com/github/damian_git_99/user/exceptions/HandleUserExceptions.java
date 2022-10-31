package com.github.damian_git_99.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandleUserExceptions {


    @ExceptionHandler(Exception.class)
    ResponseEntity<Map<String, Object>> handleException(Exception e) {
        var map = new HashMap<String, Object>();
        map.put("error", e.getMessage());
        return ResponseEntity.internalServerError()
                .body(map);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    ResponseEntity<Map<String, Object>> handleEmailAlreadyTakenException(EmailAlreadyTakenException e) {
        var map = new HashMap<String, Object>();
        map.put("error", e.getMessage());
        return ResponseEntity.badRequest()
                .body(map);
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<Map<String, Object>> handleUserNotFoundException(UserNotFoundException e) {
        var map = new HashMap<String, Object>();
        map.put("error", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(map);
    }

}
