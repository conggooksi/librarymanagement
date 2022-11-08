package librarymanagement.service.impl;

import librarymanagement.domain.entity.Author;
import librarymanagement.domain.request.AuthorRequest;
import librarymanagement.repository.AuthorRepository;
import librarymanagement.service.AuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public void addAuthor(AuthorRequest authorRequest) {

        Author author = authorRequest.toEntity(authorRequest);

        authorRepository.save(author);
    }

    @Override
    public List<Author> getAuthor() {
        List<Author> authorList = authorRepository.findAll();

        return authorList;
    }
}
