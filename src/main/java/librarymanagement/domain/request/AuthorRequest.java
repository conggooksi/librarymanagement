package librarymanagement.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import librarymanagement.domain.entity.Author;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AuthorRequest {

    @NotBlank(message = "작가의 이름에는 공백을 사용할 수 없습니다.") // 이 메시지를 그대로 사용자가 볼 ;수 있도록 작업
    private String authorName;

    public Author toEntity(AuthorRequest authorRequest) {
        return Author.createBuilder()
                .name(authorRequest.authorName)
                .build();
    }

}
