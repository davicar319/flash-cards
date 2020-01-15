package com.bixbysdoghouse.flashcards.repository;

import com.bixbysdoghouse.flashcards.entity.FlashCard;
import com.bixbysdoghouse.flashcards.entity.FlashCardId;
import java.util.HashSet;
import java.util.Set;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class FlashCardRepositoryImpl implements FlashCardRepository {

    private final Set<FlashCard> flashCards = new HashSet<>();
    private final LongFlashCardIdFactory flashCardIdFactory;

    @Autowired
    public FlashCardRepositoryImpl(@NonNull LongFlashCardIdFactory flashCardIdFactory) {
        this.flashCardIdFactory = flashCardIdFactory;
    }

    @Override
    public FlashCard save(FlashCard flashCard) throws CardExistsException {
        log.debug(">>>save: flashCard={}", flashCard);
        if (flashCards.contains(flashCard)) {
            throw new CardExistsException("The FlashCard: " + flashCard + " already exists in the system.");
        } else {
            flashCard.setId(flashCardIdFactory.createNextId());
            flashCards.add(flashCard);
        }
        log.debug("<<<save: flashcard={} -> {}", flashCard, flashCard);
        return flashCard;
    }

    @Override
    public FlashCard findById(FlashCardId id) throws FlashCardNotFoundException {
        log.debug(">>>findById: id={}", id);
        FlashCard result = flashCards.stream()
                                     .filter(flashCard -> flashCard.getId().equals(id))
                                     .findFirst()
                                     .orElseThrow(FlashCardNotFoundException::new);
        log.debug(">>>findById: id={} -> {}", id, result);
        return result;
    }
}
