package com.sistema.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistema.biblioteca.enums.Tamanho;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String autor;
    private String titulo;
    private String texto;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private Tamanho tamanho;


    public Livro() {

    }

    public Livro(Integer id, String autor, String titulo, String texto, Categoria categoria, Tamanho tamanho) {
        this.id = id;
        this.autor = autor;
        this.titulo = titulo;
        this.texto = texto;
        this.categoria = categoria;
        this.tamanho = tamanho;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }
}
