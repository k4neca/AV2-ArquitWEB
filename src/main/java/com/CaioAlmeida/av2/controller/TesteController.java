package com.CaioAlmeida.av2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TesteController {

    @GetMapping("/teste")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> testeProtegido() {
        return ResponseEntity.ok("✅ Acesso autorizado com JWT!");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> somenteAdmin() {
        return ResponseEntity.ok("🔐 Acesso exclusivo para ADMIN!");
    }
}

