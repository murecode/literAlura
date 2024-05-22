package org.educational.libraryapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.educational.libraryapp.model.Author;
import org.educational.libraryapp.model.Book;
import org.educational.libraryapp.model.Languages;
import org.educational.libraryapp.repository.AuthorRepository;
import org.educational.libraryapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

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
          ========== LiterAlura ============
          1. Listar todos los libros
          2. Buscar libro por título
          3. Listar libros por idioma
          ----------------------------------
          4. Listar todos los autores
          5. Buscar autores vivos en el año
          ----------------------------------
          6. Top 10 Libros más descargados
          
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
          findBookByTitle();
          break;

        case 3:
          findBooksByLanguage();
          break;

        case 4:
          getAllAuthors();
          break;

        case 5:
          findLivingAuthorsByYear();
          break;

        case 6:
          services.mostDownloaded();
          break;

        case 7:
          break;

        case 8:
          break;

        case 0:
          System.out.println(">>> Gracias por usar esta App <<<");
          break;

        default:
          System.out.println(">>> Opción no soportada <<<");


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

  private void findBookByTitle() {
    Scanner input_str = new Scanner(System.in);
    System.out.println(">>> Ingresa el título del libro <<<");
    String titulo = input_str.next();
    Optional<Book> libroBuscado = bookRepo.findFirstByTituloContainsIgnoreCase(titulo);

    var x = libroBuscado.orElse(null);

    System.out.println(x.toString());

  }


  private void findBooksByLanguage() {
    Scanner input_str = new Scanner(System.in);
    System.out.println(">>> Ingresa el idioma (Ingles, Frances, Español) <<<");
    String idioma = input_str.next();

    try {
      Languages language = Languages.valueOf(idioma.toUpperCase());
      List<Book> libros = bookRepo.findByIdioma(Languages.valueOf(String.valueOf(language)));

      if (!libros.isEmpty()) {
        var cantidad = libros.stream().collect(Collectors.counting());
        System.out.println( ">>> Total libros en este idioma: " + cantidad + " <<<");
        System.out.println(">>> Lista de libros: <<<");
        libros.forEach(System.out::println);
      } else {
        System.out.println(">>> Vacio: <<<");
      }

    } catch (IllegalArgumentException e) {
      System.out.println(">>> Idioma no soportado <<<");
    }
  }


  private void getAllAuthors(){
    List<Author> authors = authorRepo.getAllAuthors();

    if (!authors.isEmpty()) {
      System.out.println(">>> Lista de autores: <<<");
      authors.forEach(System.out::println);
    } else {
      System.out.println(">>> Sin resultados <<<");
    }
  }

  private void findLivingAuthorsByYear() {
    System.out.println(">>> Ingrese año (desde) <<<");
    int from = input.nextInt();
    System.out.println(">>> Ingrese año (hasta) <<<");
    int to = input.nextInt();

    List<Author> authors = authorRepo
            .findAuthorsByYear(from, to);

    if (!authors.isEmpty()) {
      System.out.println(">>> Los autores vivos desde el año " + from + " hasta " + to + " son: <<<");
      authors.forEach(System.out::println);
    } else {
      System.out.println(">>> No se encontró ningun autor vivo en ese año <<<");
    }



  }



}


