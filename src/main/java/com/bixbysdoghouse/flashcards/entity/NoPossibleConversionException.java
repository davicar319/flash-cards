package com.bixbysdoghouse.flashcards.entity;

public class NoPossibleConversionException extends Exception {
    NoPossibleConversionException() {

    }

    NoPossibleConversionException(String message) {
        super(message);
    }
}
