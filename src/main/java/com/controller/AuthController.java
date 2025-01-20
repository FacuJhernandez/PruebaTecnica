package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDTO;
import com.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Auth controller")
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    @Operation(summary  = "Permite el ingreso de un usuario")
    public ResponseEntity<Map<String,String>> login (@Valid @RequestBody UserDTO userDto){
        String token = authService.login(userDto.getUsername(),userDto.getPassword());
        Map<String,String> response= new HashMap<String,String>();
        response.put("token", token);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
