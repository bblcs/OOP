package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HandTest {
    @Test
    void testRevealingWhenAlreadyAllOpen() {
        Hand h = new Hand();

        h.add(new Card(Suit.CLUBS, Rank.ACE));
        h.add(new Card(Suit.CLUBS, Rank.ACE));
        h.add(new Card(Suit.CLUBS, Rank.ACE));

        h.revealCard();
        h.revealCard();
        h.revealCard();
        h.revealCard();
    }

    @Test
    void testToString() {
        Hand h = new Hand();

        h.add(new Card(Suit.CLUBS, Rank.ACE));
        h.add(new Card(Suit.CLUBS, Rank.ACE));
        h.revealCard();

        assertEquals("Ace of Clubs, <back face>", h.toString());
    }
}
