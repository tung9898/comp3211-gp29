package Controller;
import java.util.ArrayList;
import java.util.List;

import Model.Player;

public class PlayerController {
    /** 
      *  This controller mainly for actions relates to player
     */

    protected static Player[] players;
    protected static int currentPlayer;

    protected Player model;
    //private PlayerView view;

    public PlayerController(Player model/* , PlayerView view */){
        this.model = model;
        //this.view = view;
    }

    public void createPlayer(){
        /**
         * This function will create a player
         */
    }

    public void removePlayer(){
        /**
         * This function will remove a player
         */
    }

    public void retirePlayer(){
        /**
         * This function will retire a player
         */
    }

    public void addMoney(int income){
        /**
         * This function will add the player's money by the income.
         */
        int money = model.getMoney();
        money += income;
        model.setMoney(money);
    }

    public void reduceMoney(int outcome){
        /**
         * This function will subtract the player's money by the income.
         */
        int money = model.getMoney();
        money -= outcome;
        model.setMoney(money);
    }

    //***not yet tested
    public static int PayTax() {
        /*
         * This function will take the player 10% of his/her money for tax.
         * */
        return TaxCalculate(PlayerController.getCurrentPlayer());
    }

    public static int TaxCalculate(int id) {
       int x = PlayerController.getPlayerMoney(id);
       x = x - (x / 10) * 9;
       x = x - x % 10;
       return x;
    }

    public void receiveTax(){
        /**
         * This function will control receive tax
        */
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
        for(int i = 0; i < players.length; i++) {
            if(!players[i].getBankruptcy()) Winner.add(players[i].getId());
        }
    }

    public static Player[] getPlayers(){
        /**
         * This function will return the list of the player
         */
        return players;
    }

    public static void setPlayers(int number){
        /**
         * This function will create the player list
         */
        players = new Player[number];
    }

    public static void setPlayer(int number){
        /**
         * This function will create a player
         */
        players[number] = new Player(number);
    }

    public static void setPlayersMoney(int playerNum, int money){
        /**
         * This function will set money of a player
         */
        players[playerNum].setMoney(money);
    }

    public static int getPlayerMoney(int playerNum){
        /**
         * This function will get money of a player
         */
        return players[playerNum].getMoney();
    }

    public static void setCurrentPlayer(int number) {
        /**
         * This function will set current player number
         */
        currentPlayer = number;
    }

    public static int getCurrentPlayer() {
        /**
         * This function will get current player number
         */
        return currentPlayer;
    }

    public static boolean getPlayerBankruptcy(int playerNum) {
        /**
         * This function will get bankruptcy status of a player
         */
        return players[playerNum].getBankruptcy();
    }

    public static void setPlayerBankruptcy(int playerNum) {
        /**
         * This function will set current square of a player
         */
        players[playerNum].setBankruptcy();
    }

    public static int getDaysInJail(int playerNum) {
        /**
         * This function will get days in jail of a player
         */
        return players[playerNum].getDaysInJail();
    }

    public static void setDaysInJail(int playerNum, int days) {
        /**
         * This function will set current square of a player
         */
        players[playerNum].setDaysInJail(days);
    }

    public static int getPlayerCurrentSquare(int playerNum){
        /**
         * This function will get current square of a player
         */
        return players[playerNum].getCurrentSquare();
    }

    public static void setPlayerCurrentSquare(int playerNum, int position) {
        /**
         * This function will set current square of a player
         */
        players[playerNum].setCurrentSquare(position);
    }

}
