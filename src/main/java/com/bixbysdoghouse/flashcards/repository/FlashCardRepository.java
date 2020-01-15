package com.bixbysdoghouse.flashcards.repository;

import com.bixbysdoghouse.flashcards.entity.FlashCard;
import com.bixbysdoghouse.flashcards.entity.FlashCardId;

public interface FlashCardRepository {
    FlashCard save(FlashCard flashCard) throws CardExistsException;

    FlashCard findById(FlashCardId id) throws FlashCardNotFoundException;
}
