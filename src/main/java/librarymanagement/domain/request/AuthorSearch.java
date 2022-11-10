package librarymanagement.domain.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AuthorSearch {
    private Long authorId;

    private String authorName;

}
