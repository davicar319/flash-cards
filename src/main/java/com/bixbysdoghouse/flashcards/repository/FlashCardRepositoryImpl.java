package com.bixbysdoghouse.flashcards.repository;

import com.bixbysdoghouse.flashcards.entity.FlashCard;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class FlashCardRepositoryImpl implements FlashCardRepository{

    private final Set<FlashCard> flashCards = new HashSet<>();
    private long currentId = 0;

    @Override
    public FlashCard save(FlashCard flashCard) throws CardExistsException {
        log.debug(">>>save: flashCard={}", flashCard);
        if(flashCards.contains(flashCard)) {
            throw new CardExistsException("The FlashCard: " + flashCard + " already exists in the system.");
        } else {
            flashCard.setId(++currentId);
            flashCards.add(flashCard);
        }
        log.debug("<<<save: flashcard={} -> {}", flashCard, flashCard);
        return flashCard;
    }

    @Override
    public FlashCard findById(Long id) throws FlashCardNotFoundException {
        log.debug(">>>findById: id={}", id);
        FlashCard result =  flashCards.stream()
                                      .filter(flashCard -> flashCard.getId().equals(id))
                                      .findFirst()
                                      .orElseThrow(FlashCardNotFoundException::new);
        log.debug(">>>findById: id={} -> {}", id, result);
        return result;
    }
}
