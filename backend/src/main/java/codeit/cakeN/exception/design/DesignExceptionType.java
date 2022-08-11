package codeit.cakeN.exception.design;

import codeit.cakeN.exception.CustomExceptionType;
import org.springframework.http.HttpStatus;

public enum DesignExceptionType implements CustomExceptionType {
    NOT_FOUND_DESIGN(602, HttpStatus.NOT_FOUND, "존재하지 않는 디자인입니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    DesignExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
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
