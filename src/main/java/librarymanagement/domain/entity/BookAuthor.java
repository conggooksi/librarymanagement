package librarymanagement.domain.entity;

import librarymanagement.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookAuthor extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_author_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Builder(builderMethodName = "createBookAuthor", builderClassName = "createBookAuthor")
    public BookAuthor(Book book, Author author) {
        this.book = book;
        this.author = author;
    }

    public static BookAuthor addBookAuthor(Book book, Author author) {
        return createBookAuthor()
                .book(book)
                .author(author)
                .build();
    }
}
