/* for this tests to work make these few adjustments:
comment these sections in SimpleDominion.java:
for (int i = 0; i < 3; ++i) {
    initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
}

initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
 */

package sk.uniba.fmph.dcs;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class GameTest {
    Game game;

    public void setUpGameAndPlayEverything() {
        game = new Game(SimpleDominion.initializeTurn(), SimpleDominion.initializeBuyDecks());
        for (int i = 0; i < 5; ++i) {
            game.playCard(0);
            assertPlayIsNotEmpty();
        }
    }

    public void assertPlayIsNotEmpty() {
        assertFalse(game.getTurn().getPlay().getPlayArea().isEmpty());
    }

    @Rule
    public ExpectedException runtimeExceptionRule = ExpectedException.none();

    @Test
    public void testPlayCard() {
        runtimeExceptionRule.expect(IndexOutOfBoundsException.class);
        setUpGameAndPlayEverything();
        assertTrue(game.getTurn().getHand().getHandCards().isEmpty());
        //this should cause exception (only 5 cards are in hand)
        game.playCard(0);
    }

    @Test
    public void testBuyCard() {
        setUpGameAndPlayEverything();
        game.endPlayCardPhase();
        int discardPileSize = game.getTurn().getDiscardPile().getCards().size();
        game.buyCard(0, 0);
        assertEquals(discardPileSize + 1, game.getTurn().getDiscardPile().getCards().size());
    }

    @Test
    public void endTurn() {
        setUpGameAndPlayEverything();
        game.endPlayCardPhase();
        game.buyCard(0, 0);
        game.endTurn();

        boolean hasOneDifferentFromCopper = false;
        for (CardInterface card : game.getTurn().getDeck().getDeckCards()) {
            if (!card.cardType().getName().equals("Copper")) {
                hasOneDifferentFromCopper = true;
                break;
            }
        }
        for (CardInterface card : game.getTurn().getHand().getHandCards()) {
            if (!card.cardType().getName().equals("Copper")) {
                hasOneDifferentFromCopper = true;
                break;
            }
        }
        assertTrue(hasOneDifferentFromCopper);
    }
}
