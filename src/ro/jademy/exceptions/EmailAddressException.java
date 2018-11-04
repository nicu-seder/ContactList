package ro.jademy.exceptions;

public class EmailAddressException extends Exception{
    public EmailAddressException() {
    }

    public EmailAddressException(String message) {
        super(message);
    }

    public EmailAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAddressException(Throwable cause) {
        super(cause);
    }

    public EmailAddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public void exceptionMessage(){
        System.out.println("Email address is invalid");
    }
}
