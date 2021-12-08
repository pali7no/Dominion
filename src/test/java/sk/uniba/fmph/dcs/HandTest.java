package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class HandTest {
    private Hand hand1;
    private Hand hand2;

    private void assertIsInHand(Hand hand) {
        CardInterface card1 = new GameCard(GameCardType.GAME_CARD_TYPE_FESTIVAL);
        CardInterface card2 = new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY);
        assertTrue(hand.isInHand(card1));
        assertFalse(hand.isInHand(card2));
    }

    private void assertThrowAll(Hand hand) {
        assertFalse(hand.getHand().isEmpty());
        List<CardInterface> throwed = hand.throwAll();
        System.out.printf("%s", throwed);
        assertFalse(throwed.isEmpty());
    }

    private void assertPlayCard(Game game, int idx, String cardName) {
        game.getTurn().getTurnStatus().coins = 4;
        Hand hand = game.getTurn().getHand();
        assertFalse(hand.getHand().isEmpty());
        assertEquals(hand.getHand().get(idx).cardType().getName(), cardName);
        Optional<CardInterface> playedCard = hand.play(game, idx);
        assertFalse(hand.getHand().isEmpty());
        assertFalse(playedCard.isPresent());
    }

    public void setUpHand() {
        hand1 = new Hand(new ArrayList<>() {{
            add(new GameCard(GameCardType.GAME_CARD_TYPE_MARKET));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_FESTIVAL));
        }});
        hand2 = new Hand(new ArrayList<>() {{
            add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
            add(new GameCard(GameCardType.GAME_CARD_TYPE_FESTIVAL));
        }});
    }

    @Test
    public void testIsInHand() {
        setUpHand();
        assertIsInHand(hand1);
        assertIsInHand(hand2);
    }

    @Test
    public void testThrowAll() {
        setUpHand();
        assertThrowAll(hand1);
    }

    @Test
    public void testPlay() {
        Game game = new Game(SimpleDominion.initializeTurn(), SimpleDominion.initializeBuyDecks());
        assertPlayCard(game, 0, "Market");
    }
}
