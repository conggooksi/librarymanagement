package librarymanagement.service.impl;

import librarymanagement.domain.entity.Book;
import librarymanagement.domain.request.BookSearch;
import librarymanagement.domain.response.BookResponse;
import librarymanagement.repository.BookRepository;
import librarymanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Page<BookResponse> getBooks(BookSearch bookSearch, Pageable pageable) {
        Page<Book> searchedBook = bookRepository.findBook(bookSearch, pageable);

        return searchedBook.map(BookResponse::toSearch);
    }
}
