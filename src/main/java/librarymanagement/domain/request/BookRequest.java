package librarymanagement.domain.request;

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

    @Builder(builderClassName = "of", builderMethodName = "of")
    public BookRequest(List<Long> authorIds, Long publisherId, String bookTitle, String bookClassificationNumber, String introduction, int price) {
        this.bookTitle = bookTitle;
        this.authorIds = authorIds;
        this.publisherId = publisherId;
        this.bookClassificationNumber = bookClassificationNumber;
        this.introduction = introduction;
        this.price = price;
    }
}




