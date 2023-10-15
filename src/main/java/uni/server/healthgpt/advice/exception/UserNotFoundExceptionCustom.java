package uni.server.healthgpt.advice.exception;

public class UserNotFoundExceptionCustom extends RuntimeException {

    public UserNotFoundExceptionCustom(String msg, Throwable throwable) {
        super(msg,throwable);
    }

    public UserNotFoundExceptionCustom(String message) {
        super(message);
    }

    public UserNotFoundExceptionCustom() {
        super();
    }
}
