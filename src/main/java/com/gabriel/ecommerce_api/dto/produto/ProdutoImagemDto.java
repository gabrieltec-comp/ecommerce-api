package com.gabriel.ecommerce_api.dto.produto;

import com.gabriel.ecommerce_api.infrastructure.entity.ProdutoImagem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoImagemDto {
    private Long id;
    private Boolean ativo;
    private String caminho;

    public ProdutoImagemDto(ProdutoImagem imagem) {
        this.id = imagem.getId();
        this.ativo = imagem.getAtivo();
        this.caminho = imagem.getCaminho();
    }
}
