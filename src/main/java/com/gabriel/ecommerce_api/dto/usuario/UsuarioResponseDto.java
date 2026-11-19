package com.gabriel.ecommerce_api.dto.usuario;


import com.gabriel.ecommerce_api.infrastructure.entity.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioResponseDto {

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;

    public UsuarioResponseDto(Usuario usuario)
    {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.nome = usuario.getNome();
        this.sobrenome = usuario.getSobrenome();
    }

}
