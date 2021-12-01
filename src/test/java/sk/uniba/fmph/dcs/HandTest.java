package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

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
//        List<CardInterface> throwed = new ArrayList<>() {{
//           addAll(hand.throwAll());
//        }};
        List<CardInterface> throwed = hand.throwAll();
        System.out.printf("%s", throwed);
        assertFalse(throwed.isEmpty());
    }

    public void setUp() {
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
        setUp();
        assertIsInHand(hand1);
        assertIsInHand(hand2);
    }

    @Test
    public void testThrowAll() {
        setUp();
        assertThrowAll(hand1);
    }
}
