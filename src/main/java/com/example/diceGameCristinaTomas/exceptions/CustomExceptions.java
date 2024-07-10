package com.example.diceGameCristinaTomas.exceptions;

public class CustomExceptions {
    public static class PlayerNotFoundException extends RuntimeException {
        public PlayerNotFoundException(String message) {
            super(message);
        }
    }

    public static class UnauthorizedActionException extends RuntimeException {
        public UnauthorizedActionException(String message) {
            super(message);
        }
    }
}
