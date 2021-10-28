package src.main.Controller;
import java.util.ArrayList;
import java.util.List;

import src.main.Model.GameStatus;
import src.main.Model.Player;
import src.main.Service.PlayerService;

public class StatusController {
    private static GameStatus model;
    //private GameStatusView view;

    public StatusController(GameStatus model){
        this.model = model;
    }
    
    public static int getTotalNumberOfPlayers() {
        /* 
         * This function return the number of total player,
         * including the player is already bankruptcy.
         */
        return model.getCurrentNumberOfPlayers();
    }

    public static void setTotalNumberOfPlayers(int totalNumberOfPlayers) {
        /*
         * This function can only run before the first round start.
         * By using this function, the number of total players will be changed;
         * Max value = 6
         * Min value = 2
         */
        if(model.getRounds() == 0) model.setTotalNumberOfPlayers(totalNumberOfPlayers);
    }

    public static int getCurrentNumberOfPlayers() {
        /* 
         * This function return the number of current player.
         */
        return model.getCurrentNumberOfPlayers();
    }

    public static void setCurrentNumberOfPlayers(int currentNumberOfPlayers) {
        /*
         * This function is to set the current number of players,
         * if a player went bankruptcy, this function will be call.
         * If only 1 player left in the board, winner will be that remaining player.
         */
        model.setCurrentNumberOfPlayers(currentNumberOfPlayers);
        if(model.getCurrentNumberOfPlayers() == 1) CheckWinner();
    }

    public static void setRound(int rounds){
        model.setRound(rounds);
    }

    public static int getRounds(){
        return model.getRounds();
    }

    public static void RoundEnd() {
        /*
         * This function will be called when all player taking their turns once.
         * This function checks the rounds if it is more than 100.
         */
        int rounds = model.getRounds();
        model.setRound(rounds++);
        if(model.getRounds() > 100) CheckWinner();
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
        Player[] players = PlayerService.getPlayers();
        if(model.getCurrentNumberOfPlayers() == 1){
            for(int i = 0; i < players.length; i++) {
                if(!players[i].getBankruptcy()) Winner.add(players[i].getId());
            }
        }else{
            int max = -1;
            // Winner List is contain player with the most amount of money.
            for(int i = 0; i < players.length; i++){
                if(players[i].getMoney() > max){
                    max = players[i].getMoney();
                    Winner.clear();
                    Winner.add(players[i].getId());
                }else if(players[i].getMoney() == max){
                    Winner.add(players[i].getId());
                }
            }
        }
    }
}
