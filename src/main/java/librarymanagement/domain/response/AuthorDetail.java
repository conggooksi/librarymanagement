package librarymanagement.domain.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import librarymanagement.domain.entity.Author;
import librarymanagement.domain.entity.Publisher;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AuthorDetail {
    private Long authorId;
    private String authorName;

    private List<BookResponse> bookList;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;

    @Builder(builderMethodName = "of",builderClassName = "of")
    public AuthorDetail(Long authorId, String authorName,  List<BookResponse> bookList, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.bookList = bookList;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

    }

    public static AuthorDetail toDto(Author author){
        return of()
                .authorId(author.getId())
                .authorName(author.getAuthorName())
                .createdAt(author.getCreatedAt())
                .updatedAt(author.getUpdatedAt())
                .bookList(author.getBookAuthorList()
                        .stream().map(bookAuthor -> BookResponse.toDto(bookAuthor.getBook()))
                        .collect(Collectors.toList()))
                .build();
    }
}
