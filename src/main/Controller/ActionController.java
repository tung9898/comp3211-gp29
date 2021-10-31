package Controller;

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

import Model.GameStatus;
import Model.Player;
import Service.GameStatusService;
import Service.PlayerService;

public class ActionController {
    /** 
      *  This controller mainly for player's action
     */

    static Random rand = new Random();

    public static int getRandom(int number){
        return rand.nextInt(number);
    }

    public static int getRandom(int min, int max){
        return rand.nextInt(max - min + 1) + min;
    }
    
    public static int[] rollingDice() {
        /**
         * This function will randomly generate 2 number in a specific range.
         */
        int[] r = new int[2];
        r[0] = rand.nextInt(4)+1;
        r[1] = rand.nextInt(4)+1;
        return r;
    }
 
    // Process IO options
    public static void saveFile(Player[] players, GameStatus gameStatus){
        // Create the map & list to store the game status' data
        Map<String,Object> gameStatusMap = new HashMap<String, Object>();
        gameStatusMap.put("TotalNumberOfPlayers", gameStatus.getTotalNumberOfPlayers());
        gameStatusMap.put("CurrentNumberOfPlayers", gameStatus.getCurrentNumberOfPlayers());
        gameStatusMap.put("Rounds", gameStatus.getRounds());

        // Create the map & list to store the players' data
        Map<String,Object> playersMap = new HashMap<String, Object>();
        List<Map<String, Object>> playersList = new ArrayList<Map<String, Object>>();
        //if(players.length != 0){
            for(int i = 0; i < players.length; i++){
                playersMap.put("Name",players[i].getName());
                playersMap.put("Id",players[i].getId());
                playersMap.put("Money", players[i].getMoney());
                playersMap.put("CurrentSquare", players[i].getCurrentSquare());
                playersMap.put("DaysInJail", players[i].getDaysInJail());
                playersMap.put("Bankruptcy", players[i].getBankruptcy());
                playersList.add(playersMap);
                playersMap = new HashMap<String, Object>();
            }
        //}

        // Ask user to input file name
        Scanner userInput = new Scanner(System.in);
        System.out.print("Input your file name:");
        String fileName = userInput.next();

        // Save file
        // Should I call this controller function here? or move to monopoly.java?
        IoController.saveFile(playersList, gameStatusMap, fileName);
    }
    
    public static void loadFile(){
        String[] filenames;
        File f = new File("data");
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Does data folder exist?
        if(!f.exists()){
            System.out.println("No files 1");
            return;
        }

        filenames = f.list();

        // Does any files exist in data folder?
        if(!(filenames.length > 0 && Arrays.stream(filenames).anyMatch(str -> str.contains(".json")))){
            System.out.println("No files");
            return;
        }

        // Ask user to select the file
        Scanner userInput = new Scanner(System.in);
        int fileNumber = -1;
        do{
            // Print files' information
            System.out.println("Here are the files:");
            for(int i = 0; i < filenames.length; i++){
                System.out.printf("[%1$s] %2$s %n", i, filenames[i]);
            }
            System.out.print("Input your choice (number only):");

            // Validate the input is or not a number
            while(!(userInput.hasNextInt())){
                System.out.print("Input your choice (number only):");
                userInput.next();
            }
            fileNumber = userInput.nextInt();
       }while(!(fileNumber >= 0 && 
                fileNumber < filenames.length && 
                filenames[fileNumber].contains(".json")));

        // Load the data from file
        JSONObject loadObject = IoController.loadFile(filenames[fileNumber]);
        try{
            JSONObject gameStatusObject = (JSONObject) loadObject.get("GameStatus");
            GameStatusService.gameStatus = new GameStatus( ((Long) gameStatusObject.get("TotalNumberOfPlayers")).intValue(), 
                                                           ((Long) gameStatusObject.get("CurrentNumberOfPlayers")).intValue(),
                                                           ((Long) gameStatusObject.get("Rounds")).intValue());

            // Can delete the printlns
            System.out.println("TotalNumberOfPlayers: "+GameStatusService.gameStatus.getTotalNumberOfPlayers());
            System.out.println("CurrentNumberOfPlayers: "+GameStatusService.gameStatus.getCurrentNumberOfPlayers());
            System.out.println("Rounds: "+GameStatusService.gameStatus.getRounds());

            PlayerService.players = new Player[GameStatusService.gameStatus.getTotalNumberOfPlayers()];
            JSONArray playersArray = (JSONArray) gameStatusObject.get("Players");
            playersArray.forEach( pla -> parsePlayerObject( (JSONObject) pla ) );
            System.out.println("Load Successfully~");
        }catch(NullPointerException e){
            System.out.println("Error occur!\n"+e);
            System.out.println("Load failed!");
        }catch(Exception e){
            System.out.println("Error occur!\n"+e);
            System.out.println("Load failed!");
        }
    }
    public static void parsePlayerObject(JSONObject player){
            JSONObject playerObject = (JSONObject) player.get("Player");
            PlayerService.players[((Long) playerObject.get("Id")).intValue()] = new Player( String.valueOf(playerObject.get("Name")), 
                                                                                            ((Long) playerObject.get("Id")).intValue(), 
                                                                                            ((Long) playerObject.get("Money")).intValue(), 
                                                                                            ((Long) playerObject.get("CurrentSquare")).intValue(), 
                                                                                            ((Long) playerObject.get("DaysInJail")).intValue(),
                                                                                            (Boolean) playerObject.get("Bankruptcy"));
            
            // Can delete the printlns
            System.out.println("Name: "+PlayerService.players[((Long) playerObject.get("Id")).intValue()].getName());
            System.out.println("Id: "+PlayerService.players[((Long) playerObject.get("Id")).intValue()].getId());
            System.out.println("Money: "+PlayerService.players[((Long) playerObject.get("Id")).intValue()].getMoney());
            System.out.println("CurrentSquare: "+PlayerService.players[((Long) playerObject.get("Id")).intValue()].getCurrentSquare());
            System.out.println("DaysInJail: "+PlayerService.players[((Long) playerObject.get("Id")).intValue()].getDaysInJail());
            System.out.println("Bankruptcy: "+PlayerService.players[((Long) playerObject.get("Id")).intValue()].getBankruptcy());
    }
}
