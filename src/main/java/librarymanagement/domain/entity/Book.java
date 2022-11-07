package librarymanagement.domain.entity;

import librarymanagement.domain.request.BookRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor
public class Book extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String title;

    private String author;

    private String publisher;

    private String bookClassificationNumber;

    @Lob
    private String introduction;

    private int price;

    @Builder(builderMethodName = "bookVoBuilder", builderClassName = "bookVoBuilder")
    public Book(long bookId, String title, String author, String publisher, String bookClassificationNumber, String introduction, int price) {
        this.id = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.bookClassificationNumber = bookClassificationNumber;
        this.introduction = introduction;
        this.price = price;
    }

    public static Book toEntity(BookRequest bookRequest) {
        return bookVoBuilder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .publisher(bookRequest.getPublisher())
                .bookClassificationNumber(bookRequest.getBookClassificationNumber())
                .introduction(bookRequest.getIntroduction())
                .price(bookRequest.getPrice())
                .build();
    }

}
