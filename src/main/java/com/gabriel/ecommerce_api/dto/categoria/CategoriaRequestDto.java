package com.gabriel.ecommerce_api.dto.categoria;

import com.gabriel.ecommerce_api.infrastructure.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaRequestDto {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O slug é obrigatório")
    private String slug;

    private Boolean usarNoMenu = false;

}
