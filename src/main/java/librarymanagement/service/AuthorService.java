package librarymanagement.service;

import librarymanagement.domain.entity.Author;
import librarymanagement.domain.request.AuthorRequest;
import librarymanagement.domain.request.AuthorSearch;
import librarymanagement.domain.response.AuthorDetail;
import librarymanagement.domain.response.AuthorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    Long addAuthor(AuthorRequest authorRequest);

    Page<AuthorResponse> getAuthors(AuthorSearch authorSearch, Pageable pageable);

    AuthorDetail getAuthor(Long authorId);

    Long deleteAuthor(Long authorId);

    Long modifyAuthor(Long authorId, String authorName);
}
