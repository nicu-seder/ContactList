package ro.jademy.exceptions;

public class OptionException extends Exception {
    public OptionException() {
    }

    public OptionException(String message) {
        super(message);
    }

    public OptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public OptionException(Throwable cause) {
        super(cause);
    }

    public OptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
