package com.gabriel.ecommerce_api.infrastructure.repository;

import com.gabriel.ecommerce_api.infrastructure.entity.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(@NotBlank(message = "O email é obrigatório") @Email(message = "Formato de email inválido") String email);
}
