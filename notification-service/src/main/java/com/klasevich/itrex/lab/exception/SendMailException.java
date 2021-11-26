package com.klasevich.itrex.lab.exception;

public class SendMailException extends Exception {
    public SendMailException(String message) {
        super(message);
    }

    public SendMailException(String message, Throwable cause) {
        super(message, cause);
    }
}
