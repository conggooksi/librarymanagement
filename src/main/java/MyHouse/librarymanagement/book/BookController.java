package MyHouse.librarymanagement.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/regist/book")
    public ResponseEntity<?> registBook(@RequestBody BookRequest bookRequest) {
        return null;
    }
}
