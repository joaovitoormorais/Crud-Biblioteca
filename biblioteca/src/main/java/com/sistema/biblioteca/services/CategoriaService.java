package com.sistema.biblioteca.services;

import com.sistema.biblioteca.dtos.CategoriaDTO;
import com.sistema.biblioteca.models.Categoria;
import com.sistema.biblioteca.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id) {
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria save(Integer idCat, CategoriaDTO categoriaDTO) {
        return null;
    }

    public Categoria update(Categoria cat) {
    }

    public void delete(Integer id) {

    }

    public Categoria findByNome(String nome) {
    }
}
