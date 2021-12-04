package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Turn {
    private final DiscardPile discardPile;
    private final Deck deck;
    private final Hand hand;
    private final Play play;
    private final TurnStatus turnStatus;
    private String phase = "action";


    public Turn(DiscardPile discardPile, Deck deck, Hand hand, Play play, TurnStatus turnStatus) {
        this.discardPile = discardPile;
        this.deck = deck;
        this.hand = hand;
        this.play = play;
        this.turnStatus = turnStatus;
    }

    public void playCard(Game game, int handIdx) throws RuntimeException {
        if (phase.equals("action")) {
            Optional<CardInterface> cardToPlay = hand.play(game, handIdx);
            // tu ^ sa aj vyhodnoti, ci ma dost actionov
            if (cardToPlay.isPresent()) {
                play.putTo(cardToPlay.get());
            } else {
                throw new RuntimeException("Nemas dost actionov alebo coinov na zahratie karty.");
            }
        } else {
            throw new RuntimeException("Nemozes hrat vo faze buy.");
        }
    }

    public void setPhase(String phase) throws RuntimeException {
        if (phase.equals("buy")) {
            this.phase = phase;
        } else if (phase.equals("action")) {
            throw new RuntimeException("Nemozes zmenit fazu z buy na action.");
        } else {
            throw new RuntimeException("Fazy su iba buy a action.");
        }
    }

    public void buyCard(Game game, int buyDeckIdx, int cardIdx) throws RuntimeException {
        if (phase.equals("buy")) {
            BuyDeck buyDeck = game.getBuyDecks().get(buyDeckIdx);
            int cardCost = buyDeck.getDeck().get(cardIdx).cardType().getCost();
            if (turnStatus.buys > 0 && turnStatus.coins > cardCost) {
                CardInterface boughtCard = buyDeck.drawByIndex(cardIdx);
//                buyDeck.getDeck().remove(cardIdx);
                discardPile.addCard(boughtCard);
                --turnStatus.buys;
                turnStatus.coins -= cardCost;
            } else {
                throw new RuntimeException("Nemas dost buyov alebo coinov na kupenie karty.");
            }
        } else {
            throw new RuntimeException("Nemozes kupovat vo faze action.");
        }
    }

    public void endTurn() {
        List<CardInterface> throwedCards = new ArrayList<>();
        throwedCards.addAll(play.throwAll());
        throwedCards.addAll(hand.throwAll());
        discardPile.addCards(throwedCards);
        discardPile.setCards(discardPile.shuffle());
        deck.addCardsToDeckIfNeeded(discardPile);
        hand.drawFiveCards(deck);
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
    public DiscardPile getDiscardPile() {
        return discardPile;
    }
    public TurnStatus getTurnStatus() {
        return turnStatus;
    }
    public String getPhase() {
        return phase;
    }
}
