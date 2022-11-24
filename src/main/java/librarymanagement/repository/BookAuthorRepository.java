package librarymanagement.repository;

import librarymanagement.domain.entity.BookAuthor;
import org.springframework.data.repository.CrudRepository;

public interface BookAuthorRepository extends CrudRepository<BookAuthor, Long> {
}
