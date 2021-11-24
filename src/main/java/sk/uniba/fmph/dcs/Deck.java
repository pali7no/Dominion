package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<CardInterface> deck;
    private DiscardPile discardPile;
    
    public Deck(List<CardInterface> deck, DiscardPile discardPile) {
        this.deck = deck;
        this.discardPile = discardPile;
    }
    
    public ArrayList<CardInterface> draw(int count) {
        ArrayList<CardInterface> cardsToDraw = new ArrayList<>();
        for (int i = 0; i < count; ++i) {
            cardsToDraw.add(deck.get(deck.size() - 1));
            deck.remove(deck.size() - 1);
        }
        
        return cardsToDraw;
    }

    public void timeToShuffle() {
        deck = discardPile.shuffle();
    }
}
