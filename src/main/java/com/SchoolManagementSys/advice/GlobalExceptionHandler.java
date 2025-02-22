package com.SchoolManagementSys.advice;

import com.SchoolManagementSys.exceptions.ResourceNotFound;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFound exception) {
        ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiError> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        ApiError apiError = new ApiError(exception.getLocalizedMessage(), HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(apiError, HttpStatus.NOT_ACCEPTABLE);
    }

//
//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex) {
//        ApiError apiError = new ApiError(ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
//        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(JwtException.class)
//    public ResponseEntity<ApiError> handleJwtException(JwtException ex) {
//        ApiError apiError = new ApiError("JWT error -> "+ ex.getLocalizedMessage(), HttpStatus.UNAUTHORIZED);
//        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException ex) {
//        ApiError apiError = new ApiError("AccessDeniedException error -> "+ ex.getLocalizedMessage(), HttpStatus.FORBIDDEN);
//        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
//    }
}
