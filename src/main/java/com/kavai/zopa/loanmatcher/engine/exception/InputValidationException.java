package com.kavai.zopa.loanmatcher.engine.exception;

public class InputValidationException extends Exception{
    public InputValidationException(String message) {
        super(message);
    }

    public InputValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
