package librarymanagement.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import librarymanagement.domain.entity.Author;
import librarymanagement.domain.request.AuthorSearch;
import librarymanagement.repository.AuthorCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static librarymanagement.domain.entity.QAuthor.author;
import static librarymanagement.domain.entity.QBook.book;
import static librarymanagement.domain.entity.QBookAuthor.bookAuthor;

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
    public Optional<Author> findByIdDetail(Long authorId) {
        Optional<Author> result = Optional.ofNullable(queryFactory.select(author)
                .from(author)
                .innerJoin(author.bookAuthorList, bookAuthor)
                .fetchJoin()
                .innerJoin(bookAuthor.book, book)
                .fetchJoin()
                .where(author.id.eq(authorId))
                .fetchOne());

        return result;
    }

    private BooleanExpression authorNameEq(String authorName) {
        return authorName != null ? author.authorName.eq(authorName) : null;
    }

    private BooleanExpression authorIdEq(Long authorId) {
        return authorId != null && authorId > 0 ? author.id.eq(authorId) : null;
    }
}
