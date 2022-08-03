package codeit.cakeN.exception.user;

import codeit.cakeN.exception.CustomExceptionType;
import org.springframework.http.HttpStatus;

public enum UserExceptionType implements CustomExceptionType {
    // 회원가입, 로그인 시
    ALREADY_EXIST_USERNAME(600, HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),
    WRONG_PASSWORD(601, HttpStatus.BAD_REQUEST, "비밀번호가 잘못되었습니다."),
    NOT_FOUND_USER(602, HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    UserExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
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
