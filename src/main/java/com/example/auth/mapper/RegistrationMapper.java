package com.example.auth.mapper;

import com.example.auth.model.User;
import com.example.auth.request.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class RegistrationMapper {
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(request.getPassword()))" )
    public abstract User toUser(RegistrationRequest request);
}
