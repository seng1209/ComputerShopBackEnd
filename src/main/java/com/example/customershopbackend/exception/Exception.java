package com.example.customershopbackend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class Exception {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleBusinessException(ResponseStatusException e){

        var baseError = BaseError.builder()
                .time(LocalTime.now())
                .message(e.getMessage())
                .code(e.getStatusCode().toString())
                .state(false)
                .error(e.getReason())
                .build();

        return new ResponseEntity<>(baseError, e.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,Object> handleValidateException(MethodArgumentNotValidException e){

        Map<String,Object> error = new HashMap<>();

        List<FieldError> errors = new ArrayList<>();

        e.getFieldErrors().forEach(fieldError -> errors.add(new FieldError(fieldError.getField(), fieldError.getDefaultMessage())));

        error.put("errors", errors);

        return error;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    BaseError<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e){

        return BaseError.builder()
                .time(LocalTime.now())
                .message(e.getMessage())
                .code(e.getLocalizedMessage())
                .state(false)
                .error(e.getCause())
                .build();
    }

}
