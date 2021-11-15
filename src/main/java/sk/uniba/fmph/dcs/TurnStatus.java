package sk.uniba.fmph.dcs;

public class TurnStatus {
    public int actions;
    public int buys;
    public int coins;
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
        if (this.coins != other.coins)
            return false;
        return true;
    }
}
