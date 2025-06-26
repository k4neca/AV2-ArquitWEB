package com.CaioAlmeida.av2.controller;

import com.CaioAlmeida.av2.model.Produto;
import com.CaioAlmeida.av2.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProdutoControllerTest {

    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ProdutoController produtoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProdutos() {
        Produto produto1 = new Produto(1L, "Notebook", 2500.00);
        Produto produto2 = new Produto(2L, "Mouse", 100.00);
        List<Produto> mockProdutos = Arrays.asList(produto1, produto2);

        when(produtoService.getAllProdutos()).thenReturn(mockProdutos);

        ResponseEntity<List<Produto>> response = produtoController.getAllProdutos();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(produtoService, times(1)).getAllProdutos();
    }

    @Test
    void testGetProdutoById_Found() {
        Produto produto = new Produto(1L, "Teclado", 150.00);

        when(produtoService.getProdutoById(1L)).thenReturn(Optional.of(produto));

        ResponseEntity<Produto> response = produtoController.getProdutoById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Teclado", response.getBody().getNome());
        verify(produtoService, times(1)).getProdutoById(1L);
    }

    @Test
    void testGetProdutoById_NotFound() {
        when(produtoService.getProdutoById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Produto> response = produtoController.getProdutoById(1L);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void testCreateProduto() {
        Produto produto = new Produto(null, "Cadeira Gamer", 1200.00);
        Produto savedProduto = new Produto(1L, "Cadeira Gamer", 1200.00);

        when(produtoService.createProduto(produto)).thenReturn(savedProduto);

        ResponseEntity<Produto> response = produtoController.createProduto(produto);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1L, response.getBody().getId());
        verify(produtoService, times(1)).createProduto(produto);
    }

    @Test
    void testDeleteProduto() {
        doNothing().when(produtoService).deleteProduto(1L);

        ResponseEntity<Void> response = produtoController.deleteProduto(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(produtoService, times(1)).deleteProduto(1L);
    }
}

