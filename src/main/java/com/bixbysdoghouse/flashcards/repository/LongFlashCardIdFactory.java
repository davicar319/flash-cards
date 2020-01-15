package com.bixbysdoghouse.flashcards.repository;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

@Component
final class LongFlashCardIdFactory {
    private AtomicLong currentIdNumber = new AtomicLong(0L);

    LongFlashCardId createNextId() {
        return new LongFlashCardId(currentIdNumber.getAndIncrement());
    }
}
