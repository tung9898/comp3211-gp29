package Controller;
import java.util.HashMap;
import java.util.Map;

import Model.GameStatus;
import View.GameStatusView;

public class GameStatusController extends Controller{
    /** 
      *  This controller relates to the game status
     */

    protected static GameStatus model;
    protected static GameStatusView view;

    public GameStatusController(GameStatus model, GameStatusView view){
        this.model = model;
        this.view = view;
    }


    public static void setGameStatus(int _TotalNumberOfPlayers, int _CurrentNumberOfPlayers, int _Rounds){
        model = new GameStatus(_TotalNumberOfPlayers, _CurrentNumberOfPlayers, _Rounds);                
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
        //if(model.getCurrentNumberOfPlayers() == 1) CheckWinner();
    }

    public static void setRounds(int rounds){
        /* 
         * This function calling the setter of round in GameStatus model.
         */
        model.setRounds(rounds);
    }

    public static int getRounds(){
        /* 
         * This function calling the getter of round in GameStatus model.
         */
        return model.getRounds();
    }

    public static boolean RoundEnd() {
        /*
         * This function will be called when all player taking their turns once.
         * This function checks the rounds if it is more than 100.
         */

        if(getRounds() > 100){
            return true;
        } else{
            setRounds(getRounds()+1);
            return false;
        }
    }

    /* public static void CheckWinner(){
        
         * This function will be called if there is only 1 player left
         * or after 100 rounds. 
         * This function will check who is the richest player in the game.
         * Tie (multiple winners) is possible.
         
        // print out winner and stop the game (maybe ask for restart)
        List<Integer> Winner = new ArrayList<Integer>();
        Player[] players = PlayerController.getPlayers();
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
    } */

    public void printRoundStarted(){
        System.out.println(view.printRoundStarted(model.getRounds()));
    }

    public static Map<String, Object> getGameStatusMap(){
        Map<String,Object> gameStatusMap = new HashMap<String, Object>();
        gameStatusMap.put("TotalNumberOfPlayers", model.getTotalNumberOfPlayers());
        gameStatusMap.put("CurrentNumberOfPlayers", model.getCurrentNumberOfPlayers());
        gameStatusMap.put("Rounds", model.getRounds());
        return gameStatusMap;
    }
}
