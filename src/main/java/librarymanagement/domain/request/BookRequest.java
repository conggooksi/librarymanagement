package librarymanagement.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookRequest {
    private Long id;

    private String title;

    private String author;

    private String publisher;

    private String bookClassificationNumber;

    private String introduction;

    private int price;

}
