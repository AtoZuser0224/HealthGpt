package uni.server.healthgpt.advice.exception;

public class CEmailverificationFailException extends RuntimeException{
    public CEmailverificationFailException() {
    }

    public CEmailverificationFailException(String message) {
        super(message);
    }

    public CEmailverificationFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
