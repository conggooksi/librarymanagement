package librarymanagement.domain.entity;

import librarymanagement.domain.request.BookRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
@Getter
@NoArgsConstructor
public class Book extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_title")
    private String title;

    @OneToMany(mappedBy = "book")
    private List<BookAuthor> bookAuthorList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
