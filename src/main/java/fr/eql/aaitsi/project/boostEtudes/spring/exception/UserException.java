package fr.eql.aaitsi.project.boostEtudes.spring.exception;

public class UserException extends RuntimeException{

    public UserException() {
    }

    public UserException(String message) {
        super(message);
    }
}
