package id.co.diansetiyadi.notificationservice.advice;

public class EmailNotFoundException extends RuntimeException{

    public EmailNotFoundException(String message) {
        super(message);
    }
}
