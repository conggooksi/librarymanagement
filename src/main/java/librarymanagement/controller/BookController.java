package librarymanagement.controller;

import librarymanagement.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BookController {

    private final BookService bookService;


}
