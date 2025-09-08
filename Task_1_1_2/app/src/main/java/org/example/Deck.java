package org.example;

import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a deck of 52 cards.
 */
public class Deck {
    private ArrayList<Card> cards;
    private Random rand;

    /**
     * A default constructor.
     */
    public Deck() {
        cards = new ArrayList<>();
        rand = new Random(System.currentTimeMillis());
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        shuffle(cards, rand);
    }

    /**
     * A size of the deck.
     *
     * @return the size of the deck.
     */
    @Generated
    public int size() {
        return cards.size();
    }

    /**
     * Removes a card from the deck.
     *
     * @return a card if deck has cards, a null else.
     */
    public Card draw() {
        if (size() == 0) {
            return null;
        }

        return cards.remove(rand.nextInt(size()));
    }
}
