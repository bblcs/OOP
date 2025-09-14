package org.example;

/**
 * Class that represents a single card.
 */
public class Card {
    private boolean face;
    private Suit suit;
    private Rank rank;

    /**
     * Full constructor.
     *
     * @param suit of the card.
     * @param rank of the card.
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.face = false;
    }

    /**
     * Returns whether the card is facing upward.
     *
     * @return bebebebeb.
     */
    @Generated
    public boolean isFaceUp() {
        return face;
    }

    /**
     * Returns the card's suit.
     *
     * @return suit.
     */
    @Generated
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the card's suit.
     *
     * @return suit.
     */
    @Generated
    public Rank getRank() {
        return rank;
    }

    /**
     * Calculates the value of the card. Ace is 1.
     *
     * @return value of the card.
     */
    @Generated
    public int value() {
        return rank.value();
    }

    /**
     * Makes the card face upward.
     */
    @Generated
    public void reveal() {
        face = true;
    }

    @Generated
    public String toString() {
        return rank.toString() + " of " + suit.toString();
    }

    @Generated
    public int hashCode() {
        return java.util.Objects.hash(suit, rank);
    }
}
