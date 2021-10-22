import java.util.List;
import java.util.ArrayList;

public class GameStatus{
    static int TotalNumberOfPlayers;
    static int CurrentNumberOfPlayers;
    static int rounds = 1;

    public static void setTotalNumberOfPlayers(int totalNumberOfPlayers) {
        /*
         * This function can only run before the first round start.
         * By using this function, the number of total players will be changed;
         * Max value = 6
         * Min value = 2
         */
        if(rounds == 0) TotalNumberOfPlayers = totalNumberOfPlayers;
    }

    public static int getTotalNumberOfPlayers() {
        /* 
         * This function return the number of total player,
         * including the player is already bankruptcy.
         */
        return TotalNumberOfPlayers;
    }

    public static void setCurrentNumberOfPlayers(int currentNumberOfPlayers) {
        /*
         * This function is to set the current number of players,
         * if a player went bankruptcy, this function will be call.
         * If only 1 player left in the board, winner will be that remaining player.
         */
        CurrentNumberOfPlayers = currentNumberOfPlayers;
        if(CurrentNumberOfPlayers == 1) CheckWinner();
    }

    public static int getCurrentNumberOfPlayers() {
        /* 
         * This function return the number of current player.
         */
        return CurrentNumberOfPlayers;
    }

    public static void RoundEnd() {
        /*
         * This function will be called when all player taking their turns once.
         * This function checks the rounds if it is more than 100.
         */
        rounds++;
        if(rounds > 100) CheckWinner();
    }

    public static void CheckWinner(){
        /*
         * This function will be called if there is only 1 player left
         * or after 100 rounds. 
         * This function will check who is the richest player in the game.
         * Tie (multiple winners) is possible.
         */
        // print out winner and stop the game (maybe ask for restart)
        List<Integer> Winner = new ArrayList<Integer>();
        if(CurrentNumberOfPlayers == 1){
            for(int i = 0; i < Monopoly.players.length; i++) {
                if(!Monopoly.players[i].bankruptcy) Winner.add(Monopoly.players[i].Id);
            }
        }else{
            int max = -1;
            // Winner List is contain player with the most amount of money.
            for(int i = 0; i < Monopoly.players.length; i++){
                if(Monopoly.players[i].Money > max){
                    max = Monopoly.players[i].Money;
                    Winner.clear();
                    Winner.add(Monopoly.players[i].Id);
                }else if(Monopoly.players[i].Money == max){
                    Winner.add(Monopoly.players[i].Id);
                }
            }
        }
    }
}