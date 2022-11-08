package librarymanagement.domain.response;

import librarymanagement.domain.entity.Author;
import librarymanagement.domain.entity.BookAuthor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AuthorResponse {

    private Long authorId;
    private String authorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder(builderMethodName = "listBuilder", builderClassName = "listBuilder")
    public AuthorResponse(Long authorId, String authorName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AuthorResponse toDto() {
        return AuthorResponse.listBuilder()
                .authorId(this.authorId)
                .authorName(this.authorName)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
    
    
}
