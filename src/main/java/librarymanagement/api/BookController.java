package librarymanagement.api;

import librarymanagement.service.BookService;
import librarymanagement.domain.request.BookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("")
    public ResponseEntity<?> registBook(@RequestBody BookRequest bookRequest) {
        bookService.registBook(bookRequest);
        return ResponseEntity.ok(null);
    }

//    @GetMapping("/")
//    public ResponseEntity<?> getBookList() {
//
//        result = BookService.getBook();
//
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
}
