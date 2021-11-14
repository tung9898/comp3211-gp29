package Model;

import java.util.HashMap;
import java.util.Map;

public class GameStatus{
    protected int TotalNumberOfPlayers;
    protected int CurrentNumberOfPlayers;
    protected int Rounds = 1;

    public GameStatus(int totalNumberOfPlayers, int currentNumberOfPlayers){
        setTotalNumberOfPlayers(totalNumberOfPlayers);
        setCurrentNumberOfPlayers(currentNumberOfPlayers);
    }

    public GameStatus(int totalNumberOfPlayers, int currentNumberOfPlayers, int rounds){
        setTotalNumberOfPlayers(totalNumberOfPlayers);
        setCurrentNumberOfPlayers(currentNumberOfPlayers);
        setRounds(rounds);
    }

    public void setTotalNumberOfPlayers(int totalNumberOfPlayers) {
        /* 
         * This function will set the number of total player.
         */
        TotalNumberOfPlayers = totalNumberOfPlayers;
    }

    public int getTotalNumberOfPlayers() {
        /* 
         * This function return the number of total player,
         * including the player is already bankruptcy.
         */
        return TotalNumberOfPlayers;
    }

    public void setCurrentNumberOfPlayers(int currentNumberOfPlayers) {
        CurrentNumberOfPlayers = currentNumberOfPlayers;
    }

    public int getCurrentNumberOfPlayers() {
        /* 
         * This function return the number of current player.
         */
        return CurrentNumberOfPlayers;
    }

    public void setRounds(int rounds){
        /* 
         * This function will set the game round.
         */
        Rounds = rounds;
    }

    public int getRounds(){
        /* 
         * This function return the game round.
         */
        return Rounds;
    }
}