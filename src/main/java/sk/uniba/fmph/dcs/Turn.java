package sk.uniba.fmph.dcs;

import java.util.Optional;

public class Turn {
    private DiscardPile discardPile;
    private Deck deck;
    private Hand hand;
    private Play play;
    private TurnStatus turnStatus;

    public Turn(DiscardPile discardPile, Deck deck, Hand hand, Play play,
                TurnStatus turnStatus) {
        this.discardPile = discardPile;
        this.deck = deck;
        this.hand = hand;
        this.play = play;
        this.turnStatus = turnStatus;
    }

    public void playCard(int idx) {
        Optional<CardInterface> cardToPlay = this.getHand().play(this, idx);
        cardToPlay.ifPresent(playedCard -> this.getPlay().putTo(playedCard));
    }

    public Hand getHand() {
        return hand;
    }
    public Deck getDeck() {
        return deck;
    }
    public Play getPlay() {
        return play;
    }
    public TurnStatus getTurnStatus() {
        return turnStatus;
    }
}
