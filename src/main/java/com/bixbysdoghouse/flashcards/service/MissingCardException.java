package com.bixbysdoghouse.flashcards.service;

import com.bixbysdoghouse.flashcards.repository.FlashCardNotFoundException;

public class MissingCardException extends Exception {
    public MissingCardException(String msg, FlashCardNotFoundException e) {
        super(msg, e);
    }
}
