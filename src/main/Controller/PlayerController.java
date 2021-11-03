package Controller;
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

    public void removePlater(){
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

    public void payTax(){
        /**
         * This function will control pay tax
         */
    }

    public void receiveTax(){
        /**
         * This function will control receive tax
         */
    }

    public static Player[] getPlayers(){
        return players;
    }

    public static void setPlayers(int number){
        players = new Player[number];
    }

    public static void setPlayer(int number){
        players[number] = new Player(number);
    }

    public static void setPlayersMoney(int playerNum, int money){
        players[playerNum].setMoney(money);
    }

    public static int getPlayerMoney(int playerNum){
        return players[playerNum].getMoney();
    }

    public static void setCurrentPlayer(int number) {
        currentPlayer = number;
    }

    public static int getCurrentPlayer() {
        return currentPlayer;
    }

    public static boolean getPlayerBankruptcy(int playerNum) {
        return players[playerNum].getBankruptcy();
    }

    public static int getDaysInJail(int playerNum) {
        return players[playerNum].getDaysInJail();
    }

    public static int getPlayerCurrentSquare(int playerNum){
        return players[playerNum].getCurrentSquare();
    }
}
