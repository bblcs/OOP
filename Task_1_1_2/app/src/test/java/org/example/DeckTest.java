package org.example;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class DeckTest {
    @Test
    void drawingFromEmptyDeckReturnsNull() {
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertNull(deck.draw());
    }
}
