package org.example;

/**
 * Game logic.
 */
public class Game {
    /**
     * Result of a round.
     */
    public enum GameResult {
        PLAYER_WIN, DEALER_WIN, PUSH
    }

    /**
     * State of the game.
     */
    public enum GameState {
        PLAYER_TURN, DEALER_TURN, GAME_OVER
    }

    /**
     * Communicates player's decision.
     */
    public enum PlayerChoice {
        HIT, STAND
    }

    private Hand dealerHand;
    private Hand playerHand;
    private Deck deck;

    private GameState state;
    private GameResult result;

    /**
     * The default constructor.
     */
    public Game() {
        this.dealerHand = new Hand();
        this.playerHand = new Hand();
        this.deck = new Deck();

        playerHand.open();
    }

    /**
     * The debug constructor.
     *
     * @param deck that is predetermined.
     */
    public Game(Deck deck) {
        this.deck = deck;
        this.playerHand = new Hand();
        this.dealerHand = new Hand();
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public GameState getState() {
        return state;
    }

    public GameResult getResult() {
        return result;
    }

    /**
     * Starts the round, adding 2 cards to hands and opening cards needed.
     */
    public void startRound() {
        playerHand.add(deck.draw());
        playerHand.add(deck.draw());

        dealerHand.add(deck.draw());
        dealerHand.add(deck.draw());
        dealerHand.revealCard();

        if (initialBlackjack()) {
            state = GameState.GAME_OVER;
        } else {
            state = GameState.PLAYER_TURN;
        }
    }

    /**
     * Acts on player's choice.
     *
     * @param choice of the player.
     */
    public void playerAction(PlayerChoice choice) {
        if (choice == PlayerChoice.HIT) {
            playerHand.add(deck.draw());
            if (playerHand.sum() > 21) {
                result = GameResult.DEALER_WIN;
                state = GameState.GAME_OVER;
            }
        } else {
            state = GameState.DEALER_TURN;
        }
    }

    /**
     * Acts on the dealer's behalf.
     */
    public void dealerTurn() {
        dealerHand.open();
        while (dealerHand.sum() < 17) {
            dealerHand.add(deck.draw());
        }
        determineWinner();
        state = GameState.GAME_OVER;
    }

    /**
     * Turns the game result into a string.
     *
     * @return a string representing current round's result.
     */
    public String getResultString() {
        switch (result) {
            case PLAYER_WIN:
                return "You win.";
            case DEALER_WIN:
                if (isPlayerBust()) {
                    return "Bust. You lose.";
                }
                return "Dealer wins.";
            case PUSH:
                return "Push.";
            default:
                return null;
        }
    }

    /**
     * Turns the hands into a string.
     *
     * @return a string representing current hands from player's perspective.
     */
    public String getHandStatusString() {
        String dealerStatus = "dealer's hand: " + dealerHand;
        if (state == GameState.DEALER_TURN || state == GameState.GAME_OVER) {
            dealerStatus += " (" + dealerHand.sum() + ")";
        }

        String playerStatus = String.format("your hand: %s (%d)", playerHand, playerHand.sum());

        return dealerStatus + "\n" + playerStatus;
    }

    /**
     * Shows if player has more than 21 points.
     *
     * @return whether the player's hand is busted.
     */
    private boolean isPlayerBust() {
        return playerHand.sum() > 21;
    }

    /**
     * Checks for a blackjack in the first turn.
     *
     * @return whether someone has a black jack.
     */
    private boolean initialBlackjack() {
        boolean playerHasBlackjack = getPlayerHand().sum() == 21;
        boolean dealerHasBlackjack = getDealerHand().sum() == 21;

        if (playerHasBlackjack && dealerHasBlackjack) {
            dealerHand.open();
            result = GameResult.PUSH;
            return true;
        } else if (playerHasBlackjack) {
            result = GameResult.PLAYER_WIN;
            return true;
        } else if (dealerHasBlackjack) {
            dealerHand.open();
            result = GameResult.DEALER_WIN;
            return true;
        }
        return false;
    }

    /**
     * Determines the winner.
     */
    private void determineWinner() {
        int playerSum = playerHand.sum();
        int dealerSum = dealerHand.sum();

        if (dealerSum > 21 || playerSum > dealerSum) {
            result = GameResult.PLAYER_WIN;
        } else if (dealerSum > playerSum) {
            result = GameResult.DEALER_WIN;
        } else {
            result = GameResult.PUSH;
        }
    }

}
