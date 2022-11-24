package librarymanagement.service.impl;

import librarymanagement.common.exception.ApiException;
import librarymanagement.common.exception.code.AuthorErrorCode;
import librarymanagement.domain.entity.Author;
import librarymanagement.domain.request.AuthorRequest;
import librarymanagement.domain.request.AuthorSearch;
import librarymanagement.domain.response.AuthorDetail;
import librarymanagement.domain.response.AuthorResponse;
import librarymanagement.repository.AuthorRepository;
import librarymanagement.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    public Long addAuthor(AuthorRequest authorRequest) {
        Author author = authorRequest.toEntity(authorRequest);
        authorRepository.save(author);

        return author.getId();
    }

    @Override
    public Page<AuthorResponse> getAuthors(AuthorSearch authorSearch, Pageable pageable) {
        Page<Author> result = authorRepository.findAuthor(authorSearch, pageable);

        return result.map(AuthorResponse::toDto);
    }

    @Override
    public AuthorDetail getAuthor(Long authorId) {
        Author author = authorRepository.findByIdDetail(authorId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(AuthorErrorCode.NOT_FOUND_ID.getMessage())
                        .errorCode(AuthorErrorCode.NOT_FOUND_ID.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        return AuthorDetail.toDto(author);
    }

    @Override
    @Transactional
    public Long deleteAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(AuthorErrorCode.NOT_FOUND_ID.getMessage())
                        .errorCode(AuthorErrorCode.NOT_FOUND_ID.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
        authorRepository.delete(author);

        return author.getId();
    }

    @Override
    @Transactional
    public Long modifyAuthor(Long authorId, String authorName) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(AuthorErrorCode.NOT_FOUND_ID.getMessage())
                        .errorCode(AuthorErrorCode.NOT_FOUND_ID.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        Author.changeAuthorName(author,authorName);

        return author.getId();
    }
}
