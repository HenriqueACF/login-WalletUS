package com.example.login_and_register_api.controllers;

import com.example.login_and_register_api.domain.user.User;
import com.example.login_and_register_api.dto.LoginRequestDTO;
import com.example.login_and_register_api.dto.ResponseDTO;
import com.example.login_and_register_api.infra.security.TokenService;
import com.example.login_and_register_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body ){
        User user = this.repository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("USUÁRIO NAO ENCONTRADO."));
        if(passwordEncoder.matches(user.getPassword(), body.password())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}
