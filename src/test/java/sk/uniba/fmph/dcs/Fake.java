package sk.uniba.fmph.dcs;

class FakeCard implements CardInterface {
    private final GameCardType cardType;

    FakeCard(GameCardType cardType) {
        this.cardType = cardType;
    }

    public void evaluate(FakeTurnStatus t) {
    }

    @Override
    public void evaluate(Game game) {

    }

    public GameCardType cardType() {
        return cardType;
    }

    @Override
    public CardInterface clone() {
        return null;
    }
}

class FakeTurnStatus {
}