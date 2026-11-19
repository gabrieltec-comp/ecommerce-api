package com.gabriel.ecommerce_api.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.gabriel.ecommerce_api.dto.categoria.CategoriaRequestDto;
import com.gabriel.ecommerce_api.dto.categoria.CategoriaResponseDto;
import com.gabriel.ecommerce_api.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listarTodas()
    {
        return ResponseEntity.ok(categoriaService.listarTodasCategorias());
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoriaResponseDto> buscarPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDto> criarCategoria(@Valid
                                                                @RequestBody CategoriaRequestDto dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criarCategoria(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoriaResponseDto> atualizarCategoria(@PathVariable Long id,
                                                                    @Valid
                                                                    @RequestBody CategoriaRequestDto dto)
    {
        return ResponseEntity.ok().body(categoriaService.atualizarCategoria(id, dto));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id)
    {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }






}
