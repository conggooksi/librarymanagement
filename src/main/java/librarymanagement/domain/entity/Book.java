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

    @Column(name = "book_title")
    private String title;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookAuthor> bookAuthorList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    private String bookClassificationNumber;

    @Lob
    private String introduction;

    private int price;

    @Builder(builderMethodName = "bookBuilder", builderClassName = "bookBuilder")
    public Book(Long id, String title, List<BookAuthor> bookAuthorList, Publisher publisher, String bookClassificationNumber, String introduction, int price) {
        this.id = id;
        this.title = title;
        this.bookAuthorList = bookAuthorList;
        this.publisher = publisher;
        this.bookClassificationNumber = bookClassificationNumber;
        this.introduction = introduction;
        this.price = price;
    }
}
