package capstone.fullstack.exception;

public class FileException extends BaseException {

    private BaseExceptionType exceptionType;

    public FileException(BaseExceptionType exceptionType){
        this.exceptionType = exceptionType;
    }

    @Override
    public BaseExceptionType getExceptionType() {
        return exceptionType;
    }
}
