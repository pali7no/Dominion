package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;

// "pack" je balicek kariet, odkial sa taha na zaciatku tahu
public class Pack implements Deck {
    private final List<CardInterface> deck;

    public Pack(List<CardInterface> deck) {
        this.deck = deck;
    }

    @Override
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

    public boolean addCardsToPackIfNeeded(DiscardPile discardPile) {
        if (deck.size() < 5) {
            discardPile.moveDiscardPileToPack(this);
            return true;
        } else {
            return false;
        }
    }

    public List<CardInterface> getDeck() {
        return deck;
    }
}
