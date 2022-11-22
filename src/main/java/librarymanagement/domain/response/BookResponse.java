package librarymanagement.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import librarymanagement.domain.entity.Book;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {

    private Long id;
    private String title;
    private String publisher;
    private String author;
    private String bookClassificationNumber;
    private String introduction;
    private int price;

    @Builder(builderMethodName = "bookBuilder", builderClassName = "bookBuilder")
    public BookResponse(Long id, String title, String publisher, String author, String bookClassificationNumber, String introduction, int price) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.author = author;
        this.bookClassificationNumber = bookClassificationNumber;
        this.introduction = introduction;
        this.price = price;
    }

    @Builder(builderMethodName = "searchBuilder", builderClassName = "searchBuilder")
    public BookResponse(Long id, String title, String bookClassificationNumber, int price) {
        this.id = id;
        this.title = title;
        this.bookClassificationNumber = bookClassificationNumber;
        this.price = price;
    }

    public static BookResponse toDto(Book book) {
        return BookResponse.bookBuilder()
                .id(book.getBookId())
                .title(book.getBookTitle())
                .publisher(book.getBookPublisher().getName())
                .author(book.getBookAuthorList().stream().map(bookAuthor -> bookAuthor.getAuthor().getAuthorName()).collect(Collectors.joining(",")))
                .bookClassificationNumber(book.getBookClassificationNumber())
                .introduction(book.getBookIntroduction())
                .price(book.getBookPrice())
                .build();
    }

    public static BookResponse toSearch(Book book) {
        return BookResponse.searchBuilder()
                .id(book.getBookId())
                .title(book.getBookTitle())
                .bookClassificationNumber(book.getBookClassificationNumber())
                .price(book.getBookPrice())
                .build();
    }
}
