package com.gabriel.ecommerce_api.infrastructure.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "categorias")
@Getter
@Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String slug;

    @Column(name = "usar_no_menu")
    private Boolean usarNoMenu = false;

    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<Produto>();

    @CreationTimestamp
    @Column(name = "criado_em: ", updatable = false)
    private LocalDateTime criadoEm;



    }
