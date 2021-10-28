/* package src.main.Controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import src.main.Model.IoStorage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class IOController {
    private IoStorage model;

    public IOController(IoStorage model){
        this.model = model;
    }

    public static void saveFile(List<Map<String, Object>> playersList, String fileName){
        JSONObject playerDetail = new JSONObject();
        JSONObject playerObject = new JSONObject();
        JSONArray playerList = new JSONArray();
        if(playersList.size() != 0){
            for(int i = 0; i < playersList.size(); i++){
                playerDetail = new JSONObject();
                playerObject = new JSONObject();
                // First get for arraylist, second get for map
                playerDetail.put("name", playersList.get(i).get("name"));
                playerDetail.put("id", playersList.get(i).get("id"));
                playerObject.put("player",playerDetail);
                playerList.add(playerObject);
            }
        }
        
        // Create file
        ioStorage io = new ioStorage();
        io.setFileLocation("data\\"+fileName+".json");
        File theDir = new File("data");
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        // Write file
        try(FileWriter file = new FileWriter(io.getFileLocation())){
            file.write(playerList.toJSONString());
            file.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static JSONArray loadFile(String fileLocation){
        ioStorage io = new ioStorage();
        io.setFileLocation(fileLocation);
    
        JSONParser jsonParser = new JSONParser();
        JSONArray obj = null;
        try (FileReader reader = new FileReader("data\\"+fileLocation)){
            obj = (JSONArray)jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
 */