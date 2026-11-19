package com.gabriel.ecommerce_api.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "produto_imagens")
@Getter
@Setter

public class ProdutoImagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false,name = "ativo")
    private Boolean ativo = false;

    @Column(nullable = false, name = "caminho")
    private String caminho;
}
