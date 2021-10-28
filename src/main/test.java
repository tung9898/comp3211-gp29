import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import controller.ioController;
import model.player;
import service.playerService;

public class test {
    public static void main(String[] args){

        int action = askForSL();

        // Access current action
        switch(action){
            
            // Save file action
            case 1:
                saveFileUI();
                break;

            // Load file action
            case 2:
                loadFileUI();
                break;
            default:
                break;
        }
        //System.out.println(action);
    }

    public static int askForSL(){
        // UI for SL action
        Scanner userInput = new Scanner(System.in);
        System.out.println("[1] Save file");
        System.out.println("[2] Load file");
        int action = -1;
        while(!(action == 1 || action == 2)){
            System.out.print("Input your action (1 or 2):");
            while(!(userInput.hasNextInt())){
                System.out.print("Input your action (1 or 2):");
                userInput.next();
            }
            action = userInput.nextInt();
        }
        return action;
    }

    public static void saveFileUI(){
        // Get player data
        player[] players = playerService.getPlayers();

        // Create the map & list to store the player data (Convert player data to map & list)
        Map<String,Object> playersMap = new HashMap<String, Object>();
        List<Map<String, Object>> playersList = new ArrayList<Map<String, Object>>();
        //if(players.length != 0){
            for(int i = 0; i < players.length; i++){
                playersMap.put("name",players[i].name);
                playersMap.put("id",players[i].id);
                playersList.add(playersMap);
                playersMap = new HashMap<String, Object>();
            }
        //}

        // Ask user to input file name
        Scanner userInput = new Scanner(System.in);
        System.out.print("Input your file name:");
        String fileName = userInput.next();

        // Save file
        ioController.saveFile(playersList, fileName);
    }

    public static void loadFileUI(){
        String[] filenames;
        File f = new File("data");
        
        // Does data folder exist?
        if(!f.exists()){
            System.out.println("No files");
            return;
        }

        filenames = f.list();

        // Does any files exist in data folder?
        if(!(filenames.length > 0)){
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
        JSONArray playersArray = ioController.loadFile(filenames[fileNumber]);
        playersArray.forEach( pla -> parsePlayerObject( (JSONObject) pla ) );
    }

    public static void parsePlayerObject(JSONObject player){
        JSONObject playerObject = (JSONObject) player.get("player");
        System.out.println("name: "+playerObject.get("name"));
        System.out.println("id: "+playerObject.get("id"));
    }
}
