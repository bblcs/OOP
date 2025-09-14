package org.example;

/**
 * Represents a rank of a card.
 */
public enum Rank {
    ACE("Ace", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("Jack", 10),
    QUEEN("Queen", 10),
    KING("King", 10);

    private String s;
    private int val;

    /**
     * Mandatory constructor comment, yay
     *
     * @param the name
     */
    Rank(String s, int val) {
        this.s = s;
        this.val = val;
    }

    /**
     * Value of the Rank. Ace is 1.
     *
     * @return value of the Rank.
     */
    int value() {
        return this.val;
    }

    /**
     * Why do I have to comment toString???.
     */
    public String toString() {
        return this.s;
    }

}
