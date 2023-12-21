package com.example.auth.controller;

import com.example.auth.request.AuthenticationRequest;
import com.example.auth.request.RegistrationRequest;
import com.example.auth.response.AuthenticationResponse;
import com.example.auth.service.AuthenticationService;
import com.example.auth.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return authenticationService.login(request);
    }

    @GetMapping
    public boolean validateToken(@RequestParam String token) {
        return authenticationService.validateToken(token);
    }
}
