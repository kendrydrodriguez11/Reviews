package com.example.auth.service;

import com.example.auth.config.ConfigurationAuth;
import com.example.auth.controller.required.LoginUser;
import com.example.auth.controller.required.RegisterUser;
import com.example.auth.model.EntityUser;
import com.example.auth.repository.AuthRepository;
import com.example.auth.service.jwt.JwtAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final AuthenticationManager authenticationManager;
    private final DetailsUser detailsUser;
    private final JwtAuth jwtAuth;

    @Override
    public void registerUser(EntityUser user) {
        authRepository.save(user);
    }

    @Override
    public String authenticateUser(LoginUser user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        EntityUser userLogin = detailsUser.getDetilsUser(user.getUsername());
        return jwtAuth.CreatedToken(userLogin);

    }
}
