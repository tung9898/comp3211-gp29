import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Controller.*;
import View.UserInterface;

public class Monopoly{
    /**
     * Declare the board, using a list of object.
     */

//    static int numberOfDice = 2;
//    static int numberOfSide = 4;
    protected static int MaximumNumberOfPlayer = 6;
    protected static int MinimumNumberOfPlayer = 2;

    protected static Controller controller = new Controller();
    protected static GameStatusController gameStatusController = new GameStatusController();

    protected static SquareController squareController = new SquareController();

    public static void main(String[] args) {
        /*
          This function is the main function of Monopoly.
         */

        // init board using list, make a list of square, from 0 to 19
        System.out.println(UserInterface.gsv.printWelcome());
        // Ask for start game or load game
        int action = -1;
        Scanner userInput = new Scanner(System.in);
        // 3 for testing save file function
        while(!(action == 1 || action == 2 || action == 3)){
            System.out.print(UserInterface.printBeginActionInput());
            while(!(userInput.hasNextInt())){
                if(!(action == 1 || action == 2 || action == 3)){
                    System.out.println(UserInterface.printBeginActionInputError());
                }
                System.out.print(UserInterface.printBeginActionInput());
                userInput.next();
            }
            if(!(action == 1 || action == 2 || action == 3)){
                System.out.println(UserInterface.printBeginActionInputError());
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
                    try{
                        numberOfPlayer = myObj.nextInt();
                        if (MinimumNumberOfPlayer <= numberOfPlayer && numberOfPlayer <= MaximumNumberOfPlayer){
                            InitSquare(numberOfPlayer);
                            break;
                        }
                        else{
                            System.out.println(UserInterface.pv.printNumberOfPlayerInputError(MinimumNumberOfPlayer, MaximumNumberOfPlayer));
                        }
                    } catch(Exception e){
                        System.out.println(UserInterface.pv.printNumberOfPlayerInputError(MinimumNumberOfPlayer, MaximumNumberOfPlayer));
                        myObj = new Scanner(System.in);
                    }
                }
                // start the game
                while (GameStart());
                // ask for input if player want to continue;
                // break if necessary
                // ask for input if player want to change to number of player;
                // call InitSquare if necessary
                break;
            case 2:
                // Load file
                String loadFileName = askForLoadFileName(IoController.getFilesList());
                // Load the data from file
                JSONObject loadObject = IoController.loadFile(loadFileName);
                try{
                    JSONObject gameStatusObject = (JSONObject) loadObject.get("GameStatus");

                    gameStatusController.setGameStatus( ((Long) gameStatusObject.get("TotalNumberOfPlayers")).intValue(),
                            ((Long) gameStatusObject.get("CurrentNumberOfPlayers")).intValue(),
                            ((Long) gameStatusObject.get("Rounds")).intValue());


                    PlayerController.setPlayers((JSONArray) gameStatusObject.get("Players"), gameStatusController.getTotalNumberOfPlayers());
                    System.out.println("Load successfully~");
                }catch(NullPointerException e){
                    System.out.println("Error occur!\n" + e + "\n Load failed!");
                }catch(Exception e){
                    System.out.println("Error occur!\n" + e + "\n Load failed!");
                }
                break;
            /* case 3:
                // Save file
                // Ask user to input file name
                userInput = new Scanner(System.in);
                System.out.print("Input your file name:");
                while (!userInput.hasNext("[^\\/:*?\"<>|]")) {
                    System.out.print("Input your file name (no \\/:*?\"<>|):");
                    userInput.next();
                }
                String saveFileName = userInput.next();

                // Save file
                IoController.saveFile(PlayerController.getPlayersList(), gameStatusController.getGameStatusMap(), saveFileName);
                // ActionController.saveFile(PlayerController.getPlayers(), gameStatusModel);
                break; */
            default:
                break;
        }
    }

