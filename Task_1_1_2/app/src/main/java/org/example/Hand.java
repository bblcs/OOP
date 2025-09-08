package org.example;

import java.util.ArrayList;

/**
 * Represents a hand of a player.
 */
public class Hand {
    private boolean open = false;
    private ArrayList<Card> cards;
    private int revealCount = 0;

    public Hand() {
        cards = new ArrayList<>();
    }

    /**
     * Adds a Card into the Hand.
     *
     * @param card A card to add.
     */
    public void add(Card card) {
        cards.add(card);
    }

    /**
     * Makes all cards in the Hand visible.
     */
    public void open() {
        this.open = true;
    }

    /**
     * Returns sum of the Hand, accounting for the aces.
     *
     * @return the sum.
     */
    public int sum() {
        int nAces = 0;
        int sum = 0;

        for (Card card : cards) {
            sum += card.value();
            if (card.getRank() == Rank.ACE) {
                nAces++;
            }
        }

        while (sum <= 11 && nAces > 0) {
            sum += 10;
            nAces--;
        }

        return sum;

    }

    /**
     * Reveals a card of the hand.
     */
    public void revealCard() {
        if (revealCount < cards.size()) {
            cards.get(revealCount).reveal();
            revealCount++;
        }
    }

    @Generated
    public String toString() {
        ArrayList<String> list = new ArrayList<>();
        for (Card card : cards) {
            if (card.isFaceUp() || open) {
                list.add(card.toString());
            } else {
                list.add("<back face>");
            }
        }

        return String.join(", ", list);
    }
}
