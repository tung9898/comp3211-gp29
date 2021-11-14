package View;

import Controller.StatusController;

public class UserInterface {
    static String nl = "\n";
    static String tab = "\t";
    static String space = " ";
    static String colon = ":";
    static String to = "-";

    public static GameStatusView gsv = new GameStatusView();
    public static GameStatusView sysv = new GameStatusView();
    public static IOStorageView iosv = new IOStorageView();
    public static PlayerView pv = new PlayerView();

    public static String printBeginActionInput(){
        return "Input Your Action (1 or 2) : ";
    }

    public static String printMenuInput(){ 
        return "Press P for menu"; 
    }

    public static String printRequestRollDice(){ 
        return "Press ENTER to roll the dice: "; 
    }

    public static String printTurnStarted(int i){ 
        return nl + "------------------------‖ It is player " + i + "'s turn ‖------------------------" + nl; 
    }

    public static String printTurnEnded(){ 
        return nl + "------------------------------‖ Turn end ‖------------------------------" + nl; 
    }

    public static String printDice(int i){
        switch(i){
            case 1: return "１";
            case 2: return "２";
            case 3: return "３";
            case 4: return "４";
            case 5: return "５";
            case 6: return "６";
        }
        return "";
    }

    public static String printRollDiceResult(int[] d){
        String msg = "";
        for(int i = 0; i < d.length; i++){
            msg += "Dice " + (i+1) + " : " + printDice(d[i]) + "\n";
        }
        return msg;
    }
}
