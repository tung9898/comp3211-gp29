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
            System.out.print(UserInterface.uimv.printBeginActionInput());
            while(!(userInput.hasNextInt())){
                System.out.print(UserInterface.uimv.printBeginActionInput());
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
                    System.out.print(UserInterface.uimv.printNumberOfPlayerInput(MinimumNumberOfPlayer, MaximumNumberOfPlayer));
                    numberOfPlayer = myObj.nextInt();
                    if (MinimumNumberOfPlayer <= numberOfPlayer && numberOfPlayer <= MaximumNumberOfPlayer){
                        InitSqaure(numberOfPlayer);
                        break;
                    }
                    else{
                        System.out.println(UserInterface.sysev.printNumberOfPlayerInputError(MinimumNumberOfPlayer, MaximumNumberOfPlayer));
                    }
                }
                // start the game
                boolean keepTheGameRun = true;
                while(keepTheGameRun) {
                    if (GameStart()==false){
                        break;
                    }
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

    public static boolean GameStart(){
        /**
         * This function is to run each game round. 
         */
        int turns = StatusController.getCurrentNumberOfPlayers();
        while(true){
            System.out.println(UserInterface.sysv.printRoundStarted());
            for(int i = 0; i < turns; i++){
                PlayerController.setCurrentPlayer(i);
                if(PlayerController.getPlayerBankruptcy(i)) {
                    continue;
                }
                System.out.println(UserInterface.sysv.printTurnStarted(i+1));
                int currentPos[] = new int[20];
                currentPos[PlayerController.getPlayerCurrentSquare(i)] = 1;
                System.out.println(UserInterface.gsv.printPlayerPositionInMP(currentPos));
                if(PlayerController.getDaysInJail(i) > -1) {
                    // Check the player in jail days, if it is not -1, thats mean the player is in jail.
                    HandleInJail(PlayerController.getDaysInJail(i));
                }
                else{
                    System.out.println("Make the choice: 1 to roll the dice, 2 to save & exit the game");
                    Scanner myObj = new Scanner(System.in);
                    int choice = myObj.nextInt();
                    if (choice==1){
                        int[] dice = ActionController.rollingDice();
                        System.out.println(UserInterface.sysv.printRollDiceResult(dice));
                        PlayerMakeAMove(dice[0] + dice[1]);
                        // turns--;
                        System.out.println(UserInterface.sysv.printTurnEnded());
                    }
                    if (choice==2){
                        return false;
                    }
                }
            }
            System.out.println(UserInterface.sysv.printRoundEnded());
            if(StatusController.RoundEnd() || StatusController.getCurrentNumberOfPlayers() == 1){
                PlayerController.CheckWinner();
                System.out.println("Make the choice: 1 to play a new game, 2 to exit the game");
                Scanner myObj = new Scanner(System.in);
                int choice = myObj.nextInt();
                if (choice==1){
                    //new game
                }
                if (choice==2){
                    return false;
                }
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
        int numberOfSquare = 20;
        for(int i = 0; i < numberOfSquare; i++) {
            SquareController.setSquare(i);
        }
        for(int i = 0; i < numberOfPlayer; i++) {
            PlayerController.setPlayer(i);
            PlayerController.setPlayersMoney(i,1500);
            // Ask for name input for each player.
        }
    }

    public static void bankruptcy(int id) {
        /**
         * This function will done all the things after a player has a
         * negative amount of money.
         * Set all of his/her properties to unowned.
         */
        PlayerController.setPlayerBankruptcy(id);
        for(int i = 0; i < SquareController.getSquare().length; i++){
            if(SquareController.getBoardOwner(i) == id){
                SquareController.setBoardOwner(i, -1);
            }
        }
    }

    public static void PlayerMakeAMove(int move) {
        /**
         * This function will help player to make a move.
         */
        int currentPlayer = PlayerController.getCurrentPlayer();
        int oldPos = PlayerController.getPlayerCurrentSquare(currentPlayer);
        int newPos = oldPos + move;
        int salary = SquareController.GoSalary();
        if (newPos>19){
            newPos -= 20;
            System.out.println("Passes through GO. You get $"+salary+" salary.");
            PlayerController.setPlayersMoney(currentPlayer, PlayerController.getPlayerMoney(currentPlayer)+salary);
        }
        PlayerController.setPlayerCurrentSquare(currentPlayer, newPos);

        EffectCenter(PlayerController.getPlayerCurrentSquare(currentPlayer),currentPlayer);

        if (PlayerController.getPlayerMoney(currentPlayer)<0){
            bankruptcy(currentPlayer);
        }
    }

    public static void HandleInJail(int inJailDays) {
        /**
         * This function will
         */
        int choice;
        int[] dice;
        Scanner myObj = new Scanner(System.in);
        int currentPlayer = PlayerController.getCurrentPlayer();

        System.out.println("You're in jail and cannot make a move.");
        System.out.println("Number of turns in jail: "+(inJailDays+1));
        if (inJailDays==0){
            while(true){
                System.out.println("Enter 1 to roll the dice:");
                choice = myObj.nextInt();
                if (choice==1){
                    dice = ActionController.rollingDice();
                    System.out.println(UserInterface.sysv.printRollDiceResult(dice));
                    if (dice[0]==dice[1]){
                        System.out.println("You succeed to get out of the jail. Congratulation!");
                        PlayerController.setDaysInJail(currentPlayer,-1);
                        PlayerMakeAMove(dice[0]+dice[1]);
                        break;
                    }
                    else{
                        System.out.println("You failed to get out of the jail. Try again in the next round.");
                        PlayerController.setDaysInJail(currentPlayer,inJailDays+1);
                        break;
                    }
                }
                else{
                    System.out.println("You can only enter 1 to continue.");
                }
            }
        }
        else{
            while(true){
                System.out.println("Enter 1 to roll the dice, 2 to pay a fine of HKD 150:");
                choice = myObj.nextInt();
                dice = ActionController.rollingDice();
                if (choice==1){
                    System.out.println(UserInterface.sysv.printRollDiceResult(dice));
                    if (dice[0]==dice[1]){
                        System.out.println("You succeed to get out of the jail. Congratulation!");
                        PlayerController.setDaysInJail(currentPlayer,-1);
                        PlayerMakeAMove(dice[0]+dice[1]);
                        break;
                    }
                    else{
                        if (inJailDays==2){
                            System.out.println("You failed to get out of the jail. You must pay a fine of HKD 150 to leave.");
                            System.out.println("Fine paid. You can get out of the jail now.");
                            PlayerController.setPlayersMoney(currentPlayer, PlayerController.getPlayerMoney(currentPlayer)-150);
                            PlayerController.setDaysInJail(currentPlayer,-1);
                            PlayerMakeAMove(dice[0]+dice[1]);
                            break;
                        }
                        else{
                            System.out.println("You failed to get out of the jail. Try again in the next round.");
                            PlayerController.setDaysInJail(currentPlayer,inJailDays+1);
                            break;
                        }
                    }
                }
                if (choice==2){
                    System.out.println("Fine paid. You can get out of the jail now.");
                    PlayerController.setPlayersMoney(currentPlayer, PlayerController.getPlayerMoney(currentPlayer)-150);
                    PlayerController.setDaysInJail(currentPlayer,-1);
                    System.out.println(UserInterface.sysv.printRollDiceResult(dice));
                    PlayerMakeAMove(dice[0]+dice[1]);
                    break;
                }
                else{
                    System.out.println("You can only enter 1 or 2 to continue.");
                }
            }
        }
    }

    public static void EffectCenter(int squareId, int currentPlayer) {
        /**
         * This function is to redirect the square to its belonging effect
         */
        if (squareId!=0){
            System.out.print((squareId+1)+":");
        }
        switch(squareId+1){
            case 9:
            case 13:
            case 19:
                int chance = SquareController.ChanceSalary();
                if (chance <0){
                    System.out.println(SquareController.SquareName(squareId)+": You lose $"+ Math.abs(chance));
                }
                else{
                    System.out.println(SquareController.SquareName(squareId)+": You gain $"+chance);
                }
                PlayerController.setPlayersMoney(currentPlayer, PlayerController.getPlayerMoney(currentPlayer)+chance);
                System.out.println("You have $"+PlayerController.getPlayerMoney(currentPlayer));
                break;
            case 4:
                int tax = PlayerController.PayTax();
                System.out.println(SquareController.SquareName(squareId)+": You have to pay $"+tax+" tax.");
                PlayerController.setPlayersMoney(currentPlayer, PlayerController.getPlayerMoney(currentPlayer)-tax);
                System.out.println("You have $"+PlayerController.getPlayerMoney(currentPlayer));
                break;
            case 1:
                break;
            case 6:
            case 11:
                System.out.println(SquareController.SquareName(squareId)+": No effect.");
                break;
            case 16:
                System.out.println(SquareController.SquareName(squareId)+": You have to go to jail.");
                PlayerController.setDaysInJail(currentPlayer,0);
                PlayerController.setPlayerCurrentSquare(currentPlayer, 5);
                break;
            default:
                String name = SquareController.SquareName(squareId);
                if (SquareController.getBoardOwner(squareId)==-1){
                    SquarePurchase(squareId);
                }
                else{
                    int owner = SquareController.getBoardOwner(squareId);
                    if (currentPlayer!=owner){
                        SquarePayRent(squareId);
                    }
                    else{
                        System.out.println(name+" is owned by you. No effect.");
                    }
                }
                break;
       }
    }

    public static void SquarePurchase(int squareId) {
        /**
         * This function is to handle the player’s purchase of property. 
         */
        String name = SquareController.SquareName(squareId);
        int landPrice = SquareController.SquarePrice(squareId);
        int currentPlayer = PlayerController.getCurrentPlayer();
        int balance = PlayerController.getPlayerMoney(currentPlayer);
        System.out.println(name+" is unowned.");
        System.out.println("Price of "+name+" is $"+landPrice);
        System.out.println("You have $"+balance);
        Scanner myObj = new Scanner(System.in);
        int choice;
        while(true){
            System.out.println("Would you like to buy the property? Enter 1 to buy, 2 to do nothing:");
            choice = myObj.nextInt();
            if(choice==1){
                if(balance >= landPrice){
                    PlayerController.setPlayersMoney(currentPlayer, balance-landPrice);
                    SquareController.setBoardOwner(squareId, currentPlayer);
                    System.out.println("You have bought "+name+". Remaining amount of money: $"+PlayerController.getPlayerMoney(currentPlayer));
                    break;
                }
                else{
                    System.out.println("You only have $"+PlayerController.getPlayerMoney(currentPlayer)+". You do not have enough money to buy "+name+".");
                    break;
                }
            }
            if(choice==2){
                System.out.println("You chose not to buy "+name+". No effect.");
                break;
            }
            else{
                System.out.println("You can only enter 1 or 2 to continue.");
            }
        }
    }

    public static void SquarePayRent(int squareId) {
        /**
         * This function is to handle the player’s payment of rent and the owner’s receipt of rent.
         */
        String name = SquareController.SquareName(squareId);
        int landRent = SquareController.SquareRent(squareId);
        int currentPlayer = PlayerController.getCurrentPlayer();
        int owner = SquareController.getBoardOwner(squareId);
        System.out.println(name+" is owned by player "+(owner+1));
        System.out.println("You have to pay the rent of "+name+" of $"+landRent+" to player "+(owner+1));
        PlayerController.setPlayersMoney(currentPlayer, PlayerController.getPlayerMoney(currentPlayer)-landRent);
        PlayerController.setPlayersMoney(owner, PlayerController.getPlayerMoney(owner)+landRent);
        System.out.println("You have $"+PlayerController.getPlayerMoney(currentPlayer)+" now.");
        System.out.println("Player "+(owner+1)+" have $"+PlayerController.getPlayerMoney(owner)+" now.");
    }
}