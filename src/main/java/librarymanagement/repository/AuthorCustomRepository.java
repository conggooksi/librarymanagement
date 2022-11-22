package librarymanagement.repository;

import librarymanagement.domain.entity.Author;
import librarymanagement.domain.request.AuthorSearch;
import librarymanagement.domain.response.AuthorDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface AuthorCustomRepository {

    Page<Author> findAuthor(AuthorSearch authorSearch, Pageable pageable);

    Optional<Author> findByIdDetail(Long authorId);
}
