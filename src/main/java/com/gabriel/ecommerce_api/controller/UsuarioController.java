package com.gabriel.ecommerce_api.controller;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.gabriel.ecommerce_api.dto.usuario.UsuarioRequestDto;
import com.gabriel.ecommerce_api.dto.usuario.UsuarioResponseDto;
import com.gabriel.ecommerce_api.dto.usuario.UsuarioUpdateDto;
import com.gabriel.ecommerce_api.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> criarUsuario(@Valid
                                                           @RequestBody
                                                           UsuarioRequestDto dto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioResponseDto> atualizarUsuario(@PathVariable Long id,
                                                               @Valid
                                                               @RequestBody
                                                               UsuarioUpdateDto dto)
    {
            return ResponseEntity.ok(usuarioService.atualizarUsuario(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id)
    {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
