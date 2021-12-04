package sk.uniba.fmph.dcs;

import java.util.List;

public class Game implements SimpleDominionInterface {
    private Turn turn;
    private final List<BuyDeck> buyDecks;
    private int points = 0;

    public Game(Turn turn, List<BuyDeck> buyDecks) {
        this.turn = turn;
        this.buyDecks = buyDecks;
    }

    public void playCard(int handIdx) throws RuntimeException{
        turn.playCard(this, handIdx);
    }
    public void endPlayCardPhase() throws RuntimeException {
        turn.setPhase("buy");
    }

    public void buyCard(int buyDeckIdx, int buyCardIdx) throws RuntimeException{
        turn.buyCard(this, buyDeckIdx, buyCardIdx);
    }

    public void endTurn() throws RuntimeException{
        turn.endTurn();
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public List<BuyDeck> getBuyDecks() {
        return buyDecks;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
