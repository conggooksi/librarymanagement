package librarymanagement.domain.request;

import librarymanagement.domain.entity.Book;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookRequest {
    private List<Long> authorIds;

    private Long publisherId;

    private String bookTitle;

    private String bookClassificationNumber;

    private String introduction;

    private int price;

    public Book toEntity(BookRequest bookRequest) {
//        return Book.bookBuilder();
        return null;
    }
}




