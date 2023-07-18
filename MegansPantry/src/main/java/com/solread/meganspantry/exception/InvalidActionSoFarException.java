package com.solread.meganspantry.exception;

public class InvalidActionSoFarException extends Exception {
    public InvalidActionSoFarException() {
        super("Can't do this just yet.");
    }

    public InvalidActionSoFarException(String message) {
        super(message);
    }
}
