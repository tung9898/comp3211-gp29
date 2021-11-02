package Model;

public class IoStorage{
    /**
     * more details are as follows:
     * url: https://docs.oracle.com/javase/6/docs/api/java/util/Properties.html
     */
    protected String fileLocation;

    public IoStorage(){}

    public IoStorage(String _fileLocation){
        this.fileLocation = _fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        /**
         * This function will set the file location.
         */
        this.fileLocation = fileLocation;
    }

    public String getFileLocation() {
        /**
         * This function will return the file location.
         */
        return fileLocation;
    }
}