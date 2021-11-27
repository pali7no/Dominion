package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class BuyDeck implements Deck {
    private final List<CardInterface> deck;

    public BuyDeck(List<CardInterface> deck, boolean isBuyDeck) {
        this.deck = deck;
    }

    public CardInterface drawByIndex(int idx) {
        CardInterface cardToDraw = deck.get(idx);
        deck.remove(idx);
        return cardToDraw;
    }

    public ArrayList<CardInterface> drawFromTop(int count) throws RuntimeException {
        throw new RuntimeException("Toto je metoda Packu.");
    }

    public List<CardInterface> getDeck() {
        return deck;
    }
}
