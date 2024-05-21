package org.educational.libraryapp.model;

import jakarta.persistence.*;

@Entity
@Table(name="libros")
public class Book {
  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(unique = true)
  private String titulo;
  @Column
  private String autor;
  @Column
  @Enumerated(EnumType.STRING)
  private Languages idioma;
  @Column
  private int descargas;
  @ManyToOne
  private Author author;

  public Book() {}

  public Book(BookData bookData) {
    this.titulo = bookData.titulo();
    this.autor = bookData.autor().stream()
            .map(AuthorData::nombre)
            .findFirst()
            .orElse(null)
            .replace(", ", " ");
    this.idioma = Languages.fromString(bookData.idiomas().get(0));
    this.descargas = bookData.descargas();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public Languages getIdiomas() {
    return idioma;
  }

  public void setIdiomas(Languages idiomas) {
    this.idioma = idiomas;
  }

  public int getDescargas() {
    return descargas;
  }

  public void setDescargas(int descargas) {
    this.descargas = descargas;
  }

  @Override
  public String toString() {
    return  "ID: '" + id + '\'' + "\n" +
            "TITULO: '" + titulo + '\'' + "\n" +
            "AUTOR: " + autor + "\n" +
            "IDIOMA: '" + idioma + '\'' + "\n" +
            "DESCARGAS: " + descargas + "\n"
            ;
  }
}
