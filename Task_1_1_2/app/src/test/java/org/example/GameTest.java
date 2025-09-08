package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class GameTest {
    private Deck createPredictableDeck(Card... cards) {
        ArrayList<Card> cardList = new ArrayList<>(Arrays.asList(cards));
        return new Deck() {
            @Override
            public Card draw() {
                if (cardList.isEmpty()) {
                    return null;
                }
                return cardList.remove(0);
            }
        };
    }

    @Test
    void testPlayerBlackjackOnDeal() {
        Game game = new Game(createPredictableDeck(
                new Card(Suit.SPADES, Rank.ACE),  // p
                new Card(Suit.SPADES, Rank.KING), // p
                new Card(Suit.HEARTS, Rank.FIVE), // d
                new Card(Suit.CLUBS, Rank.FIVE)   // d
        ));
        game.startRound();
        assertEquals(Game.GameState.GAME_OVER, game.getState());
        assertEquals(Game.GameResult.PLAYER_WIN, game.getResult());
    }

    @Test
    void testDealerBlackjackOnDeal() {
        Game game = new Game(createPredictableDeck(
                new Card(Suit.SPADES, Rank.TEN),  // p
                new Card(Suit.SPADES, Rank.FIVE), // p
                new Card(Suit.HEARTS, Rank.ACE),  // d
                new Card(Suit.CLUBS, Rank.KING)   // d
        ));
        game.startRound();
        assertEquals(Game.GameState.GAME_OVER, game.getState());
        assertEquals(Game.GameResult.DEALER_WIN, game.getResult());
    }

    @Test
    void testPushBlackjackOnDeal() {
        Game game = new Game(createPredictableDeck(
                new Card(Suit.SPADES, Rank.ACE),  // p
                new Card(Suit.SPADES, Rank.KING), // p
                new Card(Suit.HEARTS, Rank.ACE),  // d
                new Card(Suit.CLUBS, Rank.KING)   // d
        ));
        game.startRound();
        assertEquals(Game.GameState.GAME_OVER, game.getState());
        assertEquals(Game.GameResult.PUSH, game.getResult());
    }

    @Test
    void testPlayerBusts() {
        Game game = new Game(createPredictableDeck(
                new Card(Suit.SPADES, Rank.TEN),   // p
                new Card(Suit.SPADES, Rank.EIGHT), // p
                new Card(Suit.HEARTS, Rank.TEN),   // d
                new Card(Suit.CLUBS, Rank.TWO),    // d
                new Card(Suit.DIAMONDS, Rank.FIVE) // p hit card
        ));
        game.startRound();
        game.playerAction(Game.PlayerChoice.HIT);
        assertEquals(23, game.getPlayerHand().sum());
        assertEquals(Game.GameState.GAME_OVER, game.getState());
        assertEquals(Game.GameResult.DEALER_WIN, game.getResult());
    }

    @Test
    void testPlayerStandsAndWinsWhenDealerBusts() {
        Game game = new Game(createPredictableDeck(
                new Card(Suit.SPADES, Rank.TEN),    // p
                new Card(Suit.SPADES, Rank.EIGHT),  // p (18)
                new Card(Suit.HEARTS, Rank.TEN),    // d
                new Card(Suit.CLUBS, Rank.SIX),     // d
                new Card(Suit.DIAMONDS, Rank.SEVEN) // d hit card (23)
        ));
        game.startRound();
        game.playerAction(Game.PlayerChoice.STAND);
        game.dealerTurn();
        assertEquals(Game.GameResult.PLAYER_WIN, game.getResult());
    }

    @Test
    void testPlayerStandsAndWinsWithHigherScore() {
        Game game = new Game(createPredictableDeck(
                new Card(Suit.SPADES, Rank.TEN),  // p
                new Card(Suit.SPADES, Rank.NINE), // p (19)
                new Card(Suit.HEARTS, Rank.TEN),  // d
                new Card(Suit.CLUBS, Rank.SEVEN)  // d (17) -> Dealer stands
        ));
        game.startRound();
        game.playerAction(Game.PlayerChoice.STAND);
        game.dealerTurn();
        assertEquals(19, game.getPlayerHand().sum());
        assertEquals(17, game.getDealerHand().sum());
        assertEquals(Game.GameResult.PLAYER_WIN, game.getResult());
    }

    @Test
    void testDealerStandsAndWins() {
        Game game = new Game(createPredictableDeck(
                new Card(Suit.SPADES, Rank.TEN),   // p
                new Card(Suit.SPADES, Rank.EIGHT), // p (18)
                new Card(Suit.HEARTS, Rank.TEN),   // d
                new Card(Suit.CLUBS, Rank.NINE)    // d (19) -> Dealer stands
        ));
        game.startRound();
        game.playerAction(Game.PlayerChoice.STAND);
        game.dealerTurn();
        assertEquals(18, game.getPlayerHand().sum());
        assertEquals(19, game.getDealerHand().sum());
        assertEquals(Game.GameResult.DEALER_WIN, game.getResult());
    }

    @Test
    void testLongPush() {
        Game game = new Game(createPredictableDeck(
                new Card(Suit.SPADES, Rank.FIVE),   // p
                new Card(Suit.SPADES, Rank.KING),   // p
                new Card(Suit.HEARTS, Rank.FIVE),   // d
                new Card(Suit.CLUBS, Rank.QUEEN),   // d
                new Card(Suit.DIAMONDS, Rank.FIVE), // p (20)
                new Card(Suit.HEARTS, Rank.FIVE)    // d (20)
        ));
        game.startRound();
        game.playerAction(Game.PlayerChoice.HIT);
        game.dealerTurn();
        game.playerAction(Game.PlayerChoice.STAND);
        game.dealerTurn();
        assertEquals(Game.GameResult.PUSH, game.getResult());
    }
}
