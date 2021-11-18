package Controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.PlainView;

import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.Player;
import View.PlayerView;

public class PlayerController extends Controller{
    /** 
      *  This controller mainly for actions relates to player
     */

    protected static Player[] players;
    protected static int currentPlayer;

    protected Player model;
    protected PlayerView view = new PlayerView();

    public PlayerController(){}
    public PlayerController(Player model/* , PlayerView view */){
        this.model = model;
        //this.view = view;
    }

    public void setName(String name) {
        /*
          This function call player model to set the player name.
         */
        model.setName(name);
    }

    public String getName() {
        /*
          This function will call player model to return the name of the player
         */
        return model.getName();
    }

    public void setId(int id) {
        /*
          This function will call player model to set the player ID.
         */
        model.setId(id);
    }

    public int getId() {
        /*
          This function will call player model to return the ID of the player
         */
        return model.getId();
    }

    public void setMoney(int money){
        /*
          This function will call player model to set the player's money.
        */
        model.setMoney(money);
    }

    public int getMoney() {
        /*
          This function will call player model to return the player's money.
         */
        return model.getMoney();
    }

    public void setCurrentSquare(int currentSquare) {
        /*
          This function will call the player model to set the player location where he/she is current on.
         */
        model.setCurrentSquare(currentSquare);
    }

    public int getCurrentSquare() {
        /*
          This function will call player model to return the location where the player is current on.
         */
        return model.getCurrentSquare();
    }

    public void setDaysInJail(int daysInJail) {
        /*
          This function will call player model to set the player in jail days.
         */
        model.setDaysInJail(daysInJail);
    }


    public int getDaysInJail() {
        /*
          This function will call player model to return days of in jail of the player.
         */
        return model.getDaysInJail();
    }

    public void setBankruptcy(boolean bankruptcy){
        /*
          This function will call player model to set the bankruptcy status of the player.
         */
        model.setBankruptcy(bankruptcy);
    }

    public boolean getBankruptcy(){
        /*
          This function will call player model to return the bankruptcy status of the player.
         */
        return model.getBankruptcy();
    }

    public void playerLeave(){
        /*
          This function will remove a player
         */
    }

    public void addMoney(int income){
        /*
          This function will add the player's money by the income.
         */
        int money = model.getMoney();
        money += income;
        model.setMoney(money);
    }

    public void reduceMoney(int outcome){
        /*
          This function will subtract the player's money by the income.
         */
        int money = model.getMoney();
        money -= outcome;
        model.setMoney(money);
    }

    public int PayTax() {
        /*
         * This function will take the player 10% of his/her money for tax.
         * */
        return TaxCalculate(currentPlayer);
    }

    public int TaxCalculate(int id) {
       int percent = 10;
       int x = this.getPlayerMoney(id);
       x = x / (100 - percent);
       x = x - x % 10;
       return x;
    }

    public List<String> CheckWinner(){
        /*
         * This function will be called if there is only 1 player left
         * or after 100 rounds. 
         * This function will check who is the richest player in the game.
         * Tie (multiple winners) is possible.
         */
        // print out winner and stop the game (maybe ask for restart)
        List<String> Winner = new ArrayList<String>();
        int amount = 0;
        for(int i = 0; i < players.length; i++) {
            if(players[i].getMoney() > amount){
                amount = players[i].getMoney();
                Winner = new ArrayList<String>();
                Winner.add(players[i].getId() + "(" + players[i].getName() + ")" );
            } 
            else if (players[i].getMoney() == amount){
                amount = players[i].getMoney();
                Winner.add(players[i].getId() + "(" + players[i].getName() + ")" );
            }
        }
        return Winner;
    }

    public void printWinner(){
        System.out.println(view.printWinner(CheckWinner()));
    }

    public Map<String, Object> getPlayerMap(Player player){
        Map<String,Object> playerMap = new HashMap<String, Object>();
        playerMap.put("Name",player.getName());
        playerMap.put("Id",player.getId());
        playerMap.put("Money", player.getMoney());
        playerMap.put("CurrentSquare", player.getCurrentSquare());
        playerMap.put("DaysInJail", player.getDaysInJail());
        playerMap.put("Bankruptcy", player.getBankruptcy());
        return playerMap;
    }

    public List<Map<String, Object>> getPlayersList(){
        List<Map<String, Object>> playersList = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < players.length; i++){
            playersList.add(getPlayerMap(players[i]));
        }
        return playersList;
    }

    public void setPlayers(JSONArray _players, int playerLength){
        players = new Player[playerLength];
        _players.forEach( player -> parsePlayerObject( (JSONObject) player ) );
    }

    public void parsePlayerObject(JSONObject player){
        JSONObject playerObject = (JSONObject) player.get("Player");
        players[((Long) playerObject.get("Id")).intValue()] = new Player( String.valueOf(playerObject.get("Name")), 
                                                                                        ((Long) playerObject.get("Id")).intValue(), 
                                                                                        ((Long) playerObject.get("Money")).intValue(), 
                                                                                        ((Long) playerObject.get("CurrentSquare")).intValue(), 
                                                                                        ((Long) playerObject.get("DaysInJail")).intValue(),
                                                                                        (Boolean) playerObject.get("Bankruptcy"));
       }

    public Player[] getPlayers(){
        /*
          This function will return the list of the player
         */
        return players;
    }

    public void setPlayers(int number){
        /*
          This function will create the player list
         */
        players = new Player[number];
    }

    public void setPlayer(int number){
        /*
          This function will create a player
         */
        players[number] = new Player(number);
        Scanner userInput = new Scanner(System.in);
        System.out.print("Please input player "+ (int)(number + 1) +"'s name: ");
        players[number].setName(userInput.nextLine());
        //System.out.println(players[number].getName());
    }

    public void setPlayersMoney(int playerNum, int money){
        /*
          This function will set money of a player
         */
        players[playerNum].setMoney(money);
    }

    public int getPlayerMoney(int playerNum){
        /*
          This function will get money of a player
         */
        return players[playerNum].getMoney();
    }

    public void setCurrentPlayer(int number) {
        /*
          This function will set current player number
         */
        currentPlayer = number;
    }

    public int getCurrentPlayer() {
        /*
          This function will get current player number
         */
        return currentPlayer;
    }

    public String getPlayerById(int id){
        /**
         * This function will get player name by id
         */
        return players[id].getName();
    }

    public boolean getPlayerBankruptcy(int playerNum) {
        /*
          This function will get bankruptcy status of a player
         */
        return players[playerNum].getBankruptcy();
    }


    public void setPlayerCurrentSquare(int playerNum, int position) {
        /*
          This function will set current square of a player
         */
        players[playerNum].setCurrentSquare(position);
    }

    public int getDaysInJail(int playerNum) {
        /*
          This function will get days in jail of a player
         */
        return players[playerNum].getDaysInJail();
    }

    public int getPlayerCurrentSquare(int playerNum){
        /*
          This function will get current square of a player
         */
        return players[playerNum].getCurrentSquare();
    }

    public void setPlayerDaysInJail(int playerNum, int days) {
        /*
          This function will set current square of a player
         */
        players[playerNum].setDaysInJail(days);
    }

    public void setPlayerBankruptcy(int playerNum) {
        /*
          This function will set current square of a player
         */
        players[playerNum].setBankruptcy(true);
    }

    public int[][] leaderboard() {
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

    public void printLeaderboard(){
        System.out.println(view.printLeaderBoard(leaderboard()));
    }
}
