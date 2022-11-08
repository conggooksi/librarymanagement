package librarymanagement.service;

import librarymanagement.domain.entity.Author;
import librarymanagement.domain.request.AuthorRequest;

import java.util.List;

public interface AuthorService {
    void addAuthor(AuthorRequest authorRequest);

    List<Author> getAuthor();
}
