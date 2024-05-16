package org.educational.libraryapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.educational.libraryapp.model.Author;
import org.educational.libraryapp.model.Book;
import org.educational.libraryapp.repository.AuthorRepository;
import org.educational.libraryapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Principal {
  Scanner input = new Scanner(System.in);
  LiterAluraServices services = new LiterAluraServices();
  @Autowired
  private BookRepository bookRepo;
  @Autowired
  private AuthorRepository authorRepo;
  public Principal(
          BookRepository bookRepository,
          AuthorRepository authorRepository) {
    this.bookRepo = bookRepository;
    this.authorRepo = authorRepository;
  }

  public void showMenu() {

    var opcion = -1;
    while (opcion != 0) {

      String menu_options = """
          ==========LiterAlura============
          1. Buscar libro por título
          2. Listar todos los libros
          3. Listar autores
          4. Buscar autores vivos durante...
          5. Buscar libro en Base de Datos
          6. Listar autores vivos en el año...
          
          0. Salir

          ================================
          """;
      System.out.println(menu_options);
      opcion = input.nextInt();
      input.nextLine();

      Scanner input = new Scanner(System.in);

      switch (opcion) {
        case 1:
          String title = input.next();
          try {
            System.out.println("Buscando...");
            services.findBookByTitle(title);
          } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
          }
          break;

        case 2:
          try {
            System.out.println("Recopilando libros...");
            services.getAllBooks();
            saveAllBooks();
          } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
          }
          break;

        case 3:
          try {
            System.out.println("Recopilando autores...");
            services.getAllAuthors();
            saveAllAuthors();
          } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
          }
          break;

        case 4:
          System.out.println("Desde el año:");
          String from = input.next();
          System.out.println("hasta el año:");
          String to = input.next();
          try {
            services.getLivingAuthorsByDate(from, to);
          } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
          }
          break;
        case 5:
          findBookInDataBase();
          break;
        case 6:
          findLivingAuthorsByYear();
          break;

        case 0:
          System.out.println("Gracias por usar esta App");
          break;


      }//end switch

    }
  }

  private void saveAllBooks() throws JsonProcessingException {
    List<Book> books = services.getAllBooks();
    bookRepo.saveAll(books);
  }

  private void saveAllAuthors() throws JsonProcessingException {
    Set<Author> authors = services.getAllAuthors();
    authorRepo.saveAll(authors);
  }

  private void findBookInDataBase() {
    System.out.println("Ingresa el título del libro");
    String titulo = input.next();
    Optional<Book> libroBuscado = bookRepo.findByTituloContainsIgnoreCase(titulo);

    if (libroBuscado.isPresent()) {
      System.out.println("Encontrado: ");
      System.out.println(libroBuscado.get());
    } else {
      System.out.println("Libro no encontrado");
    }

    /*TODO:
    * 1. Se esta teniendo problemas con la busqueda posiblemente los espacios en blnco de los titulos
    */

  }

  private void findLivingAuthorsByYear() {
    System.out.println("Ingresa el año:");
    var anio = input.nextInt();
    List<Author> autores = authorRepo.findByNacimiento(anio);

    if (!autores.isEmpty()) {
      System.out.println("Listado de autores vivos en " + anio + "son");
      autores.forEach(System.out::println);
    } else {
      System.out.println("No se encontró ningun autor vivo en " + anio);
    }



  }



}


