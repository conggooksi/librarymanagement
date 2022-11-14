package librarymanagement.controller;

import librarymanagement.common.result.ResponseHandler;
import librarymanagement.domain.request.AuthorRequest;
import librarymanagement.domain.request.AuthorSearch;
import librarymanagement.domain.response.AuthorResponse;
import librarymanagement.service.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("")
    public ResponseEntity<?> addAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
        authorService.addAuthor(authorRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAuthors(AuthorSearch authorSearch, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<AuthorResponse> authorList = authorService.getAuthors(authorSearch, pageable);
        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @GetMapping("/{author_id}")
    public ResponseEntity<?> getAuthor(@PathVariable(value = "author_id") Long authorId){
        return ResponseEntity.ok(authorService.getAuthor(authorId));
    }

    @GetMapping("/v2/{author_id}")
    public ResponseEntity<?> getAuthorV2(@PathVariable(value = "author_id") Long authorId){
        return ResponseHandler.generate()
                .data(authorService.getAuthor(authorId))
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{author_id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable(value = "author_id") Long authorId) {
        authorService.deleteAuthor(authorId);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/{author_id}")
    public ResponseEntity<?> modifyAuthor(@PathVariable(value = "author_id") Long authorId, @RequestBody @Valid AuthorRequest authorRequest) {
        authorService.modifyAuthor(authorId, authorRequest);

        return ResponseEntity.ok(null);
        
    }
}
