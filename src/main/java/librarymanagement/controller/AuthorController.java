package librarymanagement.controller;

import librarymanagement.domain.entity.Author;
import librarymanagement.domain.request.AuthorRequest;
import librarymanagement.service.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorController {

    private final AuthorService authorService;


    @PostMapping("")
    public ResponseEntity<?> addAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
        authorService.addAuthor(authorRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAuthor() {
        List<Author> authorList = authorService.getAuthor();


        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }
}
