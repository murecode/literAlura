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
  private int años_vividos;

  public Author() {}

  public Author(AuthorData authorData) {
    this.nombre = authorData.nombre();
    this.nacimiento = authorData.nacimiento();
    this.muerte = authorData.muerte();
    this.años_vividos = muerte - nacimiento;
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

  public void setNacimiento(int nacimiento) {
    this.nacimiento = nacimiento;
  }

  public int getMuerte() {
    return muerte;
  }

  public void setMuerte(int muerte) {
    this.muerte = muerte;
  }

  public int getAños_vividos() {
    return años_vividos;
  }

  public void setAños_vividos(int años_vividos) {
    this.años_vividos = años_vividos;
  }

  @Override
  public String toString() {
    return "NOMBRE: " + nombre + '\'' + "\n" +
            "NACIMIENTO: " + nacimiento + "\n" +
            "MUERTE: " + muerte + "\n" +
            "VIVIDOS: " + años_vividos + "\n"
            ;
  }
}
