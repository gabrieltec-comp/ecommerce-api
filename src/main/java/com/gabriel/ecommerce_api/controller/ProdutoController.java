package com.gabriel.ecommerce_api.controller;

import com.gabriel.ecommerce_api.dto.produto.ProdutoRequestDto;
import com.gabriel.ecommerce_api.dto.produto.ProdutoResponseDto;
import com.gabriel.ecommerce_api.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDto>> listarTodos()
    {
        return ResponseEntity.ok(produtoService.listarTodosProdutos());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponseDto> buscarPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> criarProduto(@Valid
                                                           @RequestBody
                                                           ProdutoRequestDto dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.criarProduto(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoResponseDto> atualizarProduto(@PathVariable
                                                                Long id,
                                                                @Valid
                                                                @RequestBody
                                                                ProdutoRequestDto dto)
    {
        return ResponseEntity.ok(produtoService.atualizarProduto(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable
                                               Long id)
    {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

}
