package comp3211;

import java.util.Random;

import comp3211.Controller.ActionController;
import comp3211.Controller.StatusController;
import comp3211.Model.GameStatus;
import comp3211.Model.Player;
import comp3211.Model.Square;
import comp3211.Model.SquareEffect;

public class Monopoly{
    /**
     * Declare the borad, using a list of object.
     */
    static Random rand = new Random();
    public static Square[] board = new Square[20];
    public static Player[] players;
    public static int CurrentPlayer;
    static int numberOfDice = 2;
    static int numberOfSide = 4;
    static int MaximumNumberOfPlayer = 6;
    static int MinimumNumberOfPlayer = 2;

    static StatusController statusController = new StatusController(new GameStatus());
    static ActionController actionController = new ActionController();

    public static void main(String[] args) {
        /**
         * This funcion is the main function of Monopoly.
         */
        // init board using list, make a list of square, from 0 to 19
        // request a number from a range, use for the number of player.
        // Ask for number of players input.
        int numberOfPlayer = 4;
        if(MinimumNumberOfPlayer <= numberOfPlayer && numberOfPlayer <= MaximumNumberOfPlayer) InitSqaure(numberOfPlayer);
        // start the game
        boolean keepTheGameRun = true;
        while(keepTheGameRun) {
            GameStart();
            // ask for input if player want to continue;
                // break if necessary
            // ask for input if player want to change to number of player;
                // call InitSquare if necessary
        }
        // end
    }

    public static void GameStart(){
        int turns = statusController.getCurrentNumberOfPlayers();
        while(true){
            for(int i = 0; i < turns; i++){
                if(players[i].getBankruptcy()) continue;
                CurrentPlayer = i;
                if(players[i].getDaysInJail() > 0) {
                    // Check the player in jail days, if it is not -1, thats mean the player is in jail.
                }
                int[] dice = actionController.rollingDice();
                PlayerMakeAMove(dice[0] + dice[1]);
                // turns--;
            }
            statusController.RoundEnd();
        }
    }

    public static void InitSqaure(int numberOfPlayer) {
        statusController.setTotalNumberOfPlayers(numberOfPlayer);
        statusController.setCurrentNumberOfPlayers(numberOfPlayer);
        players = new Player[numberOfPlayer];
        for(int i = 0; i < numberOfPlayer; i++) {
            board[i].setId(i);
            board[i].setOwner(-i);
        }
        for(int i = 0; i < numberOfPlayer; i++) {
            players[i].setId(i);
            players[i].setCurrentSquare(0);
            players[i].setMoney(SquareEffect.GoSalary());
            // Ask for name input for each player.
        }
    }

    public static void bankruptcy(int id) {
        /**
         * This function will done all the things after a player has a
         * negative amount of money.
         * Set all of his/her properties to unowned.
         */
        for(int i = 0; i < board.length; i++){
            if(board[i].getOwner() == id) board[i].setId(-1);
        }
        players[id].setBankruptcy(true);
        statusController.setCurrentNumberOfPlayers(statusController.getCurrentNumberOfPlayers()-1);
    
    }

    public static void PlayerMakeAMove(int move) {
        /**
         * This function will help player to make a move.
         */
        // check if player is in jail
        // return if jail is not break
        // int oldpos = current player pos;
        // int newpos = oldpos + move;
        // if newpos > 19, newpos -= 20;
        // current player pos = newpos; // delete old pos and add new pos in Square.java
        // newpos sqaure effect

        // Finally, Check player money. If less than 0, declare bankruptcies.
    }

    public static void SquarePurchase(int squareId) {
        // get the integer of that square price and the amount of money that player have.
        int landPrice = board[squareId].getPrice();
        int balance = players[CurrentPlayer].getMoney();
        // check if the player has enough money to make this deal.
        if(balance >= landPrice){
            // confirm message, return if fail to confirm.
            players[CurrentPlayer].setMoney(balance-landPrice);
            board[squareId].setOwner(CurrentPlayer);
            // successful message
        }else{
            // fail to pay message
        }
    }

    public static void SquarePayRent(int squareId) {
        // get the integer of that square rent and the amount of money that player have.
        int landRent = board[squareId].getRent();
        int owner = board[squareId].getOwner();
        int balance = players[CurrentPlayer].getMoney();
        // directly pay the rent for the player.
        players[CurrentPlayer].setMoney(balance-landRent);
        players[owner].setMoney(players[owner].getMoney()+landRent);
        // money remaining message
    }
}