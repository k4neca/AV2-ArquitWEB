package com.CaioAlmeida.av2.service;

import com.CaioAlmeida.av2.dto.LoginDTO;
import com.CaioAlmeida.av2.dto.RegisterDTO;
import com.CaioAlmeida.av2.model.User;
import com.CaioAlmeida.av2.repository.UserRepository;
import com.CaioAlmeida.av2.security.JwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(RegisterDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Usuário já existe.");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();

        userRepository.save(user);

        return "Usuário registrado com sucesso.";
    }

    public String login(LoginDTO dto) {
        Optional<User> userOpt = userRepository.findByUsername(dto.getUsername());

        if (userOpt.isEmpty() || !passwordEncoder.matches(dto.getPassword(), userOpt.get().getPassword())) {
            System.out.println("❌ Usuário ou senha inválidos.");
            throw new RuntimeException("Usuário ou senha inválidos.");
        }

        User user = userOpt.get();
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        System.out.println("🔐 Token gerado para o usuário " + user.getUsername() + ":");
        System.out.println(token);

        return token;
    }


}
