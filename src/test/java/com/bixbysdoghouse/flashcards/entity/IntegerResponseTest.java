package com.bixbysdoghouse.flashcards.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IntegerResponseTest {
    private IntegerResponse integerResponse = new IntegerResponse(10);

    @Test
    void canary() {
        assertNotNull(integerResponse);
    }

    @Test
    void anIntegerResponseCanBeConvertedToAnInteger() {
        assertEquals(10, integerResponse.asInteger());
    }

    @Test
    void anIntegerResponseCanBeConvertedToAString() {
        assertEquals("10", integerResponse.asString());
    }

    @Test
    void anIntegerResponseCanBeConvertedToALowerCasedString() {
        assertEquals("10", integerResponse.asLowerCasedString());
    }

}