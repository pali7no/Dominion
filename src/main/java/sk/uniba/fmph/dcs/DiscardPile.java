package sk.uniba.fmph.dcs;

import java.util.Optional; 
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DiscardPile {
    List<CardInterface> cards;

    public DiscardPile(List<CardInterface> cards) {
        this.cards = cards;
    }
        
    public Optional<CardInterface> getTopCard() {
    	if (cards.isEmpty()) return Optional.empty();
        return Optional.of(cards.get(cards.size() - 1));
    }
        
    public void addCards(List<CardInterface> cards) {
        this.cards.addAll(cards);
    }
    public void addCard(CardInterface card) {
        this.cards.add(card);
    }
        
    public int getSize() {
        return cards.size();
    }
        
    public List<CardInterface> shuffle() {
        Collections.shuffle(cards);
        List<CardInterface> cards_to_send = new ArrayList<>() {{
            addAll(cards);
        }};
        cards.clear();
        return cards_to_send;
    }

    public void setCards(List<CardInterface> cards) {
        this.cards = cards;
    }

    public void moveDiscardPileToDeck(Deck deck) {
        deck.getDeckCards().addAll(cards);
        cards.clear();
    }

    public List<CardInterface> getCards() {
        return cards;
    }
}
        
        
