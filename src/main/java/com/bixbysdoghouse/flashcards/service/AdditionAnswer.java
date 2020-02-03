package com.bixbysdoghouse.flashcards.service;

import com.bixbysdoghouse.flashcards.entity.Answer;
import com.bixbysdoghouse.flashcards.entity.IntegerAnswer;
import com.bixbysdoghouse.flashcards.entity.Response;

public class AdditionAnswer extends IntegerAnswer implements Answer {

    public AdditionAnswer(int answer) {
        super(answer);
    }
}
