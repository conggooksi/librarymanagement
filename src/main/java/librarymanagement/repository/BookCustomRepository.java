package librarymanagement.repository;

import librarymanagement.domain.entity.Book;
import librarymanagement.domain.request.BookSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookCustomRepository {

    Page<Book> findBook(BookSearch bookSearch, Pageable pageable);
}
