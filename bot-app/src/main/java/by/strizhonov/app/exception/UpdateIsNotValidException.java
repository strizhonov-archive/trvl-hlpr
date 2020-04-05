package by.strizhonov.app.exception;

public class UpdateIsNotValidException extends Exception {

    public UpdateIsNotValidException(final String message) {
        super(message);
    }

    public UpdateIsNotValidException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
