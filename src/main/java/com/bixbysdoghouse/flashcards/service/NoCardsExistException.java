package com.bixbysdoghouse.flashcards.service;

public class NoCardsExistException extends Exception {
    public NoCardsExistException(String msg) {
        super(msg);
    }
}
