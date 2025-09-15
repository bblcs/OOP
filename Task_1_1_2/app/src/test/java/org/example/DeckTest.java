package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    void testDrawingFromEmptyDeckReturnsNull() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertNull(deck.draw());
    }

    @Test
    void testOriginalCards() {
        Deck deck = new Deck();
        assertEquals(deck.stream().distinct().count(), 52);
    }
}
