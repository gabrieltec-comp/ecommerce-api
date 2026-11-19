package com.gabriel.ecommerce_api.dto.categoria;

import com.gabriel.ecommerce_api.infrastructure.entity.Categoria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaResponseDto {

    private Long id;
    private String nome;
    private String slug;
    private Boolean usarNoMenu;

    public CategoriaResponseDto(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.slug = categoria.getSlug();
        this.usarNoMenu = categoria.getUsarNoMenu();
    }
}
