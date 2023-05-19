package capstone.fullstack.exception;

import org.springframework.http.HttpStatus;

public enum PostExceptionType implements BaseExceptionType{
    POST_NOT_FOUND(700, HttpStatus.NOT_FOUND, "찾으시는 포스트가 없습니다."),
    NOT_AUTHORITY_UPDATE_POST(701, HttpStatus.FORBIDDEN, "포스트를 업데이트할 권한이 없습니다."),
    NOT_AUTHORITY_DELETE_POST(702, HttpStatus.FORBIDDEN, "포스트를 삭제할 권한이 없습니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    PostExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage){
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
