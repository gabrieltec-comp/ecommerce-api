package com.gabriel.ecommerce_api.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioUpdateDto {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O sobrenome é obrigatório")
    private String sobrenome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;
}
