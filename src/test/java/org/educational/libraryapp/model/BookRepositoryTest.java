package org.educational.libraryapp.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.educational.libraryapp.repository.BookRepository;
import org.educational.libraryapp.service.LiterAluraServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class BookRepositoryTest {
  @Autowired
  BookRepository bookRepository;
  @Autowired
  LiterAluraServices services;

  @Test
  void testSaveBook() throws JsonProcessingException {

//    Optional<Book> bookdata = services.findBookByTitle("Vikings");
//
//    if (bookdata.isPresent()) {
//      Book book = bookdata.get();
//      Book newbook = new Book();
//      newbook.setTitulo(book.getTitulo());
//      newbook.setAutor(book.getAutor());
//      newbook.setIdiomas(book.getIdiomas());
//      newbook.setDescargas(book.getDescargas());
//      bookRepository.save(newbook);
//    } else {
//      // Manejo si el libro no está presente en el Optional
//      // Por ejemplo:
//      System.out.println("El libro no fue encontrado");
//    }


  }

}