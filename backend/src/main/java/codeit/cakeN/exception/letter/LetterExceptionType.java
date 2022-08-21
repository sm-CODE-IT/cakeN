package codeit.cakeN.exception.letter;

import codeit.cakeN.exception.CustomExceptionType;
import org.springframework.http.HttpStatus;

public enum LetterExceptionType implements CustomExceptionType {
    NOT_FOUND_LETTER(602, HttpStatus.NOT_FOUND, "존재하지 않는 레터링입니다."),
    ALREADY_HEART_LETTER(603, HttpStatus.ALREADY_REPORTED, "이미 좋아요한 레터링입니다."),
    NOT_FOUND_HEART(604, HttpStatus.NOT_FOUND, "좋아요 기록을 찾을 수 없습니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    LetterExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }


    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}