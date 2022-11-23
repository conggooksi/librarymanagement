package librarymanagement.domain.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import librarymanagement.domain.entity.Book;
import librarymanagement.domain.entity.Publisher;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDetail {

    private Long bookId;
    private String bookTitle;
    private Publisher bookPublisher;
    private String bookClassificationNumber;
    private String bookInroduction;
    private Integer bookPrice;

    @Builder(builderMethodName = "bookBuilder", builderClassName = "bookBuilder")
    public BookDetail(Long id, String title, Publisher publisher, String bookClassificationNumber, String introduction, Integer price) {
        this.bookId = id;
        this.bookTitle = title;
        this.bookPublisher = publisher;
        this.bookClassificationNumber = bookClassificationNumber;
        this.bookInroduction = introduction;
        this.bookPrice = price;
    }

    @Builder(builderMethodName = "searchBuilder", builderClassName = "searchBuilder")
    public BookDetail(Long id, String title, String bookClassificationNumber, int price) {
        this.bookId = id;
        this.bookTitle = title;
        this.bookClassificationNumber = bookClassificationNumber;
        this.bookPrice = price;
    }

    public static BookDetail toDto(Book book) {
        return BookDetail.bookBuilder()
                .id(book.getId())
                .title(book.getBookTitle())
//                .publisher(book.getBookPublisher().getName())
                .bookClassificationNumber(book.getBookClassificationNumber())
                .introduction(book.getIntroduction())
                .price(book.getPrice())
                .build();
    }

    public static BookDetail toSearch(Book book) {
        return BookDetail.searchBuilder()
                .id(book.getId())
                .title(book.getBookTitle())
                .bookClassificationNumber(book.getBookClassificationNumber())
                .price(book.getPrice())
                .build();
    }
}
