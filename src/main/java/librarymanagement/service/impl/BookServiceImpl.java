package librarymanagement.service.impl;

import librarymanagement.domain.entity.Book;
import librarymanagement.repository.BookRepository;
import librarymanagement.domain.request.BookRequest;
import librarymanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public void registBook(BookRequest bookRequest) {

        Book book = Book.toEntity(bookRequest);

        bookRepository.save(book);
    }
}
