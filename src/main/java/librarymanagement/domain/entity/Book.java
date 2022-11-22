package librarymanagement.domain.entity;

import librarymanagement.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_title")
    private String bookTitle;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookAuthor> bookAuthorList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher bookPublisher;

    private String bookClassificationNumber;

    @Lob
    @Column(name = "introduction")
    private String bookIntroduction;

    @Column(name = "price")
    private int bookPrice;

    @Builder(builderMethodName = "bookBuilder", builderClassName = "bookBuilder")
    public Book(Long id, String title, List<BookAuthor> bookAuthorList, Publisher publisher, String bookClassificationNumber, String introduction, int price) {
        this.bookId = id;
        this.bookTitle = title;
        this.bookAuthorList = bookAuthorList;
        this.bookPublisher = publisher;
        this.bookClassificationNumber = bookClassificationNumber;
        this.bookIntroduction = introduction;
        this.bookPrice = price;
    }

    @Builder(builderMethodName = "createBuilder", builderClassName = "createBuilder")
    public Book(Long id, String title, Author author, Publisher publisher, String bookClassificationNumber, String introduction, int price) {
        this.bookId = id;
        this.bookTitle = title;

        this.bookPublisher = publisher;
        this.bookClassificationNumber = bookClassificationNumber;
        this.bookIntroduction = introduction;
        this.bookPrice = price;
    }
}
