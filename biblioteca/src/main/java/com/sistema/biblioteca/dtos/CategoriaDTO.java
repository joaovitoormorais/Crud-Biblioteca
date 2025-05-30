package com.sistema.biblioteca.dtos;

import com.sistema.biblioteca.models.Categoria;

public class CategoriaDTO {

    private Integer id;
    private String nome;
    private String descrição;

    public CategoriaDTO() {

    }

    public CategoriaDTO( Integer id, String nome, String descrição) {
        this.id = id;
        this.nome = nome;
        this.descrição = descrição;
    }

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
