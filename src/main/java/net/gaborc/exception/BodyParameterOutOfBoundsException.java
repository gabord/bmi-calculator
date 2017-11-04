package net.gaborc.exception;

/**
 * Exception for cases where given body parameter(s) are not valid
 */
public class BodyParameterOutOfBoundsException extends BusinessException {
    public BodyParameterOutOfBoundsException(String message) {
        super(message);
    }
}
