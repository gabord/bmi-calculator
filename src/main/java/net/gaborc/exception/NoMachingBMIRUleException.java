package net.gaborc.exception;

/**
 * Exception for cases where no matching rule was found for a BMI rule
 */
public class NoMachingBMIRUleException extends BusinessException {
    public NoMachingBMIRUleException() {
    }

    public NoMachingBMIRUleException(String message) {
        super(message);
    }
}
