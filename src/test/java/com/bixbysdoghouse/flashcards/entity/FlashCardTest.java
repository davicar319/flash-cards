package com.bixbysdoghouse.flashcards.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlashCardTest {

    @Test
    void testIdNotIncludedInEquals() {
        assertEquals(new FlashCard(), new FlashCard());
        assertEquals(new FlashCard("This is a test", null),
                new FlashCard("This is a test", null));

    }

}