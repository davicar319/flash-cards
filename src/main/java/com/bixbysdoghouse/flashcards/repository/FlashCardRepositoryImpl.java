package com.bixbysdoghouse.flashcards.repository;

import com.bixbysdoghouse.flashcards.entity.FlashCard;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Repository;

@Repository
public class FlashCardRepositoryImpl implements FlashCardRepository{
    private final Set<FlashCard> flashCards = new HashSet<>();
    private long currentId = 0;

    @Override
    public FlashCard save(FlashCard flashCard) throws CardExistsException {
        if(flashCards.contains(flashCard)) {
            throw new CardExistsException("The FlashCard: " + flashCard + " already exists in the system.");
        } else {
            flashCard.setId(++currentId);
            flashCards.add(flashCard);
        }
        return flashCard;
    }

    @Override
    public FlashCard findById(Long id) throws FlashCardNotFoundException {
        return flashCards.stream().filter(flashCard -> flashCard.getId().equals(id)).findFirst().orElseThrow(FlashCardNotFoundException::new);
    }
}
