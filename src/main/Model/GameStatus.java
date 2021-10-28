package src.main.Model;

public class GameStatus{
    protected int TotalNumberOfPlayers;
    protected int CurrentNumberOfPlayers;
    protected int rounds = 0;

    public GameStatus(int totalNumberOfPlayers, int currentNumberOfPlayers){
        setTotalNumberOfPlayers(totalNumberOfPlayers);
        setCurrentNumberOfPlayers(currentNumberOfPlayers);
    }

    public void setTotalNumberOfPlayers(int totalNumberOfPlayers) {
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

    public void setRound(int rounds){
        this.rounds = rounds;
    }

    public int getRounds(){
        return rounds;
    }
}