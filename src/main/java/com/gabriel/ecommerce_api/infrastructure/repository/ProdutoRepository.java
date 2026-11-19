package com.gabriel.ecommerce_api.infrastructure.repository;

import com.gabriel.ecommerce_api.infrastructure.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    void deleteById(Long id);
}
