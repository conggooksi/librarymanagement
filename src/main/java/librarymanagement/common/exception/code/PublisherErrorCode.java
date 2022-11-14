package librarymanagement.common.exception.code;

import lombok.Getter;

@Getter
public enum PublisherErrorCode {
    NOT_FOUND_ID("NOT_FOUND_ID", "아이디를 찾지 못했습니다."),
    OVERLAP_PUBLISHER("FAIL_ADD_PUBLISHER", "이미 등록된 출판사입니다.");
    private final String code;
    private final String message;

    PublisherErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
