package com.pushpey.demorest.controller;

import com.pushpey.demorest.dto.LoginRequest;
import com.pushpey.demorest.entity.User;
import com.pushpey.demorest.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        return authService.login(
                request.getUsername(),
                request.getPassword()
        );
    }
}