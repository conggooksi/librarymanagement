package librarymanagement.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import librarymanagement.domain.entity.Author;
import librarymanagement.domain.entity.QAuthor;
import librarymanagement.domain.request.AuthorSearch;
import librarymanagement.domain.response.AuthorDetail;
import librarymanagement.domain.response.BookDetail;
import librarymanagement.domain.response.BookResponse;
import librarymanagement.repository.AuthorCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.types.Projections.list;
import static librarymanagement.domain.entity.QAuthor.*;
import static librarymanagement.domain.entity.QAuthor.author;
import static librarymanagement.domain.entity.QBook.book;
import static librarymanagement.domain.entity.QBookAuthor.bookAuthor;
import static librarymanagement.domain.entity.QPublisher.publisher;

public class AuthorRepositoryImpl extends QuerydslRepositorySupport implements AuthorCustomRepository {

    private final JPAQueryFactory queryFactory;

    public AuthorRepositoryImpl(EntityManager em) {
        super(Author.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Author> findAuthor(AuthorSearch authorSearch, Pageable pageable) {
        JPAQuery<Author> jpaQuery = queryFactory.select(author)
                .from(author)
                .where(authorIdEq(authorSearch.getAuthorId()),
                    authorNameEq(authorSearch.getAuthorName()
                ));

        List<Author> result = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
        return PageableExecutionUtils.getPage(result, pageable, jpaQuery::fetchCount);
    }

    @Override
    public List<AuthorDetail> findByIdDetail(Long authorId) {
        List<AuthorDetail> result = queryFactory.select(Projections.constructor(AuthorDetail.class,
                        author.id, author.authorName,
                        list(Projections.constructor(BookResponse.class,
                                        book.id,
                                        book.bookTitle,
                                        book.bookClassificationNumber,
                                        book.price)),
                        author.createdAt, author.updatedAt))
                .from(author)
                .innerJoin(author.bookAuthorList, bookAuthor)
                .innerJoin(bookAuthor.book, book)
                .where(authorIdEq(authorId))
                .groupBy(author.id, book.id, book.bookTitle, book.bookClassificationNumber, book.price)
                .fetch();

        return result;
    }

    private BooleanExpression authorNameEq(String authorName) {
        return authorName != null ? author.authorName.eq(authorName) : null;
    }

    private BooleanExpression authorIdEq(Long authorId) {
        return authorId != null && authorId > 0 ? author.id.eq(authorId) : null;
    }
}
