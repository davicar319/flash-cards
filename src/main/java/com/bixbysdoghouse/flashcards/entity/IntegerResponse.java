package com.bixbysdoghouse.flashcards.entity;

public class IntegerResponse implements Response {
    private final int response;

    public IntegerResponse(int response) {
        this.response = response;
    }

    @Override
    public int asInteger() {
        return response;
    }

    @Override
    public String asString() {
        return Integer.toString(response);
    }

    @Override
    public String asLowerCasedString() {
        return Integer.toString(response);
    }
}
