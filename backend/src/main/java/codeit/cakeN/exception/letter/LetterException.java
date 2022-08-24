package codeit.cakeN.exception.letter;

import codeit.cakeN.exception.CustomException;
import codeit.cakeN.exception.CustomExceptionType;

public class LetterException extends CustomException {
    private CustomExceptionType exceptionType;
    public LetterException(CustomExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    @Override
    public CustomExceptionType getExceptionType() {
        return exceptionType;
    }
}
