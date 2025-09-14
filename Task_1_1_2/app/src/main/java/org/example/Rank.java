package org.example;

/**
 * Represents a rank of a card.
 */
public enum Rank {
    ACE("Ace"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("Jack"),
    QUEEN("Queen"),
    KING("King");

    private String s;

    /**
     * Mandatory constructor comment, yay
     *
     * @param the name
     */
    Rank(String s) {
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
