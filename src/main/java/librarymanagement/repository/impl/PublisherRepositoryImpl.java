package librarymanagement.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import librarymanagement.domain.entity.Publisher;
import librarymanagement.domain.request.PublisherSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static librarymanagement.domain.entity.QBook.book;
import static librarymanagement.domain.entity.QPublisher.publisher;

public class PublisherRepositoryImpl extends QuerydslRepositorySupport implements PublisherCustomRepository {

    private final JPAQueryFactory queryFactory;

    public PublisherRepositoryImpl(EntityManager em) {
        super(Publisher.class);
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Publisher> findPublisher(PublisherSearch publisherSearch, Pageable pageable) {
        JPAQuery<Publisher> jpaQuery = queryFactory.select(publisher)
                .from(publisher)
                .where(publisherIdEq(publisherSearch.getPublisherId()),
                        publisherNameContains(publisherSearch.getPublisherName()));

        List<Publisher> result = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
        return PageableExecutionUtils.getPage(result, pageable, jpaQuery::fetchCount);
    }

    @Override
    public Optional<Publisher> findByIdDetail(Long publisherId) {
        return Optional.ofNullable(queryFactory.select(publisher)
                .from(publisher)
                .innerJoin(publisher.bookList, book)
                .fetchJoin()
                .where(publisher.id.eq(publisherId))
                .fetchOne());
    }

    private BooleanExpression publisherNameContains(String publisherName) {
        return publisherName != null ? publisher.name.contains(publisherName) : null;
    }

    private BooleanExpression publisherIdEq(Long publisherId) {
        return publisherId != null && publisherId > 0 ? publisher.id.eq(publisherId) : null;
    }
}
