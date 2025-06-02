package com.sistema.biblioteca.services;

import com.sistema.biblioteca.dtos.LivroDTO;
import com.sistema.biblioteca.enums.Tamanho;
import com.sistema.biblioteca.models.Categoria;
import com.sistema.biblioteca.models.Livro;
import com.sistema.biblioteca.repositories.CategoriaRepository;
import com.sistema.biblioteca.repositories.LivroRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    public Livro findById(Integer id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            return livro.get();
        }
        throw new IllegalArgumentException("Livro não encontrado.");
    }

    public List<Livro> findAll(Integer livroId) {
        return livroRepository.findAll();
    }


    public List<Livro> findByCategoriaNome(String nome) {
        return livroRepository.findByCategoriaNome(nome);
    }

    public Livro save(Integer idLivro, LivroDTO livroDTO) {
        Livro livro = livroRepository.findById(livroDTO.getCategoria().getId())
                .orElseThrow(() -> new DataIntegrityViolationException("Categoria não encontrada."));

        Livro liv = new Livro();
        livro.setAutor(livro.getAutor());
        livro.setTitulo(livro.getTitulo());
        livro.setTexto(livro.getTexto());
        livro.setCategoria(livro.getCategoria());
        livro.setTamanho(livro.getTamanho());

        return livroRepository.save(liv);
    }

    public Livro update(Integer idLivro, LivroDTO livroDTO) {
        Livro livro = livroRepository.findById(idLivro)
                .orElseThrow(() -> new IllegalArgumentException("Livro com ID " + idLivro + " não encontrado"));

        livro.setAutor(livroDTO.getAutor());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setTexto(livroDTO.getTexto());
        livro.setCategoria(livroDTO.getCategoria());
        livro.setTamanho(livroDTO.getTamanho());

        if (livroDTO.getCategoria() != null && livroDTO.getCategoria().getId() != null) {
            Categoria cat = categoriaRepository.findById(livroDTO.getCategoria().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada."));
        }

        return livroRepository.save(livro);
    }

    public void delete(Integer id) {
        findById(id);
        Optional<Livro> livro = livroRepository.delete(id);
        if(!livro.get().getAutor().isEmpty()) {
            throw new DataIntegrityViolationException("Não é possível excluir o livro que você solicitou.");
        }

    }
}
