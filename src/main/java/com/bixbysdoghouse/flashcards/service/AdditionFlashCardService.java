package com.bixbysdoghouse.flashcards.service;

import com.bixbysdoghouse.flashcards.entity.Answer;
import com.bixbysdoghouse.flashcards.entity.FlashCard;
import com.bixbysdoghouse.flashcards.entity.FlashCardId;
import com.bixbysdoghouse.flashcards.entity.Question;
import com.bixbysdoghouse.flashcards.repository.CardExistsException;
import com.bixbysdoghouse.flashcards.repository.FlashCardNotFoundException;
import com.bixbysdoghouse.flashcards.repository.FlashCardRepository;
import javax.transaction.Transactional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdditionFlashCardService {

    private final FlashCardRepository flashCardRepository;

    public AdditionFlashCardService(final @NonNull FlashCardRepository flashCardRepository) {
        this.flashCardRepository = flashCardRepository;
    }

    @Transactional()
    public FlashCard createFlashCard(final Question question, final Answer answer) throws CardExistsException {
        log.debug(">>>createFlashCard: question={}, answer={}", question, answer);
        FlashCard flashCard = flashCardRepository.save(new FlashCard(question, answer));
        log.debug("<<<createFlashCard: question={}, answer={} -> {}", question, answer, flashCard);
        return flashCard;
    }

    @Transactional
    public Question getQuestion(FlashCardId flashCardId) throws MissingCardException {
        log.debug(">>>getQuestion: flashCardId={}", flashCardId);
        Question question;
        try {
            FlashCard flashCard = flashCardRepository.findById(flashCardId);
            question = flashCard.getQuestion();
        } catch (FlashCardNotFoundException e) {
            String msg = String.format("Could not find a flash card with the id: %s", flashCardId);
            log.warn(msg, e);
            throw new MissingCardException(msg, e);
        }
        return question;
    }

}
