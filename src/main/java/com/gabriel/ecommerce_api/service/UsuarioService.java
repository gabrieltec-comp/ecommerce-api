package com.gabriel.ecommerce_api.service;

import com.gabriel.ecommerce_api.dto.usuario.UsuarioRequestDto;
import com.gabriel.ecommerce_api.dto.usuario.UsuarioResponseDto;
import com.gabriel.ecommerce_api.dto.usuario.UsuarioUpdateDto;
import com.gabriel.ecommerce_api.exception.RecursoNaoEncontradoException;
import com.gabriel.ecommerce_api.exception.RegraNegocioException;
import com.gabriel.ecommerce_api.infrastructure.entity.Usuario;
import com.gabriel.ecommerce_api.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDto buscarPorId(Long id)
    {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com ID: " + id));
        return new UsuarioResponseDto(usuario);
    }

    public UsuarioResponseDto criarUsuario(UsuarioRequestDto dto)
    {
        if(dto.getConfirmarSenha() == null || !(dto.getConfirmarSenha().equals(dto.getSenha())))
        {
            throw new RegraNegocioException("Confirmação de senha falhou.");
        }
        if(usuarioRepository.existsByEmail(dto.getEmail()))
        {
            throw new RegraNegocioException("Email já está cadastrado!");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setSobrenome((dto.getSobrenome()));
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        Usuario salvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDto(salvo);

    }

    public UsuarioResponseDto atualizarUsuario(Long id,  UsuarioUpdateDto dto)
    {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com ID: " + id));
        if(!(usuario.getEmail().equals(dto.getEmail())) && usuarioRepository.existsByEmail(dto.getEmail()))
        {
            throw new RegraNegocioException("Email já está em uso!");
        }
        usuario.setNome(dto.getNome());
        usuario.setSobrenome(dto.getSobrenome());
        usuario.setEmail(dto.getEmail());

        Usuario atualizado = usuarioRepository.save(usuario);
        return new UsuarioResponseDto(atualizado);

    }

    public void deletarUsuario(Long id)
    {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }





}
