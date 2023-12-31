package com.springboot3crud.exception;



import com.springboot3crud.utils.Constants;
import com.springboot3crud.utils.ResponseBuilder;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@ControllerAdvice(basePackages = "com.agridence.microservice.assignment.controller")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<JSONObject> handleResourceNotFoundExceptions(Exception ex) {
        return new ResponseEntity<>(ResponseBuilder.error((ex.getMessage() + " " + Constants.RESOURCE_NOT_FOUND)).getJson(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<JSONObject> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        return new ResponseEntity<>(ResponseBuilder.error((
                handleExceptionInternal(
                        ex,
                        "invalid username/password",
                        new HttpHeaders(),
                        HttpStatus.UNAUTHORIZED, request))).getJson(),
                HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<JSONObject> handleConstraintViolation(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ResponseBuilder.error((handleExceptionInternal(
                ex, ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request))).getJson(),
                HttpStatus.BAD_REQUEST);
    }

}
