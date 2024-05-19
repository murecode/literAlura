package org.educational.libraryapp.repository;

import org.educational.libraryapp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

//  List<Author> findByNacimientoGreaterThanEqualAndMuerteLessThanEqualOrMuerteIsNull(int startYear, int endYear);

  @Query("select a from Author a where a.nacimiento >= :startYear and (a.muerte <= :endYear or a.muerte is null)")
  List<Author> findAuthorsByYear(int startYear, int endYear);

}

/*TODO: 1. Retorna registros duplipados, intentar usar DISTINCT*/
