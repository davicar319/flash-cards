package com.bixbysdoghouse.flashcards.service;

import com.bixbysdoghouse.flashcards.entity.Question;
import lombok.Data;
import lombok.Value;


@Value
public class AdditionQuestion implements Question {
    private int addend1;
    private int addend2;

    @Override
    public String getQuestion() {
        return addend1 + " + " + addend2 + " = ?";
    }
}
