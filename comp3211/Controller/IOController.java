package comp3211.Controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import comp3211.Model.IoStorage;

public class IOController {
    private IoStorage model;

    public IOController(IoStorage model){
        this.model = model;
    }

    public void loadFile(InputStream inStream) throws IOException{
        
    }

    public void saveFile(OutputStream out, String comments) {

    }
}
