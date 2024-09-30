package com.api.registration.exception;

import com.api.registration.payload.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;



@ControllerAdvice
public class GlobalException {
    @ExceptionHandler
    public ResponseEntity<ErrorDto> resourcenotfound(
            ResourceNotFound r,
            WebRequest request

    ){
        ErrorDto error= new ErrorDto(r.getMessage(),new Date(),request.getDescription(false) );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler
    public  ResponseEntity<String> globalexception(Exception e){
return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
