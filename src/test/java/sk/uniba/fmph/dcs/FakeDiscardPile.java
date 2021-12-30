package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeDiscardPile {
    List<CardInterface> fakeDiscardPile;
    public FakeDiscardPile(List<CardInterface> fakeDiscardPile) {
        fakeDiscardPile = new ArrayList<CardInterface>() {{
            add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        }};
    }
    public Optional<CardInterface> getTopCard() {
        return Optional.empty();
    }
    public List<CardInterface> getFakeDiscardPile() {
        return fakeDiscardPile;
    }
}
