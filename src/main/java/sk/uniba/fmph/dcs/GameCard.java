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
    public void evaluate(TurnStatus turnStatus) {
        if (gameCard.getPlusActions() > 0) {
            turnStatus.actions += gameCard.getPlusActions();
        }
        if (gameCard.getPlusBuys() > 0) {
            turnStatus.buys += gameCard.getPlusBuys();
        }
        if (gameCard.getPlusCoins() > 0) {
            turnStatus.coins += gameCard.getPlusCoins();
        }
    }

    @Override
    public GameCardType cardType() {
        return gameCard;
    }
}
