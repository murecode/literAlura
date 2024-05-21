package org.educational.libraryapp.repository;

import org.educational.libraryapp.model.Book;
import org.educational.libraryapp.model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
  Book findByTituloContainsIgnoreCase(String titulo);

  @Query("select b from Book b where b.idioma = :idioma")
  List<Book> findByIdioma(@Param("idioma") Languages idioma);

}
