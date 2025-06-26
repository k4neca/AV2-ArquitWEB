package com.CaioAlmeida.av2.repository;

import com.CaioAlmeida.av2.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
