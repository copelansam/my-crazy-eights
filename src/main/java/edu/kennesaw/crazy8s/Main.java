package edu.kennesaw.crazy8s;

import edu.kennesaw.crazy8s.cardDeck.Deck;
import edu.kennesaw.crazy8s.cardDeck.DeckInitializer;
import edu.kennesaw.crazy8s.cardDeck.DiscardPile;
import edu.kennesaw.crazy8s.game.CrazyEightsGame;
import edu.kennesaw.crazy8s.player.CpuPlayer;
import edu.kennesaw.crazy8s.player.HumanPlayer;
import edu.kennesaw.crazy8s.player.Player;
import edu.kennesaw.crazy8s.player.PlayerManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Simplified Crazy Eights!");
        System.out.println("Be the first person to empty their hand to win.");
        System.out.println("If the deck runs out of cards, then the person with the least cards is the winner.");
        System.out.println("If both players have the same number of cards, then the game ends in a tie");

        // Create Players
        System.out.print("Please enter your name: ");
        String playerName = scan.nextLine();

        PlayerManager players = new PlayerManager();
        players.addPlayer(new HumanPlayer(playerName));
        players.addPlayer(new CpuPlayer());

        // Initialize Decks
        Deck deck = DeckInitializer.createDeck();
        DiscardPile discardPile = new DiscardPile();

        // Initialize & start game
        CrazyEightsGame game = new CrazyEightsGame(players, deck, discardPile);
        game.start();

        // Close scanner after finishing the game
        scan.close();

    }
}
