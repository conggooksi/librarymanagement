package librarymanagement.domain.response;

import librarymanagement.domain.entity.Book;
import librarymanagement.domain.entity.BookAuthor;
import librarymanagement.domain.entity.Publisher;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BookResponse {

    private Long id;
    private String title;

    private String publisher;
    private String bookClassificationNumber;
    private String introduction;
    private int price;

    @Builder(builderMethodName = "bookBuilder", builderClassName = "bookBuilder")
    public BookResponse(Long id, String title, String publisher, String bookClassificationNumber, String introduction, int price) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.bookClassificationNumber = bookClassificationNumber;
        this.introduction = introduction;
        this.price = price;
    }

    public static BookResponse toDto(Book book) {
        return BookResponse.bookBuilder()
                .id(book.getId())
                .title(book.getTitle())
                .publisher(book.getPublisher().getName())
                .bookClassificationNumber(book.getBookClassificationNumber())
                .introduction(book.getIntroduction())
                .price(book.getPrice())
                .build();
    }
}
