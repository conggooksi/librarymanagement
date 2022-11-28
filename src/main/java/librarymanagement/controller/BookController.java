package librarymanagement.controller;

import librarymanagement.common.result.ResponseHandler;
import librarymanagement.domain.request.BookRequest;
import librarymanagement.domain.request.BookSearch;
import librarymanagement.domain.response.AuthorResponse;
import librarymanagement.domain.response.BookResponse;
import librarymanagement.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("")
    public ResponseEntity<?> addBook(@RequestBody BookRequest bookRequest) {
        Long bookId = bookService.addBook(bookRequest);

        return ResponseHandler.generate()
                .data(bookId)
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("")
    public ResponseEntity<?> getBooks(@RequestBody BookSearch bookSearch, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BookResponse> searchedBooks = bookService.getBooks(bookSearch, pageable);
        return ResponseHandler.generate()
                .data(searchedBooks)
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{book_id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "book_id") Long bookId) {
        Long deletedBookId = bookService.deleteBook(bookId);
        return ResponseHandler.generate()
                .data(deletedBookId)
                .status(HttpStatus.OK)
                .build();
    }
}
