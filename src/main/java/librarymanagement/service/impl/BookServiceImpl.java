package librarymanagement.service.impl;

import librarymanagement.common.exception.ApiException;
import librarymanagement.common.exception.code.AuthorErrorCode;
import librarymanagement.common.exception.code.BookErrorCode;
import librarymanagement.common.exception.code.PublisherErrorCode;
import librarymanagement.domain.entity.Author;
import librarymanagement.domain.entity.Book;
import librarymanagement.domain.entity.BookAuthor;
import librarymanagement.domain.entity.Publisher;
import librarymanagement.domain.request.BookRequest;
import librarymanagement.domain.request.BookSearch;
import librarymanagement.domain.response.BookResponse;
import librarymanagement.repository.AuthorRepository;
import librarymanagement.repository.BookAuthorRepository;
import librarymanagement.repository.BookRepository;
import librarymanagement.repository.PublisherRepository;
import librarymanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final BookAuthorRepository bookAuthorRepository;

    @Override
    public Page<BookResponse> getBooks(BookSearch bookSearch, Pageable pageable) {
        Page<Book> searchedBook = bookRepository.findBook(bookSearch, pageable);

        return searchedBook.map(BookResponse::toSearch);
    }

    @Override
    @Transactional
    public Long deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(BookErrorCode.NOT_FOUND_ID.getMessage())
                        .errorCode(BookErrorCode.NOT_FOUND_ID.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());
        bookRepository.delete(book);

        return book.getId();
    }

    @Override
    @Transactional
    public Long addBook(BookRequest bookRequest) {
        Publisher publisherId = publisherRepository.findById(bookRequest.getPublisherId()).orElseThrow(
                () -> ApiException.builder()
                        .errorMessage(PublisherErrorCode.NOT_FOUND_ID.getMessage())
                        .errorCode(PublisherErrorCode.NOT_FOUND_ID.getCode())
                        .status(HttpStatus.BAD_REQUEST)
                        .build());

        Book book = bookRequest.toEntity(bookRequest, publisherId);
        bookRepository.save(book);

        List<BookAuthor> bookAuthorList = bookRequest.getAuthorIds().stream().map(kid -> {
            Author author = authorRepository.findById(kid).orElseThrow(
                    () -> ApiException.builder()
                            .errorMessage(AuthorErrorCode.NOT_FOUND_ID.getMessage())
                            .errorCode(AuthorErrorCode.NOT_FOUND_ID.getCode())
                            .status(HttpStatus.BAD_REQUEST)
                            .build());
            return BookAuthor.addBookAuthor(book, author);}).collect(Collectors.toList());

        bookAuthorRepository.saveAll(bookAuthorList);

        return book.getId();
    }
}
