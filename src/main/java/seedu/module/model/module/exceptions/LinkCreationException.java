package seedu.module.model.module.exceptions;

/**
 * Signals that the url provided by user end is not a proper link recognized by program
 */
public class LinkCreationException extends RuntimeException {
    public LinkCreationException(String message) {
        super(message);
    }
}
