package sk.uniba.fmph.dcs;

interface SimpleDominionInterface {
    public void playCard(int handIdx) throws RuntimeException;
    public void endPlayCardPhase() throws RuntimeException;
    public void buyCard(int buyDeckIdx, int buyCardIdx) throws RuntimeException;
    public void endTurn() throws  RuntimeException;
}
