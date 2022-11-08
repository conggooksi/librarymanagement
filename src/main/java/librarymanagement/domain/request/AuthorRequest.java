package librarymanagement.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import librarymanagement.domain.entity.Author;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AuthorRequest {
    @JsonProperty("author_name")
    @NotBlank
    private String authorName;

    public Author toEntity(AuthorRequest authorRequest) {
        return Author.createBuilder()
                .name(authorRequest.authorName)
                .build();
    }
}