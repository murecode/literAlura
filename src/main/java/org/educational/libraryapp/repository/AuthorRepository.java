package org.educational.libraryapp.repository;

import org.educational.libraryapp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
  List<Author> findByNacimiento(int año);
}
