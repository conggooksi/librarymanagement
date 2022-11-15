package librarymanagement.repository;

import librarymanagement.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long>, BookCustomRepository {

}
