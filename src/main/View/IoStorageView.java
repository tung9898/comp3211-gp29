package View;

public class IoStorageView extends UserInterface{
    
    public String printLoadFileSuccessMessage(){
        return "Load Successfully~";
    }

    public String printFileExistMessage(){
        return "Here are the files:";
    }

    public String printLoadFileError(Exception e){
        return ("Error occur!\n" + e + "\n Load failed!");
    }

    public String printFileNameInputError() {
        return "Input your file name (no \\/:*?\"<>|):";
    }

    public String printNoDataFolderExistError(){
        return "No files 1";
    }

    public String printNoDataFilesExistError(){
        return "No files";
    }

    public String printFileNameInput(){
        return "Input your file name:";
    }

    public String printFileChoiceInput(){
        return "Input your choice (number only):";
    }
}
