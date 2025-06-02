package com.sistema.biblioteca.controllers;

import com.sistema.biblioteca.dtos.LivroDTO;
import com.sistema.biblioteca.models.Livro;
import com.sistema.biblioteca.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("livro")
public class LivroController {

    @Autowired
    LivroService livroService;

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Integer id) {
       Livro livro = livroService.findById(id);
       return ResponseEntity.ok().body(new LivroDTO(livro));
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "livro", defaultValue = "0") Integer livroId) {
        List<Livro> list = livroService.findAll(livroId);
        return ResponseEntity.ok().body(list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList()));
    }

    @GetMapping("/categoria/{nome}")
    public ResponseEntity<List<LivroDTO>> findByCategoriaNome(@PathVariable String nome) {
        List<Livro> list = livroService.findByCategoriaNome(nome);
        return ResponseEntity.ok().body(list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<LivroDTO> save(@RequestParam(value = "livro", defaultValue = "0") Integer id_livro,
                                         @RequestBody LivroDTO livroDTO) {
        Livro livro = livroService.save(id_livro, livroDTO);
        return ResponseEntity.ok().body(new LivroDTO(livro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> update(@PathVariable Integer id, Integer id_livro, @RequestBody LivroDTO livroDTO) {
        livroDTO.setId(id_livro);
        Livro livro = livroService.update(id_livro, livroDTO);
        return ResponseEntity.ok().body(new LivroDTO(livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
  }
