package com.example.demo.exceptions;

public class EmptyFileException extends RuntimeException{

    public EmptyFileException(String message) {
        super(message);
    }
}
