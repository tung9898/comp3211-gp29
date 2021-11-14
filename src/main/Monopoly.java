import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Controller.*;
import Model.*;
import View.*;

public class Monopoly{
    /**
     * Declare the borad, using a list of object.
     */
    
    static int currentPlayer;
/*     static int numberOfDice = 2;
    static int numberOfSide = 4; */
    static int MaximumNumberOfPlayer = 6;
    static int MinimumNumberOfPlayer = 2;

    static GameStatus gameStatusModel;
    static StatusController statusController;

    public static void main(String[] args) {
        /**
         * This function is the main function of Monopoly.  
         */

        // init board using list, make a list of square, from 0 to 19
        System.out.println(UserInterface.gsv.printWelcome());
        // Ask for start game or load game
        int action = -1;
        Scanner userInput = new Scanner(System.in); // need userInput.close(); ?
        // 3 for testing save file function
        while(!(action == 1 || action == 2 || action == 3)){
            System.out.print(UserInterface.printBeginActionInput());
            while(!(userInput.hasNextInt())){
                System.out.print(UserInterface.printBeginActionInput());
                userInput.next();
            }
            action = userInput.nextInt();
        }
        switch (action) {
            case 1:
                // request a number from a range, use for the number of player.
                // Ask for number of players input.
                int numberOfPlayer;
                Scanner myObj = new Scanner(System.in);
                while(true) {
                    System.out.print(UserInterface.pv.printNumberOfPlayerInput(MinimumNumberOfPlayer, MaximumNumberOfPlayer));
                    numberOfPlayer = myObj.nextInt();
                    if (MinimumNumberOfPlayer <= numberOfPlayer && numberOfPlayer <= MaximumNumberOfPlayer){
                        InitSqaure(numberOfPlayer);
                        break;
                    }
                    else{
                        System.out.println(UserInterface.pv.printNumberOfPlayerInputError(MinimumNumberOfPlayer, MaximumNumberOfPlayer));
                    }
                }
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
                break;
            case 2:
                //
                ActionController.loadFile();
                break;
            case 3:
                ActionController.saveFile(PlayerController.getPlayers(), gameStatusModel);
                break;
            default:
                break;
        }
    }

    public static void GameStart(){
        /**
         * This function is to run each game round. 
         */
        int turns = StatusController.getCurrentNumberOfPlayers();
        while(true){
            System.out.println(UserInterface.sysv.printRoundStarted());
            for(int i = 0; i < turns; i++){
                System.out.println(UserInterface.sysv.printTurnStarted(i+1));
                int currentPos[] = new int[20];
                currentPos[PlayerController.getPlayerCurrentSquare(i)] = 1;
                System.out.println(UserInterface.gsv.printPlayerPositionInMP(currentPos));
                if(PlayerController.getPlayerBankruptcy(i)) continue;
                PlayerController.setCurrentPlayer(i); 
                if(PlayerController.getDaysInJail(i) > 0) {
                    // Check the player in jail days, if it is not -1, thats mean the player is in jail.
                }
                System.out.println(UserInterface.printRequestRollDice());
                int[] dice = ActionController.rollingDice();
                System.out.println(UserInterface.sysv.printRollDiceResult(dice));
                PlayerMakeAMove(dice[0] + dice[1]);
                // turns--;
                System.out.println(UserInterface.sysv.printTurnEnded());
            }
            System.out.println(UserInterface.sysv.printRoundEnded());
            if(StatusController.RoundEnd() || StatusController.getCurrentNumberOfPlayers() == 1){
                PlayerController.CheckWinner();
            }
        }
    }

    public static void InitSqaure(int numberOfPlayer) {
        /**
         * This function is to set some important data when the game starts. 
         */
        gameStatusModel = new GameStatus(numberOfPlayer, numberOfPlayer);
        statusController = new StatusController(gameStatusModel);

        PlayerController.setPlayers(numberOfPlayer);
        for(int i = 0; i < numberOfPlayer; i++) {
            SquareController.setSquare(i);
        }
        for(int i = 0; i < numberOfPlayer; i++) {
            PlayerController.setPlayer(i);
            // Ask for name input for each player.
        }
    }

    public static void bankruptcy(int id) {
        /**
         * This function will done all the things after a player has a
         * negative amount of money.
         * Set all of his/her properties to unowned.
         */
        for(int i = 0; i < SquareController.getSquare().length; i++){
            if(SquareController.getBoardOwner(i) == id) SquareController.setBoardId(i, -1);
        }
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

    public static int EffectCenter(int squareId) {
        /**
         * This function is to redirect the sqaure to its belonging effect
         */
        switch(squareId+1){    
            case 1:    
                return SquareController.GoSalary();
            case 9:
            case 13:
            case 19:
                return SquareController.ChanceSalary();
            case 4:
                return PlayerController.PayTax();
            default:     
                return SquareController.NoEffect();
       }    
    }

    public static void SquarePurchase(int squareId) {
        /**
         * This function is to handle the player’s purchase of property. 
         */
        int landPrice = SquareController.SquarePrice(squareId);
        currentPlayer = PlayerController.getCurrentPlayer();
        int balance = PlayerController.getPlayerMoney(currentPlayer);
        if(balance >= landPrice){
            // confirm message, return if fail to confirm.
            PlayerController.setPlayersMoney(currentPlayer, balance-landPrice);
            SquareController.setBoardOwner(squareId, currentPlayer);
            // successful message
        }else{
            // fail to pay message
        }
    }

    public static void SquarePayRent(int squareId) {
        /**
         * This function is to handle the player’s payment of rent and the owner’s receipt of rent.
         */
        int landRent = SquareController.SquareRent(squareId);
        int owner = SquareController.getBoardOwner(squareId);
        currentPlayer = PlayerController.getCurrentPlayer();
        int balance = PlayerController.getPlayerMoney(currentPlayer);
        PlayerController.setPlayersMoney(currentPlayer, balance-landRent);
        PlayerController.setPlayersMoney(owner, PlayerController.getPlayerMoney(owner) + landRent); // todo, need to fix if the renter don't have money.
        // money remaining message
    }
}