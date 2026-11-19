package com.gabriel.ecommerce_api.dto.produto;

import com.gabriel.ecommerce_api.infrastructure.entity.Categoria;
import com.gabriel.ecommerce_api.infrastructure.entity.Produto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data

public class ProdutoResponseDto {

    private Long id;
    private Boolean ativo;
    private String nome;
    private String slug;
    private String usarNoMenu;
    private Integer quantidade;
    private String descricao;
    private Double preco;
    private Double precoComDesconto;
    private List<Long> categoriaIds;
    private List<ProdutoImagemDto> imagens;
    private List<ProdutoOpcaoDto> opcoes;

    public ProdutoResponseDto(Produto produto) {
        this.id = produto.getId();
        this.ativo = produto.getAtivo();
        this.nome = produto.getNome();
        this.slug = produto.getSlug();
        this.usarNoMenu = String.valueOf(produto.getUsarNoMenu());
        this.quantidade = produto.getEstoque();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.precoComDesconto = produto.getPrecoComDesconto();

        if (produto.getCategorias() != null) {
            this.categoriaIds = produto.getCategorias().stream()
                    .map(Categoria::getId)
                    .collect(Collectors.toList());
        }

        if (produto.getImagens() != null) {
            this.imagens = produto.getImagens().stream()
                    .map(ProdutoImagemDto::new)
                    .collect(Collectors.toList());
        }

        if (produto.getOpcoes() != null) {
            this.opcoes = produto.getOpcoes().stream()
                    .map(ProdutoOpcaoDto::new)
                    .collect(Collectors.toList());
        }
    }
}
