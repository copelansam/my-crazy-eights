package edu.kennesaw.crazy8s.game;

import edu.kennesaw.crazy8s.cardDeck.Deck;
import edu.kennesaw.crazy8s.cardDeck.DiscardPile;
import edu.kennesaw.crazy8s.cards.Card;
import edu.kennesaw.crazy8s.domain.Rank;
import edu.kennesaw.crazy8s.domain.Suit;
import edu.kennesaw.crazy8s.player.Player;

// Stores objects and variables relevant to the current turn (card/suit to match, current player, etc.)
public class TurnContext {
    private Suit currentSuit;
    private Card topDiscard;
    private Player currentPlayer;
    private GameContext gameContext;
    private Rank currentRank;


    public TurnContext(Suit currentSuit,
                       Card topDiscard,
                       GameContext gameContext,
                       Player currentPlayer){
        setCurrentSuit(currentSuit);
        setTopDiscard(topDiscard);
        setCurrentRank(getTopDiscard().getRank());
        setCurrentPlayer(currentPlayer);
        setGameContext(gameContext);

    }

    public Card getTopDiscard() {
        return topDiscard;
    }

    public void setTopDiscard(Card topDiscard) {
        this.topDiscard = topDiscard;
    }

    public Suit getCurrentSuit() {
        return currentSuit;
    }

    public void setCurrentSuit(Suit currentSuit) {
        this.currentSuit = currentSuit;
    }

    public Rank getCurrentRank() {
        return currentRank;
    }

    public void setCurrentRank(Rank currentRank) {
        this.currentRank = currentRank;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameContext getGameContext() {
        return gameContext;
    }

    public void setGameContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }
}
