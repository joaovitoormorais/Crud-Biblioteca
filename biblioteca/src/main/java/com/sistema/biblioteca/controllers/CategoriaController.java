package com.sistema.biblioteca.controllers;

import com.sistema.biblioteca.dtos.CategoriaDTO;
import com.sistema.biblioteca.models.Categoria;
import com.sistema.biblioteca.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("livro")
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

}

