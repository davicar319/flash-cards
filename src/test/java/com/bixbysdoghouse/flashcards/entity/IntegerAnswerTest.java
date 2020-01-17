package com.bixbysdoghouse.flashcards.entity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IntegerAnswerTest {

    private IntegerAnswer integerAnswer = new IntegerAnswer(10);

    @Test
    void canary() {
        assertNotNull(integerAnswer);
    }

    @Test
    void anIntegerAnswerIsCorrect() {
        assertTrue(integerAnswer.isCorrect(new IntegerResponse(10)));
    }

    @Test
    void anIntegerAnswerIsWrong() {
        assertFalse(integerAnswer.isCorrect(new IntegerResponse(12)));
    }

    @Test
    void anIntegerAnswerisWrongWhenResponseCannotBeConvertedToAnInteger() {

        assertFalse(integerAnswer.isCorrect(new Response() {
            @Override
            public int asInteger() throws NoPossibleConversionException {
                throw new NoPossibleConversionException("Test");
            }

            @Override
            public String asString() {
                return null;
            }

            @Override
            public String asLowerCasedString() {
                return null;
            }
        }) );

    }

}