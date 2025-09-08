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
        int value;

        switch (rank) {
            case ACE:
                value = 1;
                break;
            case TWO:
                value = 2;
                break;
            case THREE:
                value = 3;
                break;
            case FOUR:
                value = 4;
                break;
            case FIVE:
                value = 5;
                break;
            case SIX:
                value = 6;
                break;
            case SEVEN:
                value = 7;
                break;
            case EIGHT:
                value = 8;
                break;
            case NINE:
                value = 9;
                break;
            case TEN:
                value = 10;
                break;
            case JACK, QUEEN, KING:
                value = 10;
                break;
            default:
                throw new IllegalStateException("lsp, calm down");
        }

        return value;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card) o;
        return suit == card.suit && rank == card.rank;
    }

    @Generated
    public int hashCode() {
        return java.util.Objects.hash(suit, rank);
    }
}
