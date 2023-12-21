package com.example.auth.mapper;

import com.example.auth.jwt.authtoken.CustomUsernamePasswordAuthenticationToken;
import com.example.auth.model.User;
import com.example.auth.request.AuthenticationRequest;
import com.example.auth.request.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {
    @Mapping(target = "principal", source = "username")
    @Mapping(target = "credentials", source = "password")
    CustomUsernamePasswordAuthenticationToken toAuthenticationToken(AuthenticationRequest authenticationRequest);
}
