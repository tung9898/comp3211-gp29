package Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.Player;

public class PlayerController extends Controller{
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

    public void setName(String name) {
        /**
         * This function call player model to set the player name.
         */
        model.setName(name);
    }

    public String getName() {
        /**
         * This function will call player model to return the name of the player
         */
        return model.getName();
    }

    public void setId(int id) {
        /**
         * This function will call player model to set the player ID.
         */
        model.setId(id);
    }

    public int getId() {
        /**
         * This function will call player model to return the ID of the player
         */
        return model.getId();
    }

    public void setMoney(int money){
        /**
         * This function will call player model to set the player's money.
        */
        model.setMoney(money);
    }

    public int getMoney() {
        /**
         * This function will call player model to return the player's money.
         */
        return model.getMoney();
    }

    public void setCurrentSquare(int currentSquare) {
        /**
         * This function will call the player model to set the player location where he/she current on.
         */
        model.setCurrentSquare(currentSquare);
    }

    public int getCurrentSquare() {
        /**
         * This function will call player model to return the location where the player is current on.
         */
        return model.getCurrentSquare();
    }

    public void setDaysInJail(int daysInJail) {
        /**
         * This funtion will call player model to set the player in jail days.
         */
        model.setDaysInJail(daysInJail);
    }


    public int getDaysInJail() {
        /**
         * This function will call player model to return days of in jail of the player.
         */
        return model.getDaysInJail();
    }

    public void setBankruptcy(boolean bankruptcy){
        /**
         * This funtion will call player model to set the bankruptcy status of the player.
         */
        model.setBankruptcy(bankruptcy);
    }

    public boolean getBankruptcy(){
        /**
         * This function will call player model to return the bankruptcy status of the player.
         */
        return model.getBankruptcy();
    }

    public void playerLeave(){
        /**
         * This function will remove a player
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

    public static int PayTax() {
        /*
         * This function will take the player 10% of his/her money for tax.
         * */
        return TaxCalculate(PlayerController.getCurrentPlayer());
    }

    public static int TaxCalculate(int id) {
       int percent = 10;
       int x = PlayerController.getPlayerMoney(id);
       x = x / (100 - percent);
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

    public static Map<String, Object> getPlayerMap(Player player){
        Map<String,Object> playerMap = new HashMap<String, Object>();
        playerMap.put("Name",player.getName());
        playerMap.put("Id",player.getId());
        playerMap.put("Money", player.getMoney());
        playerMap.put("CurrentSquare", player.getCurrentSquare());
        playerMap.put("DaysInJail", player.getDaysInJail());
        playerMap.put("Bankruptcy", player.getBankruptcy());
        return playerMap;
    }

    public static List<Map<String, Object>> getPlayersList(){
        List<Map<String, Object>> playersList = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < players.length; i++){
            playersList.add(getPlayerMap(players[i]));
        }
        return playersList;
    }

    public static void setPlayers(JSONArray _players, int playerLength){
        players = new Player[playerLength];
        _players.forEach( player -> parsePlayerObject( (JSONObject) player ) );
    }

    public static void parsePlayerObject(JSONObject player){
        JSONObject playerObject = (JSONObject) player.get("Player");
        players[((Long) playerObject.get("Id")).intValue()] = new Player( String.valueOf(playerObject.get("Name")), 
                                                                                        ((Long) playerObject.get("Id")).intValue(), 
                                                                                        ((Long) playerObject.get("Money")).intValue(), 
                                                                                        ((Long) playerObject.get("CurrentSquare")).intValue(), 
                                                                                        ((Long) playerObject.get("DaysInJail")).intValue(),
                                                                                        (Boolean) playerObject.get("Bankruptcy"));
        
        // Can delete the printlns
        System.out.println("Name: "+players[((Long) playerObject.get("Id")).intValue()].getName());
        System.out.println("Id: "+players[((Long) playerObject.get("Id")).intValue()].getId());
        System.out.println("Money: "+players[((Long) playerObject.get("Id")).intValue()].getMoney());
        System.out.println("CurrentSquare: "+players[((Long) playerObject.get("Id")).intValue()].getCurrentSquare());
        System.out.println("DaysInJail: "+players[((Long) playerObject.get("Id")).intValue()].getDaysInJail());
        System.out.println("Bankruptcy: "+players[((Long) playerObject.get("Id")).intValue()].getBankruptcy());
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


    public static void setPlayerCurrentSquare(int playerNum, int position) {
        /**
         * This function will set current square of a player
         */
        players[playerNum].setCurrentSquare(position);
    }

    public static int getDaysInJail(int playerNum) {
        /**
         * This function will get days in jail of a player
         */
        return players[playerNum].getDaysInJail();
    }

    public static int getPlayerCurrentSquare(int playerNum){
        /**
         * This function will get current square of a player
         */
        return players[playerNum].getCurrentSquare();
    }

    public static void setPlayerDaysInJail(int playerNum, int days) {
        /**
         * This function will set current square of a player
         */
        players[playerNum].setDaysInJail(days);
    }

    public static void setPlayerBankruptcy(int playerNum) {
        /**
         * This function will set current square of a player
         */
        players[playerNum].setBankruptcy(true);
    }

    public static int[][] leaderboard() {
        int numberOfPlayer = players.length;
        int[][] lb = new int[numberOfPlayer][3]; // 0 = Player Id, 1 = Player Balance
        for(int i = 0; i < numberOfPlayer; i++){
            lb[i][0] = 0;
            lb[i][1] = i;
            lb[i][2] = getPlayerMoney(i);
        }

        Arrays.sort(lb, new Comparator<int[]>() {
            @Override
            public int compare(final int[] entry1, final int[] entry2) {
                return entry1[2] < entry2[2] ? 1 : -1;
            }
        });

        lb[0][0] = 1;
        for(int i = 1, rank = 1; i < numberOfPlayer; i++){
            lb[i][0] = lb[i][2] == lb[i-1][2] ? rank : rank + 1;
            rank += 1;
        }
        return lb;
    }
}
