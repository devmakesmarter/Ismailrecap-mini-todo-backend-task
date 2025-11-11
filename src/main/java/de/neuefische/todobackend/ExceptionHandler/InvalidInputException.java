package de.neuefische.todobackend.ExceptionHandler;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
