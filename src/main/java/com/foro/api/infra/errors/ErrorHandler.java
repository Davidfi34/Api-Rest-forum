package com.foro.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;


//ERROR MANAGEMENT
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity treatError404(){
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity errorHandlerIntegrity(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerBusinessValidation(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    //INVALID ARGUMENTS
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity treatError400(MethodArgumentNotValidException e){

        var error = e.getFieldErrors().stream().map(ValidationErrorData::new).toList();
        return ResponseEntity.badRequest().body(error);
    }


    //ERROR DUPLICATE RECORD IN DATABASE
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity treatError409(){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Error: duplicate record");
    }


    private record ValidationErrorData(String field, String error){
        public ValidationErrorData(FieldError error){
            this(error.getField(),error.getDefaultMessage());
        }
    }

}
