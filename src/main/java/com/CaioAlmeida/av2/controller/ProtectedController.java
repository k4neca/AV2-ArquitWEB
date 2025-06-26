package com.CaioAlmeida.av2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teste")
@RequiredArgsConstructor
public class ProtectedController {

    @GetMapping("/publico")
    public String publico() {
        return "Este endpoint é público (sem token necessário).";
    }

    @GetMapping("/usuario")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String usuario() {
        return "Acesso permitido para USER ou ADMIN.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Acesso permitido somente para ADMIN.";
    }
}
