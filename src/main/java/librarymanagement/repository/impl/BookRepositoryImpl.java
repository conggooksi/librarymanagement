package librarymanagement.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import librarymanagement.domain.entity.Book;
import librarymanagement.domain.response.BookResponse;
import librarymanagement.repository.BookCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static librarymanagement.domain.entity.QAuthor.author;
import static librarymanagement.domain.entity.QBook.book;
import static librarymanagement.domain.entity.QBookAuthor.bookAuthor;
import static librarymanagement.domain.entity.QPublisher.publisher;

public class BookRepositoryImpl extends QuerydslRepositorySupport implements BookCustomRepository {

    private final JPAQueryFactory queryFactory;

    public BookRepositoryImpl(EntityManager em) {
        super(Book.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

}
