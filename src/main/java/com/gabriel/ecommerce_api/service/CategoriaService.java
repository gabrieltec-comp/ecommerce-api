package com.gabriel.ecommerce_api.service;

import com.gabriel.ecommerce_api.dto.categoria.CategoriaRequestDto;
import com.gabriel.ecommerce_api.dto.categoria.CategoriaResponseDto;
import com.gabriel.ecommerce_api.exception.RecursoNaoEncontradoException;
import com.gabriel.ecommerce_api.infrastructure.entity.Categoria;
import com.gabriel.ecommerce_api.infrastructure.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public List<CategoriaResponseDto> listarTodasCategorias()
    {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaResponseDto::new)
                .collect(Collectors.toList());
    }

    public CategoriaResponseDto buscarPorId(Long id)
    {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada com ID: " + id));
        return new CategoriaResponseDto(categoria);
    }


    public CategoriaResponseDto criarCategoria(CategoriaRequestDto dto)
    {
        Categoria categoria = new Categoria();
        categoria.setNome( dto.getNome());
        categoria.setSlug(dto.getSlug());
        categoria.setUsarNoMenu(dto.getUsarNoMenu() != null ? dto.getUsarNoMenu() : false);
        Categoria salva = categoriaRepository.save(categoria);
        return new CategoriaResponseDto(salva);

    }

    public CategoriaResponseDto atualizarCategoria(Long id, CategoriaRequestDto dto)
    {
      Categoria categoria = categoriaRepository.findById(id)
              .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada com ID: " + id));

      categoria.setNome(dto.getNome());
      categoria.setSlug(dto.getSlug());
      if(dto.getUsarNoMenu() != null)
      {
          categoria.setUsarNoMenu(dto.getUsarNoMenu());
      }
      Categoria atualizada = categoriaRepository.save(categoria);

      return new CategoriaResponseDto(atualizada);

    }

    public void deletarCategoria(Long id)
    {
        if(!categoriaRepository.existsById(id))
        {
            throw new RecursoNaoEncontradoException("Categoria não encontrada com ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }


}
