package com.bixbysdoghouse.flashcards.service;

import com.bixbysdoghouse.flashcards.entity.Answer;
import com.bixbysdoghouse.flashcards.entity.FlashCard;
import com.bixbysdoghouse.flashcards.entity.Question;
import com.bixbysdoghouse.flashcards.repository.CardExistsException;
import com.bixbysdoghouse.flashcards.repository.FlashCardNotFoundException;
import com.bixbysdoghouse.flashcards.repository.FlashCardRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class AdditionFlashCardService {

    private final FlashCardRepository flashCardRepository;
    private final List<Long> ids = new ArrayList<>();
    private int currentCard = 0;

    public AdditionFlashCardService(final @NonNull FlashCardRepository flashCardRepository) {
        this.flashCardRepository = flashCardRepository;
    }

    @Transactional()
    public FlashCard createFlashCard(final Question question, final Answer answer) throws CardExistsException {
        FlashCard flashCard = flashCardRepository.save(new FlashCard(question, answer));
        ids.add(flashCard.getId());
        return flashCard;
    }

    @Transactional
    public FlashCard getNextCard() throws NoCardsExistException {
        Long id = getIdOfNextCard();
        try {
            return flashCardRepository.findById(id);
        } catch (FlashCardNotFoundException e) {
            throw new AdditionFlashCardSystemException("Could not find a FlashCard with the Id: " + id);
        }
    }

    private Long getIdOfNextCard() throws NoCardsExistException {
        int numIds = ids.size();
        if (numIds == 0) {
            throw new NoCardsExistException("There are no cards available.");
        } else if (currentCard >= numIds) {
            currentCard = 0;
        }
        return ids.get(currentCard++);
    }
}
