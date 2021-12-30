package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hand {
    private List<CardInterface> handCards;

    public Hand(List<CardInterface> handCards) {
        this.handCards = handCards;
    }

    public Optional<CardInterface> play(Game game, int idx) {
        TurnStatus turnStatus = game.getTurn().getTurnStatus();
        CardInterface cardToPlay = handCards.get(idx);
        if (/*turnStatus.coins >= cardToPlay.cardType().getCost() && */turnStatus.actions > 0) {
            cardToPlay.evaluate(game);
            handCards.remove(idx);
            return Optional.of(cardToPlay);
        } else {
            return Optional.empty();
        }
    }

    boolean isInHand(CardInterface gameCardType) {
        for (CardInterface myCard : handCards) {
            if (myCard.cardType().getName().equals(gameCardType.cardType().getName())) {
                return true;
            }
        }
        return false;
    }

    public List<CardInterface> throwAll() {
        List<CardInterface> throwing = new ArrayList<>();
        for (CardInterface card : handCards) {
            throwing.add(card.clone());
        }
        handCards.clear();
        return throwing;
    }

    public void drawFiveCards(Deck deck) {
        handCards.addAll(deck.drawFromTop(5));
    }

    public List<CardInterface> getHandCards() {
        return handCards;
    }
}
