package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dto.ErrorDTO;
import com.exception.NoExisteEquipoException;
import com.exception.RequestInvalidaException;

@RestControllerAdvice
public class ExceptionHadlerController {

    @ExceptionHandler(value = NoExisteEquipoException.class)
    public ResponseEntity<ErrorDTO> runtimeExceptionHadler(NoExisteEquipoException ex){
        ErrorDTO error = ErrorDTO.builder().code(HttpStatus.NOT_FOUND.value()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value= RequestInvalidaException.class)
    public ResponseEntity<ErrorDTO> invalidRequestExceptionHadler(RequestInvalidaException ex){
        ErrorDTO error = ErrorDTO.builder().code(HttpStatus.BAD_REQUEST.value()).message(ex.getMessage()).build();
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }


}
