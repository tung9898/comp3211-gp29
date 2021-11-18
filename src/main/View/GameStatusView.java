package View;

import Controller.GameStatusController;

public class GameStatusView extends UserInterface{
    /* 
     * This class will print the view of the game status.
    */


    public String printRoundStarted(int r){
        return 
        "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
        "                        Round " + r + " Start\n" +
        "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"; 
    }

    public String printRoundEnded(){ 
        return 
        "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
        "                              Round End\n" +
          "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
    }

    public String printRoundOver100(){ 
        return "Game Over, round is over 100.";
    }

}
