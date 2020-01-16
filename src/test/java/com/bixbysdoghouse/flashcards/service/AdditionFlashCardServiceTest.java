package com.bixbysdoghouse.flashcards.service;

import static org.junit.jupiter.api.Assertions.*;

import com.bixbysdoghouse.flashcards.entity.*;
import com.bixbysdoghouse.flashcards.repository.CardExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdditionFlashCardServiceTest {

    @Autowired
    private AdditionFlashCardService additionFlashCardService;

    @Test
    void canary() {
        assertNotNull(additionFlashCardService);
    }

    @Test
    void aNewAdditionFlashCardCanBeCreated() throws Exception{
        Question question = Mockito.mock(Question.class);
        Answer answer = Mockito.mock(Answer.class);
        FlashCard flashCard = additionFlashCardService.createFlashCard(question, answer);
        assertNotNull(flashCard);
        assertEquals(question, flashCard.getQuestion());
        assertEquals(answer, flashCard.getAnswer());
        assertNotNull(flashCard.getId());
    }

    @Test
    void eachDifferentFlashCardHasDifferentId() throws Exception{
        Question question1 = Mockito.mock(Question.class);
        Answer answer1 = Mockito.mock(Answer.class);
        Question question2 = Mockito.mock(Question.class);
        Answer answer2 = Mockito.mock(Answer.class);

        FlashCard flashCard1 = additionFlashCardService.createFlashCard(question1, answer1);
        FlashCard flashCard2 = additionFlashCardService.createFlashCard(question2, answer2);

        assertNotEquals(flashCard1, flashCard2);
        assertNotEquals(flashCard1.getId(), flashCard2.getId());
    }

    @Test
    void creatingTwoFlashCardsThatAreTheSameResultsInACardExistsException() throws Exception {
        Question question = Mockito.mock(Question.class);
        Answer answer = Mockito.mock(Answer.class);

        additionFlashCardService.createFlashCard(question, answer);
        try {
            additionFlashCardService.createFlashCard(question, answer);
            fail("Expected CardExistsException");
        } catch (CardExistsException e) {
            //Test Passes
        }
    }

    @Test
    void canGetTheQuestionForFlashCard() throws Exception {
        Question question = new AdditionQuestion(1, 1);
        Answer answer = new IntegerAnswer(2);
        //When I create a single flash card
        FlashCard flashCard = additionFlashCardService.createFlashCard(question, answer);
        //Then I can get it back
        Question returnedQuestion = additionFlashCardService.getQuestion(flashCard.getId());
        assertEquals(question, returnedQuestion);
    }

    @Test
    void whenFlashCardIsMissingExceptionIsThrown() {
        Assertions.assertThrows(MissingCardException.class, () -> {
            FlashCardId flashCardId = new FlashCardId() {};
            additionFlashCardService.getQuestion(flashCardId);
        });
    }


}
