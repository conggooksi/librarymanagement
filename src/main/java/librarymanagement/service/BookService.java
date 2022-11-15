package librarymanagement.service;

import librarymanagement.domain.entity.Book;
import librarymanagement.domain.request.BookRequest;
import librarymanagement.domain.request.BookSearch;
import librarymanagement.domain.response.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Page<BookResponse> getBooks(BookSearch bookSearch, Pageable pageable);

    Long deleteBook(Long bookId);
}
