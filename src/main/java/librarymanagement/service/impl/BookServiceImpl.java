package librarymanagement.service.impl;

import librarymanagement.domain.request.BookRequest;
import librarymanagement.repository.AuthorRepository;
import librarymanagement.repository.BookRepository;
import librarymanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    @Override
    public void registBook(BookRequest bookRequest) {

    }
}
