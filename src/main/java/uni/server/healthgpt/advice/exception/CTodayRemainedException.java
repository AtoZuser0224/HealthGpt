package uni.server.healthgpt.advice.exception;

public class CTodayRemainedException extends RuntimeException{
    public CTodayRemainedException() {
        super();
    }

    public CTodayRemainedException(String message) {
        super(message);
    }

    public CTodayRemainedException(String message, Throwable cause) {
        super(message, cause);
    }
}
