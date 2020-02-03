package com.bixbysdoghouse.flashcards.web;

import com.bixbysdoghouse.flashcards.entity.FlashCard;
import com.bixbysdoghouse.flashcards.entity.FlashCardId;
import com.bixbysdoghouse.flashcards.repository.CardExistsException;
import com.bixbysdoghouse.flashcards.service.AdditionAnswer;
import com.bixbysdoghouse.flashcards.service.AdditionFlashCardService;
import com.bixbysdoghouse.flashcards.service.AdditionQuestion;
import com.bixbysdoghouse.flashcards.to.integer.addition.NewFlashCard;
import com.bixbysdoghouse.flashcards.to.integer.addition.NewQuestion;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
public class AdditionController {

    private final AdditionFlashCardService additionFlashCardService;

    @Autowired
    AdditionController(final AdditionFlashCardService additionFlashCardService) {
        this.additionFlashCardService = additionFlashCardService;
    }

    @PostMapping("/flash-card/addition")
    ResponseEntity<Void> createFlashCard(@RequestBody NewFlashCard newFlashCard) {
        try {
            NewQuestion newQuestion = newFlashCard.getQuestion();
            AdditionQuestion question = new AdditionQuestion(newQuestion.getAddend1(), newQuestion.getAddend2());
            AdditionAnswer answer = new AdditionAnswer(newFlashCard.getAnswer().getAnswer());
            FlashCard flashCard = additionFlashCardService.createFlashCard(question, answer);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                    "/{id}").buildAndExpand(flashCard.getId().getId()).toUri();
            return ResponseEntity.created(location).build();
        } catch (CardExistsException e) {
            String errorMessage = String.format("Could not create the card: %s Because that card already exists.",
                                                newFlashCard);
            log.warn(errorMessage, e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
        }
    }
}
