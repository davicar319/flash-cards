package com.bixbysdoghouse.flashcards.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionQuestionTest {
    private static final int ADDEND_1 = 2;
    private static final int ADDEND_2 = 3;
    private AdditionQuestion additionQuestion = new AdditionQuestion(ADDEND_1, ADDEND_2);

    @Test
    void canary() {
        assertNotNull(additionQuestion);
    }

    @Test
    void checkQuestionOutput() {
        String expectedOutput = String.format("%d + %d = ?", ADDEND_1, ADDEND_2);
        assertEquals(expectedOutput, additionQuestion.getQuestion());
    }

}