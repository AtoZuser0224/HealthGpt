package uni.server.healthgpt.advice.exception;

public class CEmailTimeOutException extends RuntimeException{
    public CEmailTimeOutException(String message) {
        super(message);
    }

    public CEmailTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public CEmailTimeOutException() {
    }
}
