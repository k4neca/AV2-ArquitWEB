package com.CaioAlmeida.av2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/protegido")
public class ExemploProtegidoController {

    // Qualquer usuário autenticado (USER ou ADMIN)
    @GetMapping("/usuario")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String acessoUsuario() {
        return "✅ Acesso permitido para USER ou ADMIN!";
    }

    // Somente usuários com papel ADMIN
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String acessoAdmin() {
        return "✅ Acesso permitido somente para ADMIN!";
    }
}
