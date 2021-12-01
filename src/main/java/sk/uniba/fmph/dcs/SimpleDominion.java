package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class SimpleDominion {
    public static void main(String[] args) {
        out.println("Hello!");
        out.println("Let's begin the Dominion game!");
        Game game = new Game(initializeTurn());

        Scanner scanner = new Scanner(System.in);
        while(playTurn(game, scanner)) {
            Turn newTurn = new Turn(game.getTurn().getDiscardPile(), game.getTurn().getDeck(),
                    game.getTurn().getBuyDecks(), game.getTurn().getHand(), game.getTurn().getPlay(),
                    game.getTurn().getTurnStatus());
            game.setTurn(newTurn);
        }
    }

    public static Turn initializeTurn() {
        out.println("Initializing Turn...");
        DiscardPile discardPile = new DiscardPile(new ArrayList<CardInterface>());
        Deck deck = new Deck(initializeDeck());
        BuyDeck buyDeck = new BuyDeck(new ArrayList<CardInterface>());
        List<BuyDeck> buyDecks = new ArrayList<BuyDeck>();
        buyDecks.add(buyDeck);
        List<CardInterface> handCards = new ArrayList<CardInterface>();
        handCards.add(new GameCard(GameCardType.GAME_CARD_TYPE_MARKET));
        Hand hand = new Hand(handCards);
        Play play = new Play(new ArrayList<CardInterface>());
        TurnStatus turnStatus = new TurnStatus();

        return new Turn(discardPile, deck, buyDecks, hand, play, turnStatus);
    }

    public static List<CardInterface> initializeDeck() {
        List<CardInterface> initialDeck = new ArrayList<>();
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_MARKET));
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_SMITHY));
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_VILLAGE));
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_FESTIVAL));
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        return initialDeck;
    }

    public static boolean playTurn(Game game, Scanner scanner) {
        while(true) {
            switch (scanner.next()) {
                case "play":
                    int handIdx = scanner.nextInt();
                    game.playCard(handIdx);
                    break;
                case "endPlay":
                    game.endPlayCardPhase();
                    break;
                case "buy":
                    int buyDeckIdx = scanner.nextInt();
                    int buyCardIdx = scanner.nextInt();
                    game.buyCard(buyDeckIdx, buyCardIdx);
                    break;
                case "endTurn":
                    game.endTurn();
                    return true;
            }
        }
    }
}