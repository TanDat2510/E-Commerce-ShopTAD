package com.tad.controller;

import com.tad.dto.message.RegisterDto;
import com.tad.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor// tu dong tao ra mot constructor
public class AuthController {
    private final AuthService authService;
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(this.authService.register(registerDto), HttpStatus.CREATED);
    }
}
