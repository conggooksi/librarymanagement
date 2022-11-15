package librarymanagement.domain.request;

import librarymanagement.domain.entity.BookAuthor;
import librarymanagement.domain.entity.Publisher;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BookSearch {
    private Long id;
    private String title;
    private String bookClassificationNumber;
    private Integer minPrice;
    private Integer maxPrice;
}
