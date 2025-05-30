package com.sistema.biblioteca.services;

import com.sistema.biblioteca.dtos.CategoriaDTO;
import com.sistema.biblioteca.models.Categoria;
import com.sistema.biblioteca.repositories.CategoriaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id) {
     Optional<Categoria> cat = categoriaRepository.findById(id);
     if(cat.isPresent()) {
         return cat.get();
     }

     throw new IllegalArgumentException("Categoria não encontrada.");
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        findByNome(categoria);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria cat) {
        findById(cat.getId());
        findByNome(cat);
        return categoriaRepository.save(cat);
    }

    public void delete(Integer id) {
        findById(id);
        Optional<Categoria> cat = categoriaRepository.findById(id);
        if (!cat.get().getLivros().isEmpty()) {
            throw new DataIntegrityViolationException("Não é possivél excluir uma categoria" +
                    "que possui livros associados");
        }
    }

    private void findByNome(Categoria categoria) {
        Optional<Categoria> cat = categoriaRepository.findByNome(categoria.getNome());
        if (cat.isPresent() && cat.get().getNome().equals(categoria.getNome())) {
            throw new IllegalArgumentException("Já existe uma categoria com esse nome.");
        }
    }

}