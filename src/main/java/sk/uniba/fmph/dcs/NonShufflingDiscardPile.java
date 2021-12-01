package sk.uniba.fmph.dcs;

import java.util.List;

public class NonShufflingDiscardPile extends DiscardPile{
    public NonShufflingDiscardPile(List<CardInterface> cards) {
        super(cards);
    }

    @Override
    public List<CardInterface> shuffle() {
        return super.getCards();
    }
}
