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

  public void showMenu() throws JsonProcessingException {

    var opcion = -1;
    while (opcion != 0) {

      String menu_options = """
          ==========LiterAlura============
          1. Listar todos los libros
          2. Buscar libro por título
          3. Listar todos los autores
          4. Buscar autores vivos en el año
          5. Poblar bases de datos
          6. 
          
          0. Salir

          ================================
          """;
      System.out.println(menu_options);

      opcion = input.nextInt();

      switch (opcion) {

        case 1:
          getAllBooks();
          break;

        case 2:
          findBookInDataBase();
          break;

        case 3:
          getAllAuthors();
          break;

        case 4:
          findLivingAuthorsByYear();
          break;

        case 5:
          saveAllBooks();
          saveAllAuthors();
          break;

        case 6:
          break;

        case 0:
          System.out.println(">>> Gracias por usar esta App <<<");
          break;


      }//end switch

    }
  }

  private void saveAllBooks() throws JsonProcessingException {
    Set<Book> books = services.getAllBooks();
    bookRepo.saveAll(books);
  }

  private void saveAllAuthors() throws JsonProcessingException {
    Set<Author> authors = services.getAllAuthors();
    authorRepo.saveAll(authors);
  }

  private void getAllBooks(){
    List<Book> books = bookRepo.findAll();

    if (!books.isEmpty()) {
      System.out.println(">>> Lista de autores: <<<");
      books.forEach(System.out::println);
    } else {
      System.out.println(">>> Sin resultados <<<");
    }
  }

  private void findBookInDataBase() {
    System.out.println(">>> Ingresa el título del libro <<<");
    var titulo = input.next();
    Optional<Book> libroBuscado = bookRepo.findByTituloContainsIgnoreCase(titulo);

    if (libroBuscado.isPresent()) {
      System.out.println(">>> Encontrado: <<<");
      System.out.println(libroBuscado.get());
    } else {
      System.out.println(">>> Libro no encontrado <<<");
    }

    /*TODO:
       1. Se esta teniendo problema con la busqueda debido a que algunos titulios
          se componen con palabras reservadas de SQL. ej: "IN"
    */

  }

  private void getAllAuthors(){
    List<Author> authors = authorRepo.findAll();

    if (!authors.isEmpty()) {
      System.out.println(">>> Lista de autores: <<<");
      authors.forEach(System.out::println);
    } else {
      System.out.println(">>> Sin resultados <<<");
    }
  }

  private void findLivingAuthorsByYear() {
    System.out.println(">>> Ingrese año <<<");
    int from = input.nextInt();
    int to = from + 90;

    List<Author> authors = authorRepo
            .findAuthorsByYear(from, to);

    if (!authors.isEmpty()) {
      System.out.println(">>> Los autores vivos en el año " + from + " son: <<<");
      authors.forEach(System.out::println);
    } else {
      System.out.println(">>> No se encontró ningun autor vivo en ese año <<<");
    }



  }



}


