package org.educational.libraryapp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Author {
  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column
  private String nombre;
  @Column
  private int nacimiento;
  @Column
  private int muerte;
  @Column
  private int anios_vividos;
  @OneToMany(mappedBy = "author")
  private List<Book> libros;

  public Author() {}

  public Author(AuthorData authorData) {
    this.nombre = authorData.nombre();
    this.nacimiento = authorData.nacimiento();
    this.muerte = authorData.muerte();
    this.anios_vividos = muerte - nacimiento;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getNacimiento() {
    return nacimiento;
  }

  public List<Book> getLibros() {
    return libros;
  }

  public void setLibros(List<Book> libros) {
    this.libros = libros;
  }

  public void setNacimiento(int nacimiento) {
    this.nacimiento = nacimiento;
  }

  public int getMuerte() {
    return muerte;
  }

  public void setMuerte(int muerte) {
    this.muerte = muerte;
  }

  public int getAnios_vividos() {
    return anios_vividos;
  }

  public void setAnios_vividos(int anios_vividos) {
    this.anios_vividos = anios_vividos;
  }

  @Override
  public String toString() {
    return  "NOMBRE: "     + nombre        + "\n" +
            "NACIMIENTO: " + nacimiento    + "\n" +
            "MUERTE: "     + muerte        + "\n" +
            "VIVIDOS: "    + anios_vividos + "\n" ;
  }
}
