package org.example;

/**
 * Represents a suit of a card.
 */
public enum Suit {
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    SPADES("Spades");

    private String s;

    /**
     * Mandatory constructor comment, yay
     *
     * @param the name
     */
    Suit(String s) {
        this.s = s;
    }

    /**
     * Why do I have to comment toString???.
     */
    @Generated
    public String toString() {
        return this.s;
    }
}
