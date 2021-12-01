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
    public void evaluate(Game game) throws RuntimeException{
        if (gameCard.isAction()) {
            if (game.getTurn().getTurnStatus().actions > 0) {
                evaluateGameCard(game);
            } else {
                throw new RuntimeException("Nemas action na zahratie karty.");
            }
        } else {
            evaluateGameCard(game);
        }
    }

    private void evaluateGameCard(Game game) {
        game.getTurn().getTurnStatus().actions += gameCard.getPlusActions();
        game.getTurn().getTurnStatus().buys += gameCard.getPlusBuys();
        game.getTurn().getDiscardPile().addCard(this);
        game.getTurn().getTurnStatus().coins += gameCard.getPlusCoins();
        game.setPoints(game.getPoints() + gameCard.getPoints());
    }

    @Override
    public GameCardType cardType() {
        return gameCard;
    }

    @Override
    public CardInterface clone() {
        return new GameCard(new GameCardType(
                gameCard.getPlusActions(),
                gameCard.getPlusBuys(),
                gameCard.getPlusCards(),
                gameCard.getPlusCoins(),
                gameCard.getPoints(),
                gameCard.getCost(),
                gameCard.isAction(),
                gameCard.getName(),
                gameCard.getDescription()
        ));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.cardType().getName()).append(": \n");
        result.append("Plus actions: ").append(this.cardType().getPlusActions());
        result.append("Plus buys: ").append(this.cardType().getPlusBuys());
        result.append("Plus cards: ").append(this.cardType().getPlusCards());
        result.append("Plus coins: ").append(this.cardType().getPlusCoins());
        result.append("Points: ").append(this.cardType().getPoints());
        result.append("Cost:").append(this.cardType().getCost());
        result.append("Is action: ").append(this.cardType().isAction()).append('\n');
        result.append("Description: ").append(this.cardType().getPlusActions());
        return result.toString();
    }
}
