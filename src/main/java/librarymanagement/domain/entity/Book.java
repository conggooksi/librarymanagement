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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisherId;

    @Column(name = "book_title")
    private String bookTitle;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookAuthor> bookAuthorList = new ArrayList<>();

    private String bookClassificationNumber;

    @Lob
    @Column(name = "introduction")
    private String introduction;

    private Integer price;

    @Builder(builderMethodName = "bookBuilder", builderClassName = "bookBuilder")
    public Book(Long id, String title, List<BookAuthor> bookAuthorList, Publisher publisher, String bookClassificationNumber, String introduction, Integer price) {
        this.id = id;
        this.bookTitle = title;
        this.bookAuthorList = bookAuthorList;
        this.publisherId = publisher;
        this.bookClassificationNumber = bookClassificationNumber;
        this.introduction = introduction;
        this.price = price;
    }

    @Builder(builderMethodName = "createBuilder", builderClassName = "createBuilder")
    public Book(Long id, String bookTitle, Publisher publisherId, String bookClassificationNumber, String introduction, int price) {
        this.id = id;
        this.publisherId = publisherId;
        this.bookTitle = bookTitle;
        this.bookClassificationNumber = bookClassificationNumber;
        this.introduction = introduction;
        this.price = price;
    }
}
