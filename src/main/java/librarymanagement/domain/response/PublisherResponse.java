package librarymanagement.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import librarymanagement.domain.entity.Publisher;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PublisherResponse {

    private Long publisherId;
    private String publisherName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;

    @Builder(builderClassName = "listBuilder", builderMethodName = "listBuilder")
    public PublisherResponse(Long publisherId, String publisherName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PublisherResponse toDto(Publisher publisher) {
        return PublisherResponse.listBuilder()
                .publisherId(publisher.getId())
                .publisherName(publisher.getName())
                .createdAt(publisher.getCreatedAt())
                .updatedAt(publisher.getUpdatedAt())
                .build();
    }
}
