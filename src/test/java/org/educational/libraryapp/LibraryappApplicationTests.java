package org.educational.libraryapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.educational.libraryapp.external_services.DataConverterImpl;
import org.educational.libraryapp.external_services.GutendexAPI;
import org.educational.libraryapp.model.*;
import org.educational.libraryapp.repository.AuthorRepository;
import org.educational.libraryapp.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LibraryappApplicationTests {

	GutendexAPI apiService = new GutendexAPI();
	DataConverterImpl converter = new DataConverterImpl();
	String URL_BASE = "https://gutendex.com/books/";

	@Test
	void testGetAllBooks() throws JsonProcessingException {

		String jsonBooks = apiService.getData(URL_BASE);
		var objBooks = converter.jsonToClass(jsonBooks, ApiResponse.class);

		List<Book> books = objBooks.results().stream()
						.map(b -> new Book(
										b.titulo(),
										b.autor().stream().map(AuthorData::nombre).findFirst().orElse(null),
										b.idiomas().stream().findFirst().orElse(null),
										b.descargas())
						)
						.sorted(Comparator.comparing(Book::getTitulo))
						.collect(Collectors.toList());

		System.out.println(jsonBooks);
		books.forEach(System.out::println);

	}
	

	@Test
	void testGetBookByTitle() throws JsonProcessingException {

		String book_title = "Alice";

		String jsonBook = apiService.getData(URL_BASE + "?search=" + book_title.replace(" ", "%20"));
		ApiResponse objBook = converter.jsonToClass(jsonBook, ApiResponse.class);

		Optional<Book> book = objBook.results().stream()
						.filter(t -> t.titulo().toUpperCase().contains(book_title.toUpperCase()))
						.map(b -> new Book(
										b.titulo(),
										b.autor().stream().map(AuthorData::nombre).findFirst().orElse(null),
										b.idiomas().stream().findFirst().orElse(null),
										b.descargas())
						)
						.findFirst();

		if (book.isPresent()) {
			System.out.println(book.get().toString());
		} else {
			System.out.println("Ups!, No se encontró el libro");
		}

		// LOGS
		System.out.println(jsonBook);
		System.out.println(objBook);

	}


	@Test
	void testGetFavoriteDownloads() throws JsonProcessingException {

		String jsonBooks = apiService.getData(URL_BASE);

		ApiResponse objBook = converter.jsonToClass(jsonBooks, ApiResponse.class);
		System.out.println( objBook );

		// Imprime los libros indicando la propiedad
		objBook.results().stream()
						.sorted(Comparator.comparing(BookData::descargas).reversed())
						.limit(3)
						.map(e -> e.titulo())
						.forEach(System.out::println);

	}


	@Test
	void testGetAllAuthors() throws JsonProcessingException {

		String res = apiService.getData(URL_BASE);

		ApiResponse objAuthors = converter.jsonToClass(res, ApiResponse.class);
		System.out.println(objAuthors);

		// Se usa un Set para evitar almacenar elementos repetidos
		Set<Author> autores = objAuthors.results().stream()
						.flatMap(a -> a.autor().stream())
						.map(a -> new Author(a.nombre(), a.nacimiento(), a.muerte()))
						.collect(Collectors.toSet());

		autores.forEach(System.out::println);

//		System.out.println("[2]" + autores.get(2));

	}

	@Test
	void testGetLivingAuthorsByDate() throws JsonProcessingException {
		String yearStart = "1800";
		String yearEnd = "1900";
		
		String jsonAuthors = apiService.getData(URL_BASE + "?author_year_start=" + yearStart + "&author_year_end=" + yearEnd);
		ApiResponse objAuthors = converter.jsonToClass(jsonAuthors, ApiResponse.class);

		List<Author> autores_ls = objAuthors.results().stream()
						.flatMap(a -> a.autor().stream())
						.map(a -> new Author(a.nombre(), a.nacimiento(), a.muerte()))
						.sorted(Comparator.comparing(Author::getAños_vividos).reversed())
						.collect(Collectors.toList());

		// LOGS
		System.out.println(jsonAuthors);
		System.out.println(objAuthors);
		autores_ls.forEach(System.out::println);

	}


}

// Docs

/* flatMap(),
*/
