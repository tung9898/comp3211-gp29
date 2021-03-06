package Controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import Model.IoStorage;
import View.IoStorageView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class IoStorageController extends Controller{
    /** 
      *  This controller mainly for operations with system
     */

    protected IoStorage model;
    protected IoStorageView ioStorageView = new IoStorageView();

    public IoStorageController(){}

    public IoStorageController(IoStorage model){
        this.model = model;
    }

    public void setFileLocation(String fileLocation) {
        /*
          This function will call model IoStorage to set the file location.
         */
        model.setFileLocation(fileLocation);
    }

    public String getFileLocation() {
        /*
          This function will call IoStorage to return the file location.
         */
        return model.getFileLocation();
    }

    public void saveFile(List<Map<String, Object>> playersList, 
                         Map<String, Object> gameStatusMap,
                         List<Map<String, Object>> boardList,
                         String fileName){

        JSONObject gameStatusDetail = new JSONObject();
        JSONObject gameStatusObject = new JSONObject();
        gameStatusDetail.put("TotalNumberOfPlayers", gameStatusMap.get("TotalNumberOfPlayers"));
        gameStatusDetail.put("CurrentNumberOfPlayers", gameStatusMap.get("CurrentNumberOfPlayers"));
        gameStatusDetail.put("Rounds", gameStatusMap.get("Rounds"));
        gameStatusDetail.put("CurrentPlayer", gameStatusMap.get("CurrentPlayer"));

        JSONObject playerDetail = new JSONObject();
        JSONObject playerObject = new JSONObject();
        JSONArray playersArray = new JSONArray();

        //if(playersList.size() != 0){
            for(int i = 0; i < playersList.size(); i++){
                playerDetail = new JSONObject();
                playerObject = new JSONObject();
                // First get for arraylist, second get for map
                playerDetail.put("Name", playersList.get(i).get("Name"));
                playerDetail.put("Id", playersList.get(i).get("Id"));
                playerDetail.put("Money", playersList.get(i).get("Money"));
                playerDetail.put("CurrentSquare", playersList.get(i).get("CurrentSquare"));
                playerDetail.put("DaysInJail", playersList.get(i).get("DaysInJail"));
                playerDetail.put("Bankruptcy", playersList.get(i).get("Bankruptcy"));
                playerObject.put("Player",playerDetail);
                playersArray.add(playerObject);
            }
        //}
        
        JSONObject squareDetail = new JSONObject();
        JSONObject squareObject = new JSONObject();
        JSONArray boardArray = new JSONArray();

        //if(playersList.size() != 0){
            for(int i = 0; i < boardList.size(); i++){
                squareDetail = new JSONObject();
                squareObject = new JSONObject();
                // First get for arraylist, second get for map
                squareDetail.put("Id", boardList.get(i).get("Id"));
                squareDetail.put("Name", boardList.get(i).get("Name"));
                squareDetail.put("Owner", boardList.get(i).get("Owner"));
                squareDetail.put("Price", boardList.get(i).get("Price"));
                squareDetail.put("Rent", boardList.get(i).get("Rent"));
                squareObject.put("Square",squareDetail);
                boardArray.add(squareObject);
            }
        //}

        gameStatusDetail.put("Players", playersArray);
        gameStatusDetail.put("Board", boardArray);
        gameStatusObject.put("GameStatus", gameStatusDetail);

        // Create file
        IoStorage io = new IoStorage();
        io.setFileLocation("data\\"+fileName+".json");
        File theDir = new File("data");
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        // Write file
        try(FileWriter file = new FileWriter(io.getFileLocation())){
            file.write(gameStatusObject.toJSONString());
            file.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public JSONObject loadFile(String fileLocation){
        IoStorage io = new IoStorage();
        io.setFileLocation(fileLocation);
    
        JSONParser jsonParser = new JSONParser();
        JSONObject obj = null;
        try (FileReader reader = new FileReader("data/"+fileLocation)){
            obj = (JSONObject)jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public String[] getFilesList(){
        String[] filenames;
        File f = new File("data");
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        // Does data folder exist?
        if(!f.exists()){
            System.out.println(ioStorageView.printNoDataFolderExistError());
            return null;
        }

        filenames = f.list();

        // Does any files exist in data folder?
        if(!(filenames.length > 0 && Arrays.stream(filenames).anyMatch(str -> str.contains(".json")))){
            System.out.println(ioStorageView.printNoDataFilesExistError());
            return null;
        }
        return filenames;
    }

    public void printFileChoiceInput(){
        System.out.println(ioStorageView.printFileChoiceInput());
    }
}
