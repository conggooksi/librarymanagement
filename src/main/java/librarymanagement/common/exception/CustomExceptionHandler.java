package librarymanagement.common.exception;

import librarymanagement.common.result.JsonResultData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<?> exceptionHandler(HttpServletRequest request, final ApiException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(JsonResultData.failResultBuilder()
                        .errorCode(e.getErrorEntity().getError().getCode())
                        .errorMessage(e.getErrorEntity().getError().getMessage())
                        .build());

    }
}
