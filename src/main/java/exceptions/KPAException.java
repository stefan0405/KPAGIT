package exceptions;

import java.util.Arrays;
import java.util.List;

public class KPAException extends Exception {

    private final List<String> errorMessages;

    public KPAException(List<String> errorMessages) {
        super("Validation of input parameters failed.");
        this.errorMessages = errorMessages;
    }

    public KPAException(String errorMessage) {
        super("Validation of input parameters failed.");
        this.errorMessages = Arrays.asList(errorMessage);
    }

    public KPAException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
        this.errorMessages = Arrays.asList(errorMessage);
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

}
