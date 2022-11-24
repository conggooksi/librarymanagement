package librarymanagement.common.exception.code;

import lombok.Getter;

@Getter
public enum BookErrorCode {
    NOT_FOUND_ID("NOT_FOUND_ID", "도서 아이디를 찾을 수 없습니다.");
    private final String code;
    private final String message;

    BookErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
