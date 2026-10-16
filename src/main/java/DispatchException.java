/**
 * Custom exception used for errors related to incident
 * and dispatch operations.
 */
public class DispatchException extends RuntimeException {

    /**
     * Creates a dispatch exception with the specified error message.
     *
     * @param message description of the error
     */
    public DispatchException(String message) {
        super(message);
    }
}
