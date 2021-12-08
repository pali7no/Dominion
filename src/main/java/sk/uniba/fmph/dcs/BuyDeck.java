package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class BuyDeck {
    private final List<CardInterface> deck;

    public BuyDeck(List<CardInterface> deck) {
        this.deck = deck;
    }

    public CardInterface drawByIndex(int idx) {
        CardInterface cardToDraw = deck.get(idx);
        deck.remove(idx);
        return cardToDraw;
    }

    public List<CardInterface> getDeck() {
        return deck;
    }
}
