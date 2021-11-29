package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<CardInterface> deck;

    public Deck(List<CardInterface> deck) {
        this.deck = deck;
    }

    public ArrayList<CardInterface> drawFromTop(int count) {
        ArrayList<CardInterface> cardsToDraw = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            cardsToDraw.add(deck.get(deck.size() - 1));
            deck.remove(deck.size() - 1);
        }
        return cardsToDraw;
    }

    public CardInterface drawByIndex(int idx) throws RuntimeException {
        throw new RuntimeException("Nemozes si tahat lubovolnu kartu z packu.");
    }

    public void addCardsToPackIfNeeded(DiscardPile discardPile) {
        if (deck.size() < 5) {
            discardPile.moveDiscardPileToPack(this);
        }
    }

    public List<CardInterface> getDeck() {
        return deck;
    }
}