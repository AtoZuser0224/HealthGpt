package uni.server.healthgpt.advice.exception;

public class CEmailNotFoundException extends RuntimeException{
    public CEmailNotFoundException() {
    }

    public CEmailNotFoundException(String message) {
        super(message);
    }
}
