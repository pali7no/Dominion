package sk.uniba.fmph.dcs;

import java.util.ArrayList;

public interface Deck {
    public abstract ArrayList<CardInterface> drawFromTop(int count);
    public abstract CardInterface drawByIndex(int idx);
}
