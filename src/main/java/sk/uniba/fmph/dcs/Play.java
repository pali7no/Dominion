package sk.uniba.fmph.dcs;

import java.util.List;

public class Play {
    private List<CardInterface> playArea;

    public Play(List<CardInterface> playArea) {
        this.playArea = playArea;
    }

    public void putTo(CardInterface playedCard) {
        playArea.add(playedCard);
    }

    public List<CardInterface> throwAll() {
        List<CardInterface> throwing = playArea;
        playArea.clear();
        return throwing;
    }
}
