package org.educational.libraryapp.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name="libros")
public class Book {
  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(unique = true)
  private String titulo;
  @Transient
  private String autor;
  @Column
  private String idiomas;
  @Column
  private int descargas;

  public Book(String titulo, String autor, String idiomas, int descargas) {
    this.titulo = titulo;
    this.autor = autor;
    this.idiomas = idiomas;
    this.descargas = descargas;
  }


  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public long getId() {
    return id;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public String getIdiomas() {
    return idiomas;
  }

  public void setIdiomas(String idiomas) {
    this.idiomas = idiomas;
  }

  public int getDescargas() {
    return descargas;
  }

  public void setDescargas(int descargas) {
    this.descargas = descargas;
  }

  @Override
  public String toString() {
    return  "TITULO: '" + titulo + '\'' + "\n" +
            "AUTOR: " + autor + "\n" +
            "IDIOMA: '" + idiomas + '\'' + "\n" +
            "DESCARGAS: " + descargas + "\n"
            ;
  }
}
