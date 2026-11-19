package com.gabriel.ecommerce_api.service;

import com.gabriel.ecommerce_api.dto.produto.ProdutoImagemDto;
import com.gabriel.ecommerce_api.dto.produto.ProdutoOpcaoDto;
import com.gabriel.ecommerce_api.dto.produto.ProdutoRequestDto;
import com.gabriel.ecommerce_api.dto.produto.ProdutoResponseDto;
import com.gabriel.ecommerce_api.exception.RecursoNaoEncontradoException;
import com.gabriel.ecommerce_api.exception.RegraNegocioException;
import com.gabriel.ecommerce_api.infrastructure.entity.Categoria;
import com.gabriel.ecommerce_api.infrastructure.entity.Produto;
import com.gabriel.ecommerce_api.infrastructure.entity.ProdutoImagem;
import com.gabriel.ecommerce_api.infrastructure.entity.ProdutoOpcao;
import com.gabriel.ecommerce_api.infrastructure.repository.CategoriaRepository;
import com.gabriel.ecommerce_api.infrastructure.repository.ProdutoRepository;
import com.gabriel.ecommerce_api.infrastructure.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public List<ProdutoResponseDto> listarTodosProdutos()
    {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoResponseDto::new)
                .collect(Collectors.toList());
    }

    public ProdutoResponseDto buscarPorId(Long id)
    {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com ID: " + id));
        return new ProdutoResponseDto(produto);
    }

    public void deletarProduto(Long id)
    {
        if(!produtoRepository.existsById(id))
            throw new RecursoNaoEncontradoException("Produto não encontrado com ID: " + id);
        produtoRepository.deleteById(id);
    }

    private void preeencherDados(Produto produto, ProdutoRequestDto dto)
    {
        if (dto.getPrecoComDesconto() != null && dto.getPrecoComDesconto().compareTo(dto.getPreco()) >= 0) {
            throw new RegraNegocioException("Preço com desconto deve ser menor que o preço original.");
        }

        produto.setNome(dto.getNome());
        produto.setSlug(dto.getSlug());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        int estoque = dto.getQuantidade() != null ? dto.getQuantidade() : 0;
        produto.setEstoque(estoque);
        produto.setAtivo(estoque > 0);
        produto.setAtivo(dto.getQuantidade() > 0);
        produto.setUsarNoMenu(dto.getUsarNoMenu() != null ? dto.getUsarNoMenu() : false);

        if (dto.getCategoriaIds() != null && !dto.getCategoriaIds().isEmpty()) {
            List<Categoria> categorias = categoriaRepository.findAllById(dto.getCategoriaIds());
            produto.setCategorias(categorias);
        } else {
            produto.setCategorias(new ArrayList<>());
        }

        if (produto.getImagens() == null) {
            produto.setImagens(new ArrayList<>());
        } else {
            produto.getImagens().clear();
        }

        if (dto.getImagens() != null) {
            for (ProdutoImagemDto imgDto : dto.getImagens()) {
                ProdutoImagem img = new ProdutoImagem();
                img.setCaminho(imgDto.getCaminho());
                img.setAtivo(imgDto.getAtivo() != null ? imgDto.getAtivo() : true);
                img.setProduto(produto);
                produto.getImagens().add(img);
            }
        }
        if (produto.getOpcoes() == null) {
            produto.setOpcoes(new ArrayList<>());
        } else {
            produto.getOpcoes().clear();
        }

        if (dto.getOpcoes() != null) {
            for (ProdutoOpcaoDto opcaoDto : dto.getOpcoes()) {
                ProdutoOpcao opcao = new ProdutoOpcao();
                opcao.setTitulo(opcaoDto.getTitulo());
                opcao.setRaio(opcaoDto.getRaio() != null ? opcaoDto.getRaio() : 0);
                opcao.setValores(opcaoDto.getValores());

                if (opcaoDto.getFormato() != null) {
                    opcao.setFormato(ProdutoOpcao.FormatoOpcao.valueOf(opcaoDto.getFormato().toUpperCase()));
                }
                if (opcaoDto.getTipo() != null) {
                    opcao.setTipo(ProdutoOpcao.TipoOpcao.valueOf(opcaoDto.getTipo().toUpperCase()));
                }

                opcao.setProduto(produto);
                produto.getOpcoes().add(opcao);
            }

        }
    }

    @Transactional
    public ProdutoResponseDto criarProduto(ProdutoRequestDto dto)
    {
        Produto produto = new Produto();
        preeencherDados(produto, dto);
        Produto salvo = produtoRepository.save(produto);

        return new ProdutoResponseDto(salvo);
    }

    @Transactional
    public ProdutoResponseDto atualizarProduto(Long id, ProdutoRequestDto dto)
    {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado com ID: " + id));

        preeencherDados(produto,dto);
        Produto atualizado = produtoRepository.save(produto);
        return new ProdutoResponseDto(atualizado);

    }



}
