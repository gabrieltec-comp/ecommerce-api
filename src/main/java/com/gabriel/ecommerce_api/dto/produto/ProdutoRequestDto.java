package com.gabriel.ecommerce_api.dto.produto;

import com.gabriel.ecommerce_api.infrastructure.entity.ProdutoImagem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProdutoRequestDto {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O slug é obrigatório")
    private String slug;

    @NotNull(message = "A quantidade do produto é obrigatória")
    private Integer quantidade;

    @NotNull(message = "É obriatório informar o preço do produto")
    private Double preco;

    @NotNull(message = "É obriatório informar o preço com desconto do produto")
    private Double precoComDesconto;

    private String descricao;

    private Boolean usarNoMenu = false;

    private List<Long> categoriaIds;
    private List<ProdutoImagemDto> imagens;
    private List<ProdutoOpcaoDto> opcoes;




}
