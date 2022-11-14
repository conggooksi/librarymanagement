package librarymanagement.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import librarymanagement.domain.entity.Publisher;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PublisherDetail {
    private Long publisherId;
    private String publisherName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;
    private List<BookResponse> bookList;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public PublisherDetail(Long publisherId, String publisherName, LocalDateTime createdAt, LocalDateTime updatedAt, List<BookResponse> bookList) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.bookList = bookList;
    }

    public static PublisherDetail toDto(Publisher publisher) {
        return of()
                .publisherId(publisher.getId())
                .publisherName(publisher.getName())
                .createdAt(publisher.getCreatedAt())
                .updatedAt(publisher.getUpdatedAt())
                .bookList(publisher.getBookList().stream().map(BookResponse::toDto).collect(Collectors.toList()))
                .build();
    }
}
