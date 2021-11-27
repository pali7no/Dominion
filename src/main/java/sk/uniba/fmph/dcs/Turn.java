package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Turn {
    private final DiscardPile discardPile;
    private final Pack pack;
    private final List<BuyDeck> buyDecks;
    private final Hand hand;
    private final Play play;
    private final TurnStatus turnStatus;
    private String phase = "action";


    public Turn(DiscardPile discardPile, Pack pack, List<BuyDeck> buyDecks, Hand hand, Play play, TurnStatus turnStatus) {
        this.discardPile = discardPile;
        this.pack = pack;
        this.buyDecks = buyDecks;
        this.hand = hand;
        this.play = play;
        this.turnStatus = turnStatus;
    }

    public void playCard(int idx) throws RuntimeException {
        if (phase.equals("action")) {
            Optional<CardInterface> cardToPlay = hand.play(this, turnStatus, idx);
            cardToPlay.ifPresent(play::putTo);
        } else {
            throw new RuntimeException("Nemozes hrat vo faze buy.");
        }
    }

    public void buyCard(int buyDeckIdx, int cardIdx) throws RuntimeException {
        if (phase.equals("buy")) {
            CardInterface boughtCard = buyDecks.get(buyDeckIdx).drawByIndex(cardIdx);
            buyDecks.get(buyDeckIdx).getDeck().remove(cardIdx);
            discardPile.addCard(boughtCard);
        } else {
            throw new RuntimeException("Nemozes kupovat vo faze action.");
        }
    }

    public void setPhase(String phase) throws RuntimeException {
        if (phase.equals("action") || phase.equals("buy")) {
            this.phase = phase;
        } else {
            throw new RuntimeException("Fazy su iba buy a action.");
        }
    }

    public void endTurn() {
        List<CardInterface> throwedCards = new ArrayList<>();
        throwedCards.addAll(play.throwAll());
        throwedCards.addAll(hand.throwAll());
        discardPile.addCards(throwedCards);
        discardPile.setCards(discardPile.shuffle());
        pack.addCardsToPackIfNeeded(discardPile);
    }

    public Hand getHand() {
        return hand;
    }
    public Deck getPack() {
        return pack;
    }
    public Play getPlay() {
        return play;
    }
    public TurnStatus getTurnStatus() {
        return turnStatus;
    }
}
