package com.bixbysdoghouse.flashcards.entity;

public class IntegerAnswer implements Answer {
    private final int answer;

    public IntegerAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public boolean isCorrect(Response response) {
        try {
            return response.asInteger() == answer;
        } catch (NoPossibleConversionException e) {
            return false;
        }
    }
}
