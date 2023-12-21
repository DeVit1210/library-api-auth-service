package com.example.auth.service;

import com.example.auth.mapper.RegistrationMapper;
import com.example.auth.model.User;
import com.example.auth.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final RegistrationMapper mapper;
    private final CustomUserDetailsService userDetailsService;
    public ResponseEntity<String> register(RegistrationRequest request) {
        User user = mapper.toUser(request);
        if (userDetailsService.isUserExists(user.getUsername())) {
            throw new IllegalStateException("user with such username is already authenticated!");
        }
        UserDetails savedUser = userDetailsService.saveUser(user);
        return ResponseEntity.ok("user with username " + savedUser.getUsername() + "successfully registered!");
    }
}
