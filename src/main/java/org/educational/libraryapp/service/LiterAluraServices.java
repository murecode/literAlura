package org.educational.libraryapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.educational.libraryapp.external_services.DataConverterImpl;
import org.educational.libraryapp.external_services.GutendexAPI;
import org.educational.libraryapp.model.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LiterAluraServices {
  GutendexAPI apiService = new GutendexAPI();
  DataConverterImpl converter = new DataConverterImpl();
  String URL_BASE = "https://gutendex.com/books/";


  public Set<Book> getAllBooks() throws JsonProcessingException {

    String jsonBooks = apiService.getData(URL_BASE);
    ApiResponse objBooks = converter.jsonToClass(jsonBooks, ApiResponse.class);

    Set<Book> books = objBooks.results().stream()
            .map(b -> new Book(b))
            .sorted(Comparator.comparing(Book::getId))
            .collect(Collectors.toSet());

    books.forEach(System.out::println);
    return books;
  }

  public static String sanitizeString(String input) {
    // Reemplazar todos los caracteres que no sean letras ni espacios con una cadena vacía
    // También reemplazar comillas simples explícitamente
    return input.replaceAll("[^\\p{L}\\s]", "").replace("'", "").trim();
  }


  public Optional<Book> findBookByTitle(String title) throws JsonProcessingException {

    String book_title = title;

    String jsonBook = apiService.getData(URL_BASE + "?search=" + book_title.replace(" ", "%20"));
    ApiResponse objBook = converter.jsonToClass(jsonBook, ApiResponse.class);

    Optional<Book> bookData = objBook.results().stream()
            .filter(t -> t.titulo().toUpperCase().contains(book_title.toUpperCase().replace("'", "")))
            .map(b -> new Book(b))
            .findFirst();

    if (bookData.isPresent()) {
      System.out.println("¡Encontrado!");
      System.out.println(bookData.get().toString());

    } else {
      System.out.println("Ups!, No se encontró el libro");
    }

    return bookData;
  }

  public void mostDownloaded() throws JsonProcessingException {

    String jsonBooks = apiService.getData(URL_BASE);
    ApiResponse objBook = converter.jsonToClass(jsonBooks, ApiResponse.class);

    List<Book> books = objBook.results().stream()
            .sorted(Comparator.comparing(BookData::descargas).reversed())
            .map(b -> new Book(b))
            .limit(10)
            .collect(Collectors.toList());

    System.out.println(">>> TOP 10 EN DESCARGAS <<<");
    books.forEach(System.out::println);

  }

  public Set<Author> getAllAuthors() throws JsonProcessingException {

    String jsonAuthors = apiService.getData(URL_BASE);
    ApiResponse objAuthors = converter.jsonToClass(jsonAuthors, ApiResponse.class);

    Set<Author> autores = objAuthors.results().stream()
            .flatMap(a -> a.autor().stream())
            .map(a -> new Author(a))
            .collect(Collectors.toSet());

//    autores.forEach(System.out::println);

    return autores;
  }

/*  public void getLivingAuthorsByDate(String from, String to) throws JsonProcessingException {
    String yearStart = from;
    String yearEnd = to;

    String jsonAuthors = apiService.getData(URL_BASE + "?author_year_start=" + yearStart + "&author_year_end=" + yearEnd);
    ApiResponse objAuthors = converter.jsonToClass(jsonAuthors, ApiResponse.class);

    List<Author> autores = objAuthors.results().stream()
            .flatMap(a -> a.autor().stream())
            .map(a -> new Author(a))
            .sorted(Comparator.comparing(Author::getAños_vividos).reversed())
            .collect(Collectors.toList());

    autores.forEach(System.out::println);

  }*/

}
