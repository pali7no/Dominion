package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<CardInterface> deckCards;

    public Deck(List<CardInterface> deckCards) {
        this.deckCards = deckCards;
    }

    public ArrayList<CardInterface> drawFromTop(int count) {
        ArrayList<CardInterface> cardsToDraw = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            cardsToDraw.add(deckCards.get(deckCards.size() - 1));
            deckCards.remove(deckCards.size() - 1);
        }
        return cardsToDraw;
    }

    public void addCardsToDeckIfNeeded(DiscardPile discardPile) {
        if (deckCards.size() < 5) {
            discardPile.moveDiscardPileToDeck(this);
        }
    }

    public List<CardInterface> getDeckCards() {
        return deckCards;
    }
}