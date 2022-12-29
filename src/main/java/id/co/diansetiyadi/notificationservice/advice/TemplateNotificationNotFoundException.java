package id.co.diansetiyadi.notificationservice.advice;

public class TemplateNotificationNotFoundException extends RuntimeException{

    public TemplateNotificationNotFoundException(String message) {
        super(message);
    }
}
