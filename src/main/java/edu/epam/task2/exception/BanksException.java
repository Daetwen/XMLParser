package edu.epam.task2.exception;

public class BanksException extends Exception {
    public BanksException() {
    }

    public BanksException(String message) {
        super(message);
    }

    public BanksException(Throwable cause) {
        super(cause);
    }

    public BanksException(String message, Throwable cause) {
        super(message, cause);
    }
}
