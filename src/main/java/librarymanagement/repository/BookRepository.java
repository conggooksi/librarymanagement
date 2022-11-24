package librarymanagement.repository;

import librarymanagement.domain.entity.Book;
import librarymanagement.repository.impl.BookCustomRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long>, BookCustomRepository {

}
