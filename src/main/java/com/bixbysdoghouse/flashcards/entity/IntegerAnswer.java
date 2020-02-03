package com.bixbysdoghouse.flashcards.entity;

import lombok.Data;

@Data
public class IntegerAnswer implements Answer {
    private final int value;

    @Override
    public boolean isCorrect(Response response) {
        try {
            return response.asInteger() == value;
        } catch (NoPossibleConversionException e) {
            return false;
        }
    }
}
