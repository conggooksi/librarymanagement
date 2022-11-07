package librarymanagement.service;

import librarymanagement.domain.request.BookRequest;

public interface BookService {
    void registBook(BookRequest bookRequest);
}
