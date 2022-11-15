package librarymanagement.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import librarymanagement.domain.entity.Book;
import librarymanagement.domain.request.BookSearch;
import librarymanagement.repository.BookCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static librarymanagement.domain.entity.QBook.book;

public class BookRepositoryImpl extends QuerydslRepositorySupport implements BookCustomRepository {

    private final JPAQueryFactory queryFactory;

    public BookRepositoryImpl(EntityManager em) {
        super(Book.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Book> findBook(BookSearch bookSearch, Pageable pageable) {
        JPAQuery<Book> jpaQuery = queryFactory.select(book)
                .from(book)
                .where(bookIdEq(bookSearch.getId()),
                        bookTitleContain(bookSearch.getTitle()),
                        bookClassificationNumberEq(bookSearch.getBookClassificationNumber()),
                        bookMinPrice(bookSearch.getMinPrice()),
                        bookMaxPrice(bookSearch.getMaxPrice()));

        List<Book> result = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
        return PageableExecutionUtils.getPage(result, pageable, jpaQuery::fetchCount);
    }

    private BooleanExpression bookIdEq(Long id) {
        return id != null && id > 0 ? book.id.eq(id) : null;
    }

    private BooleanExpression bookTitleContain(String title) {
        return title != null ? book.title.contains(title) : null;
    }

    private BooleanExpression bookClassificationNumberEq(String bookClassificationNumber) {
        return bookClassificationNumber != null && !"".equals(bookClassificationNumber) ? book.bookClassificationNumber.eq(bookClassificationNumber) : null;
    }

    private BooleanExpression bookMinPrice(Integer minPrice) {
        return minPrice != null ? book.price.gt(minPrice) : null;
    }

    private BooleanExpression bookMaxPrice(Integer maxPrice) {
        return maxPrice != null ? book.price.lt(maxPrice) : null;
    }
}
