package librarymanagement.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PublisherSearch {
    private Long publisherId;
    private String publisherName;
}
