package com.bixbysdoghouse.flashcards.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.Value;


@Data
public class SimpleQuestion implements Question {
   @NonNull
    private String question;
}
