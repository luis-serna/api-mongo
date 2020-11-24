package com.example.apimongo.exceptions;

public class NoResultException extends Exception {

    private static final long serialVersionUID = -3387516993694229948L;

    public NoResultException(String message) {
        super(message);
    }

}
