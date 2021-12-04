package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hand {
    private List<CardInterface> hand;

    public Hand(List<CardInterface> hand) {
        this.hand = hand;
    }

    public Optional<CardInterface> play(Game game, int idx) {
        TurnStatus turnStatus = game.getTurn().getTurnStatus();
        CardInterface cardToPlay = hand.get(idx);
        if (turnStatus.coins >= cardToPlay.cardType().getCost() && turnStatus.actions > 0) {
            cardToPlay.evaluate(game);
            hand.remove(idx);
            return Optional.of(cardToPlay);
        } else {
            return Optional.empty();
        }
    }

    boolean isInHand(CardInterface gameCardType) {
        for (CardInterface myCard : hand) {
            if (myCard.cardType().getName().equals(gameCardType.cardType().getName())) {
                return true;
            }
        }
        return false;
    }

    public List<CardInterface> throwAll() {
        List<CardInterface> throwing = new ArrayList<>();
        for (CardInterface card : hand) {
            throwing.add(card.clone());
        }
        hand.clear();
        return throwing;
    }

    public void drawFiveCards(Deck deck) {
        hand.addAll(deck.drawFromTop(5));
    }

    public List<CardInterface> getHand() {
        return hand;
    }
}
