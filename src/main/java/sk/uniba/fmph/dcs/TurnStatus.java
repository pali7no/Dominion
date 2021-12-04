package sk.uniba.fmph.dcs;

public class TurnStatus {
    public int actions = 0;
    public int buys = 0;
    public int coins = 0;

    public TurnStatus(int plusActions, int plusBuys, int plusCoins) {
        actions += plusActions + 1;
        buys += plusBuys + 1;
        coins += plusCoins;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + actions;
        result = prime * result + buys;
        result = prime * result + coins;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        TurnStatus other = (TurnStatus) obj;
        if (this.actions != other.actions)
            return false;
        if (this.buys != other.buys)
            return false;
        return this.coins == other.coins;
    }

    @Override
    public String toString() {
        return "actions: " + actions +
                "\nbuys: " + buys +
                "\ncoins: " + coins;
    }
}
