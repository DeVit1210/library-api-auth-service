package com.example.auth.service;

import com.example.auth.jwt.JwtUtils;
import com.example.auth.mapper.AuthenticationMapper;
import com.example.auth.request.AuthenticationRequest;
import com.example.auth.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationMapper authenticationMapper;
    private final JwtUtils jwtUtils;

    public ResponseEntity<AuthenticationResponse> login(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = authenticationMapper.toAuthenticationToken(request);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if (authentication.isAuthenticated()) {
            String token = jwtUtils.generateToken(request.getUsername());
            return ResponseEntity.ok(AuthenticationResponse.builder().token(token).build());
        } else throw new IllegalStateException("user is not authenticated!");
    }

    public boolean validateToken(String token) {
        String username = jwtUtils.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtils.isTokenValid(token, userDetails);
    }

}
