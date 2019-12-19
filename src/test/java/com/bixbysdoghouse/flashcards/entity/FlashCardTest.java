package com.bixbysdoghouse.flashcards.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlashCardTest {

    @Mock
    private Answer mockAnswer;
    @Mock
    private Question mockQuestion;
    @Mock
    private Response mockSubmission;
    private FlashCard flashCard;

    @BeforeEach
    void setupTests() {
        this.flashCard = new FlashCard(1L, mockQuestion, mockAnswer);
    }

    @Test
    void canary() {
        assertNotNull(this.flashCard);
    }

    @Test
    void testIdNotIncludedInEquals() {
        assertEquals(new FlashCard(), new FlashCard());
        assertEquals(new FlashCard(mockQuestion, mockAnswer),
                new FlashCard(mockQuestion, mockAnswer));
        assertEquals(new FlashCard(1L, mockQuestion, null),
                     new FlashCard(2L, mockQuestion, null));
        Question differentMockQuestion = Mockito.mock(Question.class);
        assertNotEquals(new FlashCard(1L, mockQuestion, null),
                        new FlashCard(1L, differentMockQuestion, null));

    }

    @Test
    void testIsAnswerCorrect() {
        Mockito.when(mockAnswer.isCorrect(mockSubmission)).thenReturn(true);
        assertTrue(this.flashCard.isCorrect(mockSubmission));
        Mockito.verify(mockAnswer).isCorrect(mockSubmission);
    }

}