package sk.uniba.fmph.dcs;

interface CardInterface {
    void evaluate(Game game);
    GameCardType cardType();
    CardInterface clone();
}


