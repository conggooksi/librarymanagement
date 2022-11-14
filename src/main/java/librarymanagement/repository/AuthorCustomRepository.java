package librarymanagement.repository;

import librarymanagement.domain.entity.Author;
import librarymanagement.domain.request.AuthorSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AuthorCustomRepository {

    Page<Author> findAuthor(AuthorSearch authorSearch, Pageable pageable);

}
