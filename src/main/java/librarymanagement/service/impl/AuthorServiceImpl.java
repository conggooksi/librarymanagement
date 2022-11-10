package librarymanagement.service.impl;

import librarymanagement.domain.entity.Author;
import librarymanagement.domain.entity.Book;
import librarymanagement.domain.request.AuthorRequest;
import librarymanagement.domain.request.AuthorSearch;
import librarymanagement.domain.response.AuthorDetail;
import librarymanagement.domain.response.AuthorResponse;
import librarymanagement.repository.AuthorRepository;
import librarymanagement.service.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public void addAuthor(AuthorRequest authorRequest) {
        Author author = authorRequest.toEntity(authorRequest);
        authorRepository.save(author);
    }

    @Override
    public Page<AuthorResponse> getAuthors(AuthorSearch authorSearch, Pageable pageable) {
        Page<Author> result = authorRepository.findAuthor(authorSearch, pageable);

        return result.map(AuthorResponse::toDto);
    }

    @Override
    public AuthorDetail getAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new RuntimeException());
        return AuthorDetail.toDto(author);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new RuntimeException());
        authorRepository.delete(author);
    }
}
