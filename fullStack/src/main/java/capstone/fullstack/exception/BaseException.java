package capstone.fullstack.exception;

public abstract class BaseException extends RuntimeException{

    public abstract BaseExceptionType getExceptionType();
}
