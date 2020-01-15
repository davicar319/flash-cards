package com.bixbysdoghouse.flashcards.repository;

import com.bixbysdoghouse.flashcards.entity.FlashCardId;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
final class LongFlashCardId implements FlashCardId {
    private Long id;

    LongFlashCardId(final Long id) {
        this.id = id;
    }
}
