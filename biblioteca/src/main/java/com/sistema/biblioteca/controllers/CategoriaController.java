package com.sistema.biblioteca.controllers;

import com.sistema.biblioteca.dtos.CategoriaDTO;
import com.sistema.biblioteca.models.Categoria;
import com.sistema.biblioteca.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok().body(new CategoriaDTO(categoria));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer categoriaId) {
        List<Categoria> list = categoriaService.findAll();

        List<CategoriaDTO> categoriaDTOList = list.stream().map(CategoriaDTO::
                new).toList();
        return ResponseEntity.ok().body(categoriaDTOList);

    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> save(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat,
                                             @RequestBody CategoriaDTO categoriaDTO) {
        Categoria cat = categoriaService.save(id_cat, categoriaDTO);
        return ResponseEntity.ok().body(new CategoriaDTO(cat));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable String nome, @Valid @RequestBody CategoriaDTO categoriaDTO) {
      Categoria categoriaExistente = categoriaService.findByNome(nome);

      if(categoriaExistente == null) {
          return ResponseEntity.notFound().build();
      }

      categoriaExistente.setNome(categoriaDTO.getNome());
      categoriaExistente.setDescrição(categoriaDTO.getDescrição());

      Categoria categoriaAtualizada = categoriaService.update(categoriaExistente);

      CategoriaDTO categoriaAtualizadaDTO = new CategoriaDTO();
      categoriaAtualizada.setNome(categoriaAtualizada.getNome());
      categoriaAtualizada.setDescrição(categoriaAtualizada.getDescrição());

      return ResponseEntity.ok().body(categoriaAtualizadaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    }


