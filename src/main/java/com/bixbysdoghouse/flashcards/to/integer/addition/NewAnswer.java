package com.bixbysdoghouse.flashcards.to.integer.addition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor
public class NewAnswer {
    private int answer;
}
