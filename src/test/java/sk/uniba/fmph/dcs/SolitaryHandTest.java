package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.mockito.Mock;

public class SolitaryHandTest {
    private Hand hand1;
    private Hand hand2;
    private GameCard gameCard1;
    private GameCard gameCard2;
    private GameCard gameCard3;
    private Game game;

    @Mock
    public List<String> mockedList;

    public void setUpHand() {
        gameCard1 = mock(GameCard.class);
        when(gameCard1.cardType()).thenReturn(GameCardType.GAME_CARD_TYPE_MARKET);
        when(gameCard1.clone()).thenReturn(mock(GameCard.class));
        gameCard2 = mock(GameCard.class);
        when(gameCard2.cardType()).thenReturn(GameCardType.GAME_CARD_TYPE_FESTIVAL);
        when(gameCard2.clone()).thenReturn(mock(GameCard.class));
        gameCard3 = mock(GameCard.class);
        when(gameCard3.cardType()).thenReturn(GameCardType.GAME_CARD_TYPE_COPPER);
        when(gameCard3.clone()).thenReturn(mock(GameCard.class));
        hand1 = new Hand(new ArrayList<>() {{
            add(gameCard1);
            add(gameCard2);
        }});
        hand2 = new Hand(new ArrayList<>() {{
            add(gameCard3);
            add(gameCard2);
        }});
    }

    public void setUpGame() {
        game = mock(Game.class);
        Turn turn = mock(Turn.class);
        when(game.getTurn()).thenReturn(turn);
        TurnStatus turnStatus = mock(TurnStatus.class);
        when(turn.getTurnStatus()).thenReturn(turnStatus);
        turnStatus.coins = 4;

        when(turn.getHand()).thenReturn(hand1);
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

    @Test
    public void testThrowAll() {
        setUpHand();
        assertThrowAll(hand1);
        assertThrowAll(hand2);
    }

    @Test
    public void testPlay() {
        setUpHand();
        setUpGame();
        assertPlayCard(game, 0, "Market");
    }
}
