package librarymanagement.common.exception.code;

import lombok.Getter;

@Getter
public enum AuthorErrorCode {
    NOT_FOUND_ID("NOT_FOUND_ID", "저자 아이디를 찾을 수 없습니다."),
    BAD_REQUEST_AUTHOR("BAD_REQUEST_AUTHOR", "요청 형식이 잘못됐습니다.");
    private final String code;
    private final String message;

    AuthorErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