    public static boolean GameStart(){
        /*
          This function is to run each game round.
         */
        int turns = gameStatusController.getCurrentNumberOfPlayers();
        while(true){
            gameStatusController.printRoundStarted();
            for(int i = 0; i < turns; i++){
                PlayerController.setCurrentPlayer(i);
                if(PlayerController.getPlayerBankruptcy(i)) {
                    continue;
                }
                System.out.println(UserInterface.sysv.printTurnStarted(i+1));
                int currentPos[] = new int[20];
                currentPos[PlayerController.getPlayerCurrentSquare(i)] = 1;
                System.out.println(UserInterface.pv.printPlayerPositionInMP(currentPos));
                if(PlayerController.getDaysInJail(i) > -1) {
                    // Check the player in jail days, if it is not -1, that's mean the player is in jail.
                    HandleInJail(PlayerController.getDaysInJail(i));
                }
                else{
                    System.out.println("Make the choice: \n[1] to roll the dice, 2 to save & exit the game");
                    Scanner myObj = new Scanner(System.in);
                    int choice = myObj.nextInt();
                    if (choice==1){
                        int[] dice = controller.rollingDice();
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
            System.out.println(UserInterface.gsv.printRoundEnded());
            if(gameStatusController.RoundEnd() || gameStatusController.getCurrentNumberOfPlayers() == 1){
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

    public static void InitSquare(int numberOfPlayer) {
        /*
          This function is to set some important data when the game starts.
         */
        gameStatusController = new GameStatusController(numberOfPlayer);
        PlayerController.setPlayers(numberOfPlayer);
        SquareController.initBoard();
        for(int i = 0; i < numberOfPlayer; i++) {
            PlayerController.setPlayer(i);
            PlayerController.setPlayersMoney(i,1500);
            // Ask for name input for each player.
        }
    }

    public static void bankruptcy(int id) {
        /*
          This function will done all the things after a player has a
          negative amount of money.
          Set all of his/her properties to unowned.
         */
        PlayerController.setPlayerBankruptcy(id);
        for(int i = 0; i < squareController.getSquare().length; i++){
            if(squareController.getBoardOwner(i) == id) squareController.setBoardId(i, -1);
        }
    }

    public static void PlayerMakeAMove(int move) {
        /*
          This function will help player to make a move.
         */
        // check if player is in jail
        // return if jail is not break
        // int oldpos = current player pos;
        // int newpos = oldpos + move;
        // if newpos > 19, newpos -= 20;
        // current player pos = newpos; // delete old pos and add new pos in Square.java
        // newpos square effect

        // Finally, Check player money. If less than 0, declare bankruptcies.
        int currentPlayer = PlayerController.getCurrentPlayer();
        int oldPos = PlayerController.getPlayerCurrentSquare(currentPlayer);
        int newPos = oldPos + move;
        int salary = squareController.GoSalary();
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
        /*
          This function will
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
                try {
                    choice = myObj.nextInt();
                    if (choice==1){
                        dice = controller.rollingDice();
                        System.out.println(UserInterface.sysv.printRollDiceResult(dice));
                        if (dice[0]==dice[1]){
                            System.out.println("You succeed to get out of the jail. Congratulation!");
                            PlayerController.setPlayerDaysInJail(currentPlayer,-1);
                            PlayerMakeAMove(dice[0]+dice[1]);
                            break;
                        }
                        else{
                            System.out.println("You failed to get out of the jail. Try again in the next round.");
                            PlayerController.setPlayerDaysInJail(currentPlayer,inJailDays+1);
                            break;
                        }
                    }
                    else{
                        System.out.println("You can only enter 1 to continue.");
                    }
                } catch (Exception e) {
                    System.out.println("You can only enter 1 to continue.");
                }
                    
            }
        }
        else{
            while(true){
                System.out.println("Enter 1 to roll the dice, 2 to pay a fine of HKD 150:");
                choice = myObj.nextInt();
                dice = controller.rollingDice();
                if (choice==1){
                    System.out.println(UserInterface.sysv.printRollDiceResult(dice));
                    if (dice[0]==dice[1]){
                        System.out.println("You succeed to get out of the jail. Congratulation!");
                        PlayerController.setPlayerDaysInJail(currentPlayer,-1);
                        PlayerMakeAMove(dice[0]+dice[1]);
                        break;
                    }
                    else{
                        if (inJailDays==2){
                            System.out.println("You failed to get out of the jail. You must pay a fine of HKD 150 to leave.");
                            System.out.println("Fine paid. You can get out of the jail now.");
                            PlayerController.setPlayersMoney(currentPlayer, PlayerController.getPlayerMoney(currentPlayer)-150);
                            PlayerController.setPlayerDaysInJail(currentPlayer,-1);
                            PlayerMakeAMove(dice[0]+dice[1]);
                            break;
                        }
                        else{
                            System.out.println("You failed to get out of the jail. Try again in the next round.");
                            PlayerController.setPlayerDaysInJail(currentPlayer,inJailDays+1);
                            break;
                        }
                    }
                }
                if (choice==2){
                    System.out.println("Fine paid. You can get out of the jail now.");
                    PlayerController.setPlayersMoney(currentPlayer, PlayerController.getPlayerMoney(currentPlayer)-150);
                    PlayerController.setPlayerDaysInJail(currentPlayer,-1);
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
        /*
          This function is to redirect the square to its belonging effect
         */
        if (squareId!=0){
            System.out.print((squareId+1)+":");
        }
        switch(squareId+1){
            case 9:
            case 13:
            case 19:
                int chance = squareController.ChanceSalary();
                if (chance <0){
                    System.out.println(squareController.SquareName(squareId)+": You lose $"+ Math.abs(chance));
                }
                else{
                    System.out.println(squareController.SquareName(squareId)+": You gain $"+chance);
                }
                PlayerController.setPlayersMoney(currentPlayer, PlayerController.getPlayerMoney(currentPlayer)+chance);
                System.out.println("You have $"+PlayerController.getPlayerMoney(currentPlayer));
                break;
            case 4:
                int tax = PlayerController.PayTax();
                System.out.println(squareController.SquareName(squareId)+": You have to pay $"+tax+" tax.");
                PlayerController.setPlayersMoney(currentPlayer, PlayerController.getPlayerMoney(currentPlayer)-tax);
                System.out.println("You have $"+PlayerController.getPlayerMoney(currentPlayer));
                break;
            case 1:
                break;
            case 6:
            case 11:
                System.out.println(squareController.SquareName(squareId)+": No effect.");
                break;
            case 16:
                System.out.println(squareController.SquareName(squareId)+": You have to go to jail.");
                PlayerController.setPlayerDaysInJail(currentPlayer,0);
                PlayerController.setPlayerCurrentSquare(currentPlayer, 5);
                break;
            default:
                String name = squareController.SquareName(squareId);
                if (squareController.getBoardOwner(squareId)==-1){
                    SquarePurchase(squareId);
                }
                else{
                    int owner = squareController.getBoardOwner(squareId);
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
        /*
          This function is to handle the player’s purchase of property.
         */
        String name = squareController.SquareName(squareId);
        int landPrice = squareController.SquarePrice(squareId);
        int currentPlayer = PlayerController.getCurrentPlayer();
        int balance = PlayerController.getPlayerMoney(currentPlayer);

        // fail to pay message
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
                    squareController.setBoardOwner(squareId, currentPlayer);
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
        /*
          This function is to handle the player’s payment of rent and the owner’s receipt of rent.
         */
        String name = squareController.SquareName(squareId);
        int landRent = squareController.SquareRent(squareId);
        int currentPlayer = PlayerController.getCurrentPlayer();
        int owner = squareController.getBoardOwner(squareId);

        System.out.println(name+" is owned by player "+(owner+1));
        System.out.println("You have to pay the rent of "+name+" of $"+landRent+" to player "+(owner+1));
        PlayerController.setPlayersMoney(currentPlayer, PlayerController.getPlayerMoney(currentPlayer)-landRent);
        PlayerController.setPlayersMoney(owner, PlayerController.getPlayerMoney(owner)+landRent);
        System.out.println("You have $"+PlayerController.getPlayerMoney(currentPlayer)+" now.");
        System.out.println("Player "+(owner+1)+" have $"+PlayerController.getPlayerMoney(owner)+" now.");
    }

    public static String askForLoadFileName(String[] filenames){
        // Ask user to select the file
        Scanner userInput = new Scanner(System.in);
        int fileNumber = -1;
        do{
            // Print files' information
            System.out.println(UserInterface.iosv.printFileExistMessage());
            for(int i = 0; i < filenames.length; i++){
                System.out.printf("[%1$s] %2$s %n", i, filenames[i]);
            }
            System.out.print(UserInterface.iosv.printFileChoiceInput());

            // Validate the input is or not a number
            while(!(userInput.hasNextInt())){
                System.out.print(UserInterface.iosv.printFileChoiceInput());
                userInput.next();
            }
            fileNumber = userInput.nextInt();
        }while(!(fileNumber >= 0 &&
                fileNumber < filenames.length &&
                filenames[fileNumber].contains(".json")));
        return filenames[fileNumber];
    }
}