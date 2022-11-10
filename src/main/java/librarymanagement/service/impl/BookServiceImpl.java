package librarymanagement.service.impl;

import librarymanagement.domain.entity.Book;
import librarymanagement.domain.request.BookRequest;
import librarymanagement.domain.response.BookResponse;
import librarymanagement.repository.AuthorRepository;
import librarymanagement.repository.BookCustomRepository;
import librarymanagement.repository.BookRepository;
import librarymanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

}
