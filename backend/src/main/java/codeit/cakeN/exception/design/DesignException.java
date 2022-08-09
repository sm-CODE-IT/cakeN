package codeit.cakeN.exception.design;

import codeit.cakeN.exception.CustomException;
import codeit.cakeN.exception.CustomExceptionType;

public class DesignException extends CustomException {
    private CustomExceptionType exceptionType;
    public DesignException(CustomExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public CustomExceptionType getExceptionType() {
        return exceptionType;
    }
}
