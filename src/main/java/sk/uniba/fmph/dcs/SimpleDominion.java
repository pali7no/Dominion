package sk.uniba.fmph.dcs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class SimpleDominion {
    public static void main(String[] args) {
        out.println("Hello!");
        out.println("Let's begin the Dominion game!");
        Game game = new Game(initializeTurn(), initializeBuyDecks());

        Scanner scanner = new Scanner(System.in);
        while(playTurn(game, scanner)) {
            Turn newTurn = new Turn(game.getTurn().getDiscardPile(), game.getTurn().getDeck(),
                    game.getTurn().getHand(), game.getTurn().getPlay(),
                    game.getTurn().getTurnStatus());
            game.setTurn(newTurn);
        }
    }

    public static Turn initializeTurn() {
        out.println("Initializing Turn...");
        DiscardPile discardPile = new DiscardPile(new ArrayList<>());
        Deck deck = new Deck(initializeDeck());
        Hand hand = new Hand(deck.drawFromTop(5));
        Play play = new Play(new ArrayList<>());
        TurnStatus turnStatus = new TurnStatus(0, 0, 0);
        out.println("Done!");
        return new Turn(discardPile, deck, hand, play, turnStatus);
    }

    private static List<CardInterface> initializeDeck() {
        List<CardInterface> initialDeck = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        }
        for (int i = 0; i < 7; ++i) {
            initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        }
        Collections.shuffle(initialDeck);
        return initialDeck;
    }

    public static List<BuyDeck> initializeBuyDecks() {
        BuyDeck buyDeck = new BuyDeck(initializeBuyDeck());
        return new ArrayList<>() {{
            add(buyDeck);
        }};
    }

    public static List<CardInterface> initializeBuyDeck() {
        List<CardInterface> initialDeck = new ArrayList<>();
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_MARKET));
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_ESTATE));
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_COPPER));
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_SMITHY));
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_VILLAGE));
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_FESTIVAL));
        initialDeck.add(new GameCard(GameCardType.GAME_CARD_TYPE_LABORATORY));
        Collections.shuffle(initialDeck);
        return initialDeck;
    }


    public static boolean playTurn(Game game, Scanner scanner) {
        while(true) {
            switch (scanner.next()) {
                case "play" -> {
                    int handIdx = scanner.nextInt();
                    try {
                        game.playCard(handIdx);
                    } catch (RuntimeException runtimeException) {
                        runtimeException.printStackTrace();
                    }
                }
                case "showHand" -> {
                    for (CardInterface card : game.getTurn().getHand().getHandCards()) {
                        out.print(card);
                    }
                }
                case "endPlay" -> game.endPlayCardPhase();
                case "buy" -> {
                    int buyDeckIdx = scanner.nextInt();
                    int buyCardIdx = scanner.nextInt();
                    try {
                        game.buyCard(buyDeckIdx, buyCardIdx);
                    } catch (RuntimeException runtimeException) {
                        runtimeException.printStackTrace();
                    }
                }
                case "showBuy" -> {
                    for (BuyDeck buyDeck : game.getBuyDecks()) {
                        for (CardInterface card : buyDeck.getDeck()) {
                            out.print(card);
                        }
                    }
                }
                case "turnStatus" -> out.println(game.getTurn().getTurnStatus());
                case "points" -> out.println(game.getPoints());
                case "endTurn" -> {
                    try {
                        game.endTurn();
                    } catch (RuntimeException runtimeException) {
                        runtimeException.printStackTrace();
                    }
                    return true;
                }
            }
        }
    }
}
