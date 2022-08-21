package codeit.cakeN.exception;

public abstract class CustomException extends RuntimeException {
    public abstract CustomExceptionType getExceptionType();
}
