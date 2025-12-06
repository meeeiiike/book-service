package ie.atu.book_service.ErrorHandling;

public class DuplicateExceptionHandling extends RuntimeException {
    public DuplicateExceptionHandling(String message) {
        super(message);
    }
}
