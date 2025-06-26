package com.CaioAlmeida.av2.service;

import com.CaioAlmeida.av2.dto.LoginDTO;
import com.CaioAlmeida.av2.dto.RegisterDTO;
import com.CaioAlmeida.av2.model.User;
import com.CaioAlmeida.av2.repository.UserRepository;
import com.CaioAlmeida.av2.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    private UserRepository userRepository;
    private JwtUtil jwtService; // JwtUtil é a classe real
    private PasswordEncoder passwordEncoder;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        jwtService = mock(JwtUtil.class); // JwtUtil em vez de JwtService
        passwordEncoder = mock(PasswordEncoder.class);
        authService = new AuthService(userRepository, passwordEncoder, jwtService);
    }

    @Test
    void testRegister() {
        RegisterDTO dto = new RegisterDTO();
        dto.setUsername("admin");
        dto.setPassword("123456");
        dto.setRole("USER");

        when(userRepository.findByUsername("admin")).thenReturn(Optional.empty());
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("encoded-password");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        String result = authService.register(dto);

        assertEquals("Usuário registrado com sucesso.", result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testLogin() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("encoded-password");
        user.setRole("USER");

        when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("123456", "encoded-password")).thenReturn(true);
        when(jwtService.generateToken("admin", "USER")).thenReturn("fake-jwt-token");

        String token = authService.login(new LoginDTO("admin", "123456"));

        assertNotNull(token);
        assertEquals("fake-jwt-token", token);
    }
}
