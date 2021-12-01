package sk.uniba.fmph.dcs;

import java.util.Optional; 
 
interface SimpleDominionInterface {
    public void playCard(int handIdx) throws RuntimeException;
    public void endPlayCardPhase() throws RuntimeException;
    public void buyCard(int buyDeckIdx, int buyCardIdx) throws RuntimeException;
    public void endTurn() throws  RuntimeException;
}
