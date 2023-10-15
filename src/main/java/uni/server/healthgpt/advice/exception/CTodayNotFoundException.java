package uni.server.healthgpt.advice.exception;

public class CTodayNotFoundException extends RuntimeException{
    public CTodayNotFoundException() {
        super();
    }

    public CTodayNotFoundException(String message) {
        super(message);
    }

    public CTodayNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
