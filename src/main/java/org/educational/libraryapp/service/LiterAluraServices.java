package org.educational.libraryapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.educational.libraryapp.external_services.DataConverterImpl;
import org.educational.libraryapp.external_services.GutendexAPI;
import org.educational.libraryapp.model.Author;
import org.educational.libraryapp.model.AuthorData;
import org.educational.libraryapp.model.Book;
import org.educational.libraryapp.model.ApiResponse;
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

  public List<Book> getAllBooks () throws JsonProcessingException {

    String jsonBooks = apiService.getData(URL_BASE);
    ApiResponse objBooks = converter.jsonToClass(jsonBooks, ApiResponse.class);

    List<Book> books = objBooks.results().stream()
            .map(b -> new Book(
                    b.titulo(),
                    b.autor().stream().map(AuthorData::nombre).findFirst().orElse(null),
                    b.idiomas().stream().findFirst().orElse(null),
                    b.descargas())
            )
            .sorted(Comparator.comparing(Book::getTitulo))
            .toList();

    books.forEach(System.out::println);
    return books;
  }


  public Optional<Book> findBookByTitle(String title) throws JsonProcessingException {

    String book_title = title;

    String jsonBook = apiService.getData(URL_BASE + "?search=" + book_title.replace(" ", "%20"));
    ApiResponse objBook = converter.jsonToClass(jsonBook, ApiResponse.class);

    Optional<Book> bookData = objBook.results().stream()
            .filter(t -> t.titulo().toUpperCase().contains(book_title.toUpperCase()))
            .map(b -> new Book(
                    b.titulo(),
                    b.autor().stream().map(AuthorData::nombre).findFirst().orElse(null),
                    b.idiomas().stream().findFirst().orElse(null),
                    b.descargas())
            )
            .findFirst();

    if (bookData.isPresent()) {
      System.out.println("¡Encontrado!");
      System.out.println(bookData.get().toString());

    } else {
      System.out.println("Ups!, No se encontró el libro");
    }

    return bookData;
  }

  // mostDownloaded() {}

  public Set<Author> getAllAuthors() throws JsonProcessingException {

    String jsonAuthors = apiService.getData(URL_BASE);
    ApiResponse objAuthors = converter.jsonToClass(jsonAuthors, ApiResponse.class);

    Set<Author> autores = objAuthors.results().stream()
            .flatMap(a -> a.autor().stream())
            .map(a -> new Author(a.nombre(), a.nacimiento(), a.muerte()))
            .collect(Collectors.toSet());

    autores.forEach(System.out::println);

    return autores;
  }

  public void getLivingAuthorsByDate(String from, String to) throws JsonProcessingException {
    String yearStart = from;
    String yearEnd = to;

    String jsonAuthors = apiService.getData(URL_BASE + "?author_year_start=" + yearStart + "&author_year_end=" + yearEnd);
    ApiResponse objAuthors = converter.jsonToClass(jsonAuthors, ApiResponse.class);

    List<Author> autores = objAuthors.results().stream()
            .flatMap(a -> a.autor().stream())
            .map(a -> new Author(a.nombre(), a.nacimiento(), a.muerte()))
            .sorted(Comparator.comparing(Author::getAños_vividos).reversed())
            .collect(Collectors.toList());

    autores.forEach(System.out::println);

  }

}
