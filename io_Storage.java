import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class io_Storage{
    /**
     * more details are as follows:
     * url: https://docs.oracle.com/javase/6/docs/api/java/util/Properties.html
     */
    public String fileLocation;

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void loadFile(InputStream inStream) throws IOException{
        
    }

    public void saveFile(OutputStream out, String comments) {

    }
}