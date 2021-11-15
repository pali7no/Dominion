package sk.uniba.fmph.dcs;

public class GameCard implements CardInterface{
    private final GameCardType gameCard;

    public GameCard(GameCardType gameCardType) {
        this.gameCard = new GameCardType(
                gameCardType.getPlusActions(),
                gameCardType.getPlusBuys(),
                gameCardType.getPlusCards(),
                gameCardType.getPlusCoins(),
                gameCardType.getPoints(),
                gameCardType.getCost(),
                gameCardType.isAction(),
                gameCardType.getName(),
                gameCardType.getDescription()
        );
    }

    @Override
    public void evaluate(TurnStatus ts) {
        if (gameCard.getPlusActions() > 0) {
            ts.actions += gameCard.getPlusActions();
        }
        if (gameCard.getPlusBuys() > 0) {
            ts.buys += gameCard.getPlusBuys();
        }
        if (gameCard.getPlusCoins() > 0) {
            ts.coins += gameCard.getPlusCoins();
        }
    }

    @Override
    public GameCardType cardType() {
        return gameCard;
    }
}
