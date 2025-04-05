package com.example.auth.service;

import com.example.auth.controller.required.LoginUser;
import com.example.auth.controller.required.RegisterUser;
import com.example.auth.model.EntityUser;

public interface AuthService {
    void registerUser(EntityUser user);

    String authenticateUser(LoginUser user);
}
