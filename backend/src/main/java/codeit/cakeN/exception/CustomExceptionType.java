package codeit.cakeN.exception;

import org.springframework.http.HttpStatus;

public interface CustomExceptionType {
    int getErrorCode();
    HttpStatus getHttpStatus();
    String getErrorMessage();
}
