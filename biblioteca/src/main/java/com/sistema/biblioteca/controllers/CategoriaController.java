package com.sistema.biblioteca.controllers;

import com.sistema.biblioteca.dtos.CategoriaDTO;
import com.sistema.biblioteca.models.Categoria;
import com.sistema.biblioteca.services.CategoriaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok().body(modelMapper.map(categoria, CategoriaDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> list = categoriaService.findAll();
        return ResponseEntity.ok().body(list.stream().map(categoria -> modelMapper.map(categoria, CategoriaDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> save(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        categoriaDTO.setId(null);
        Categoria cat = categoriaService.save(modelMapper.map(categoriaDTO, Categoria.class));
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        categoriaDTO.setId(id);
        Categoria categoria = categoriaService.update(modelMapper.map(categoriaDTO, Categoria.class));
        return ResponseEntity.ok().body(modelMapper.map(categoria, CategoriaDTO.class));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    }


