package codeit.cakeN.exception.user;

import codeit.cakeN.exception.CustomException;
import codeit.cakeN.exception.CustomExceptionType;

public class UserException extends CustomException {
    private CustomExceptionType exceptionType;
    public UserException(CustomExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public CustomExceptionType getExceptionType() {
        return exceptionType;
    }
}
