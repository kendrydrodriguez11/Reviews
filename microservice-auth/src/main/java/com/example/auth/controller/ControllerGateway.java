package com.example.auth.controller;

import com.example.auth.controller.required.LoginUser;
import com.example.auth.controller.required.RegisterUser;
import com.example.auth.model.EntityRoles;
import com.example.auth.model.EntityUser;
import com.example.auth.model.RolesE;
import com.example.auth.service.AuthService;
import com.example.auth.service.jwt.JwtAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class ControllerGateway {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuth jwtAuth;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestPart String username,
                                               @RequestPart String email,
                                               @RequestPart String password,
                                               @RequestPart(required = false) MultipartFile profileImage)
    {
        try{
            Set<EntityRoles> rol = Collections.singleton(
                    EntityRoles.builder().name(RolesE.USER).build()
            );

            EntityUser userCreated = EntityUser.builder()
                    .username(username)
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .roles(rol)
                    .build();
            authService.registerUser(userCreated);
            return ResponseEntity.ok("Created successfully");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error ocurred");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody LoginUser user) {
        try {
            String token = authService.authenticateUser(user);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token)
                    .body(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Error-Message", "Credenciales inválidas")
                    .body(errorResponse);
        }
    }


    @PostMapping("/validationToken")
    public boolean ValidationToken(@RequestBody Map<String, String> request){
        return jwtAuth.ValidationToken(request.get("token"));
    }






}
