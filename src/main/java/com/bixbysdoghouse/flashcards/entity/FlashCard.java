package com.bixbysdoghouse.flashcards.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class FlashCard {
    @EqualsAndHashCode.Exclude
    private FlashCardId id;
    private Question question;
    private Answer answer;

    public FlashCard() {

    }

    public FlashCard(Question question, Answer answer) {
        this.question = question;
        this.answer = answer;
    }

    public boolean isCorrect(Response response) {
        return this.answer.isCorrect(response);
    }
}
