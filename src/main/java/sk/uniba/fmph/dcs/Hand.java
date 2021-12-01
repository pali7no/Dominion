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
        CardInterface cardToPlay = hand.get(idx);
        if (game.getTurn().getTurnStatus().coins >= cardToPlay.cardType().getCost()) {
            cardToPlay.evaluate(game);
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

    public List<CardInterface> getHand() {
        return hand;
    }
}
