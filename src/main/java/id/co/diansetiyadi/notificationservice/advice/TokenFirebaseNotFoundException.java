package id.co.diansetiyadi.notificationservice.advice;

public class TokenFirebaseNotFoundException extends RuntimeException{

    public TokenFirebaseNotFoundException(String message) {
        super(message);
    }
}
