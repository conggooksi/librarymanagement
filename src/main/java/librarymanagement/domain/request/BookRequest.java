package librarymanagement.domain.request;

import librarymanagement.domain.entity.Book;
import librarymanagement.domain.entity.Publisher;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

import static librarymanagement.domain.entity.QPublisher.publisher;

@Data
@NoArgsConstructor
public class BookRequest {
    private List<Long> authorIds;

    private Long publisherId;

    private String bookTitle;

    private String bookClassificationNumber;

    private String introduction;

    private int price;

    public Book toEntity(BookRequest bookRequest, Publisher publisherId) {
        return Book.createBuilder()
                .publisherId(publisherId)
                .bookTitle(bookRequest.getBookTitle())
                .bookClassificationNumber(bookRequest.getBookClassificationNumber())
                .introduction(bookRequest.getIntroduction())
                .price(bookRequest.getPrice())
                .build();
    }

}




