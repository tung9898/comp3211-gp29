package View;

import java.util.List;

import Controller.PlayerController;

public class PlayerView extends UserInterface{

    public String printBankruptcyWarning(){ 
        return "Your money is not under 0, make your choice wisely."; 
    }

    public String printChancepositive(int m){
        return "Congratulations!! you won " + m + " HKD!!" + nl;
    }

    public String printChancenegative(int m){
        return "Bad luck! you need to pay the bank " + m + " HKD." + nl;
    }

    public String printPayTax(int pid){
        return "Income Tax: " + (PlayerController.getPlayerMoney(pid) - PlayerController.TaxCalculate(pid)) + nl;
    }

    public String printNumberOfPlayerInputError(int min, int max){
        return 
        nl +
        " ┌──────────────────────────────────────────────────────────────────────────────────┐" + nl +
        " │ Error: The game only accommodates "+ min +" to " + max +" players. Enter again." + tab + tab + "    │" + nl +
        " └──────────────────────────────────────────────────────────────────────────────────┘" + nl;
    }

    public String printInJailDays(){ 
        int pid = PlayerController.getCurrentPlayer();
        String msg = "";
        if(PlayerController.getDaysInJail(pid) > -1){
            msg = "Player " + (pid+1) + ", you are in jail for " + PlayerController.getDaysInJail(pid) + " days." + nl;
        }else{
            msg = "Player " + (pid+1) + ", you are not in jail." + nl;
        }
        return msg; 
    }

    public String printWinnerMessage(List<Integer> w){
        String msg;
        if(w.size() == 1){
            msg = "There is only one player left and the player is ";
            msg += w.get(0);
        }else{
            msg = "There is " + w.size() + "left in the board, they are ";
            for(int i = 0; i < w.size(); i++){
                msg += w.get(i);
            }
        }
        return msg;
    }

    public String printNumberOfPlayerInput(int min, int max){
        /* String msg = "------------------------------------------\nPlease enter the number of players from range of ";
        msg += min + " to " + max + ":";
        return msg; */
        String msg = "------------------------------------------" + nl + "How many players ? ";
        msg += "(" + min + " to " + max + ") : ";
        return msg;
    }
}
