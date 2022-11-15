package librarymanagement.domain.response;

import librarymanagement.domain.entity.Book;
import librarymanagement.domain.entity.BookAuthor;
import librarymanagement.domain.entity.Publisher;
import librarymanagement.domain.entity.QBook;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static librarymanagement.domain.entity.QBook.book;
import static librarymanagement.domain.entity.QBookAuthor.bookAuthor;

@Data
@NoArgsConstructor
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
                .id(book.getId())
                .title(book.getTitle())
                .publisher(book.getPublisher().getName())
                .author(book.getBookAuthorList().stream().map(bookAuthor -> bookAuthor.getAuthor().getName()).collect(Collectors.joining(",")))
                .bookClassificationNumber(book.getBookClassificationNumber())
                .introduction(book.getIntroduction())
                .price(book.getPrice())
                .build();
    }

    public static BookResponse toSearch(Book book) {
        return BookResponse.searchBuilder()
                .id(book.getId())
                .title(book.getTitle())
                .bookClassificationNumber(book.getBookClassificationNumber())
                .price(book.getPrice())
                .build();
    }
}
