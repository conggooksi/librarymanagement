package librarymanagement.domain.dto;

import librarymanagement.domain.entity.Book;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {

    private Long id;

    private String title;

    private String author;

    private String publisher;

    private String bookClassificationNumber;

    private String introduction;

    private int price;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public BookDto(long bookId, String title, String author, String publisher, String bookClassificationNumber, String introduction, int price) {
        this.id = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.bookClassificationNumber = bookClassificationNumber;
        this.introduction = introduction;
        this.price = price;
    }

    public static BookDto toDto(Book bookVo) {
        return of()
                .bookId(bookVo.getId())
                .title(bookVo.getTitle())
                .author(bookVo.getAuthor())
                .publisher(bookVo.getPublisher())
                .bookClassificationNumber(bookVo.getBookClassificationNumber())
                .introduction(bookVo.getIntroduction())
                .price(bookVo.getPrice())
                .build();
    }

}


