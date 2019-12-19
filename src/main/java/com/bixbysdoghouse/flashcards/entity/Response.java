package com.bixbysdoghouse.flashcards.entity;

public interface Response {
    int asInteger() throws NoPossibleConversionException;
    String asString() throws NoPossibleConversionException;
    String asLowerCasedString() throws  NoPossibleConversionException;
}
