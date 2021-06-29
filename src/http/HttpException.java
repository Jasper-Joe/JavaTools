package http;

public class HttpException extends RuntimeException{
    public HttpException(Throwable e) {
        super(e.getMessage(), e);
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException (String message, Throwable throwable) {
        super(message, throwable);
    }


}
