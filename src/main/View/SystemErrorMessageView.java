package View;

public class SystemErrorMessageView extends UserInterface {
    /* 
     * This class will print the error message of system.
    */

    public enum SystemErrorMessage {
        BeginActionInputError,
        NumberOfPlayerInputError,
        LoadFileError,
        FileNameInputError,
        NoDataFolderExistError,
        NoDataFilesExistError
    };

    public String printNumberOfPlayerInputError(int min, int max){
        return 
        nl +
        " ┌──────────────────────────────────────────────────────────────────────────────────┐" + nl +
        " │ Error: The game only accommodates "+ min +" to " + max +" players. Enter again." + tab + tab + "    │" + nl +
        " └──────────────────────────────────────────────────────────────────────────────────┘" + nl;
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
}
