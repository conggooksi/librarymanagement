package MyHouse.librarymanagement.book.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor
public class BookVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookId;

    private String title;

    private String author;

    private String publisher;

    private int bookClassificationNumber;

    private String introduction;

    private int price;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public BookVo(long bookId, String title, String author, String publisher, int bookClassificationNumber, String introduction, int price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.bookClassificationNumber = bookClassificationNumber;
        this.introduction = introduction;
        this.price = price;
    }
}
