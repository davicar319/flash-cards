package com.bixbysdoghouse.flashcards.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class FlashCard {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String question;
    private Answer answer;

    public FlashCard() {

    }

    public FlashCard(String question, Answer answer) {
        this.question = question;
        this.answer = answer;
    }
}
