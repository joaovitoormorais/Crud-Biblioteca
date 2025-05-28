package com.sistema.biblioteca.dtos;

import com.sistema.biblioteca.models.Categoria;

public class CategoriaDTO {

    private String nome;
    private String descrição;

    public CategoriaDTO() {

    }

    public CategoriaDTO(String nome, String descrição) {
        this.nome = nome;
        this.descrição = descrição;
    }

    public CategoriaDTO(Categoria categoria) {
        this.nome = categoria.getNome();
        this.descrição = categoria.getDescrição();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }
}
