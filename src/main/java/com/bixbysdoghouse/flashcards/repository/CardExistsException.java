package com.bixbysdoghouse.flashcards.repository;

public class CardExistsException extends Exception {
    public CardExistsException(String msg) {
        super(msg);
    }
}
