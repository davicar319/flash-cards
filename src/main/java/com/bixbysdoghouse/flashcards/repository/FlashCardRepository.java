package com.bixbysdoghouse.flashcards.repository;

import com.bixbysdoghouse.flashcards.entity.FlashCard;

public interface FlashCardRepository {
    FlashCard save(FlashCard flashCard) throws CardExistsException;

    FlashCard findById(Long id) throws FlashCardNotFoundException;
}
