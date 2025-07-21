package sanatorium.patient.service.exeptions;

public class DuplicateFieldError extends RuntimeException {
    public DuplicateFieldError(String message) {
        super(message);
    }
}
