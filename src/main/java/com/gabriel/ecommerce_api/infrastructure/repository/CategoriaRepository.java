package com.gabriel.ecommerce_api.infrastructure.repository;

import com.gabriel.ecommerce_api.infrastructure.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


}
