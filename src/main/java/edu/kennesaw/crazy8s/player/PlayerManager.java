package edu.kennesaw.crazy8s.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Keeps track of the players throughout the game
public class PlayerManager {

    private final List<Player> players = new ArrayList<>();
    private int currentPlayerIndex;

    public List<Player> getPlayers(){
        return Collections.unmodifiableList(players);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }

    public void changeCurrentPlayer(){
        currentPlayerIndex = ((currentPlayerIndex + 1) % 2);
    }

    public Player playerChange(){
        changeCurrentPlayer();
        return getCurrentPlayer();
    }

    public int checkSmallestHand(){
        int smallestHand = 52;

        for (Player player: players){
            int playerHandCount = player.getHandSize();
            if (playerHandCount < smallestHand){
                smallestHand = playerHandCount;
            }
        }
        return smallestHand;
    }

    public List<Player> getPlayersWithSmallestHands(){
        int smallestHand = checkSmallestHand();
        List<Player> smallestHandPlayers = new ArrayList<>();
        for (Player player: players){
            if (player.getHandSize() == smallestHand){
                smallestHandPlayers.add(player);
            }
        }
        return Collections.unmodifiableList(smallestHandPlayers);
    }
}
