package sk.uniba.fmph.dcs;

import java.util.List;
import java.util.Optional;

public class Hand {
    List<CardInterface> hand;

    public Hand(List<CardInterface> hand) {
        this.hand = hand;
    }

    public boolean isActionCard(int idx) {
        return hand.get(idx).cardType().isAction();
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
            if (myCard.cardType() == gameCardType.cardType()) {
                return true;
            }
        }
        return false;
    }

    public List<CardInterface> throwAll() {
        List<CardInterface> throwing = hand;
        hand.clear();
        return throwing;
    }
}
