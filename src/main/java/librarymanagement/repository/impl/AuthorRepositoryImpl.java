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

import static librarymanagement.domain.entity.QAuthor.author;

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

    private BooleanExpression authorNameEq(String authorName) {
        return authorName != null ? author.name.eq(authorName) : null; //공백처리
    }

    private BooleanExpression authorIdEq(Long authorId) {
        return authorId != null && authorId > 0 ? author.id.eq(authorId) : null;
    }
}
