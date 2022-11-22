package librarymanagement.controller;

import librarymanagement.common.exception.ApiException;
import librarymanagement.common.exception.code.AuthorErrorCode;
import librarymanagement.common.result.ResponseHandler;
import librarymanagement.domain.entity.Author;
import librarymanagement.domain.request.AuthorRequest;
import librarymanagement.domain.request.AuthorSearch;
import librarymanagement.domain.response.AuthorDetail;
import librarymanagement.domain.response.AuthorResponse;
import librarymanagement.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("")
    public ResponseEntity<?> addAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
        Long authorId = authorService.addAuthor(authorRequest);
        return ResponseHandler.generate()
                .data(authorId)
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("")
    public ResponseEntity<?> getAuthors(AuthorSearch authorSearch, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<AuthorResponse> authorList = authorService.getAuthors(authorSearch, pageable);
        return ResponseHandler.generate()
                .data(authorList)
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/{author_id}")
    public ResponseEntity<?> getAuthor(@PathVariable (value = "author_id") Long authorId){
        AuthorDetail author = authorService.getAuthor(authorId);

        return ResponseHandler.generate()
                .data(author)
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{author_id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable(value = "author_id") Long authorId) {
        Long deletedAuthorId = authorService.deleteAuthor(authorId);
        return ResponseHandler.generate()
                .data(deletedAuthorId)
                .status(HttpStatus.OK)
                .build();
    }

    @PatchMapping("/{author_id}")
    public ResponseEntity<?> modifyAuthor(@PathVariable(value = "author_id") Long authorId, @RequestBody @Valid String authorName) {
        Long author = authorService.modifyAuthor(authorId, authorName);

        return ResponseHandler.generate()
                .data(author)
                .status(HttpStatus.OK)
                .build();

    }
}
