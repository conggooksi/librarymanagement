package librarymanagement.repository;

import librarymanagement.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface AuthorRepository extends CrudRepository<Author, Long>, AuthorCustomRepository {

}
