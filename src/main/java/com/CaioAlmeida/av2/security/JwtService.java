package com.CaioAlmeida.av2.security;

import com.CaioAlmeida.av2.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtUtil jwtUtil;

    public String generateToken(User user) {
        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}

