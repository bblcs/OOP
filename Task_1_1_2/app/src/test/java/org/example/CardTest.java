package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CardTest {
    @Test
    void testToString() {
        Card c = new Card(Suit.CLUBS, Rank.ACE);
        assertEquals("Ace of Clubs", c.toString());
    }
}
