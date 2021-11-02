package View;

public class SystemErrorMessageView extends UserInterface {
    /* 
     * This class will print the error message of system.
    */

    public enum SystemErrorMessage {
        BeginActionInputError,
        NumberOfPlayerInputError
    };

    public String printNumberOfPlayerInputError(int min, int max){
        return 
        nl +
        " ┌──────────────────────────────────────────────────────────────────────────────────┐" + nl +
        " │ Error: The game only accommodates "+ min +" to " + max +" players. Enter again." + tab + tab + "    │" + nl +
        " └──────────────────────────────────────────────────────────────────────────────────┘" + nl;
    }
}
