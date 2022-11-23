package librarymanagement.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import librarymanagement.domain.entity.Author;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AuthorResponse {

    private Long authorId;
    private String authorName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;

    @Builder(builderMethodName = "listBuilder", builderClassName = "listBuilder")
    public AuthorResponse(Long authorId, String authorName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static AuthorResponse toDto(Author author) {
        return AuthorResponse.listBuilder()
                .authorId(author.getId())
                .authorName(author.getAuthorName())
                .createdAt(author.getCreatedAt())
                .updatedAt(author.getUpdatedAt())
                .build();
    }


    public Page<AuthorResponse> toPage(Page<Author> author){
        Page<AuthorResponse> authorList = author.map(m -> AuthorResponse.listBuilder()
                .authorId(m.getId())
                .createdAt(m.getCreatedAt())
                .updatedAt(m.getUpdatedAt())
                .build());
        return authorList;
    }
}
