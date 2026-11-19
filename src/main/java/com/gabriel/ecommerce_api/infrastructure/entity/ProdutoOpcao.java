package com.gabriel.ecommerce_api.infrastructure.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "produto_opcoes")
@Getter
@Setter

public class ProdutoOpcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormatoOpcao formato = FormatoOpcao.QUADRADO;

    @Column(nullable = false)
    private Integer raio = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoOpcao tipo = TipoOpcao.TEXTO;

    @Column(nullable = false)
    private String valores;

    public enum FormatoOpcao {
        QUADRADO,
        CIRCULO
    }

    public enum TipoOpcao {
        TEXTO,
        COR
    }
}
