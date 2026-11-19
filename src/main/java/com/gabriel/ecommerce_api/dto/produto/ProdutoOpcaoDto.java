package com.gabriel.ecommerce_api.dto.produto;


import com.gabriel.ecommerce_api.infrastructure.entity.ProdutoOpcao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ProdutoOpcaoDto {

    private Long id;
    private String titulo;
    private String formato;
    private Integer raio;
    private String tipo;
    private String valores;

    public ProdutoOpcaoDto(ProdutoOpcao opcao) {
        this.id = opcao.getId();
        this.titulo = opcao.getTitulo();
        this.formato = String.valueOf(opcao.getFormato());
        this.raio = opcao.getRaio();
        this.tipo = String.valueOf(opcao.getTipo());
        this.valores = opcao.getValores();
    }
}
