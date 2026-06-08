package com.pushpey.demorest.service;

import com.pushpey.demorest.entity.User;
import com.pushpey.demorest.repository.UserRepository;
import com.pushpey.demorest.config.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String register(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(user);

        return "User Registered Successfully";
    }

    public String login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        UserDetails userDetails =
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password(user.getPassword())
                        .authorities("USER")
                        .build();

        return jwtUtil.generateToken(userDetails);

    }

}