package sk.uniba.fmph.dcs;

public class Game implements SimpleDominionInterface {
    private Turn turn;
    private int points = 0;

    public Game(Turn turn) {
        this.turn = turn;
    }

    public void playCard(int handIdx) throws RuntimeException{
        turn.playCard(this, handIdx);
    }
    public void endPlayCardPhase() throws RuntimeException {
        turn.setPhase("buy");
    }

    public void buyCard(int buyDeckIdx, int buyCardIdx) throws RuntimeException{
        turn.buyCard(buyDeckIdx, buyCardIdx);
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
