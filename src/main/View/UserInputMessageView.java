package View;

import Service.BoardService;

public class UserInputMessageView extends UserInterface{
    /* 
     * This class will print the alert message asking player to input.
    */

    public enum UserInputMessage {
        BeginActionInput, 
        MenuInput, 
        NumberOfPlayerInput, 
        RequestRollDice, 
        SquarePurchase,
        FileNameInput,
        FileChoiceInput
    };

    public String printBeginActionInput(){
        return "Input Your Action (1 or 2) : ";
    }

    public String printMenuInput(){ 
        return "Press P for menu"; 
    }

    public String printNumberOfPlayerInput(int min, int max){
        /* String msg = "------------------------------------------\nPlease enter the number of players from range of ";
        msg += min + " to " + max + ":";
        return msg; */
        String msg = "------------------------------------------" + nl + "How many players ? ";
        msg += "(" + min + " to " + max + ") : ";
        return msg;
    }

    public String printRequestRollDice(){ 
        return "Press ENTER to roll the dice: "; 
    }

    public String printSquarePurchase(int sid){
        if(BoardService.getBoardPrice(sid) > -1)
            return BoardService.getBoardName(sid) + tab + BoardService.getBoardPrice(sid) + nl + "Press ENTER to make the deal.";
        return "This property belongs to the government, you cannot purchase this land.";
    }

    public String printFileNameInput(){
        return "Input your file name:";
    }

    public String printFileChoiceInput(){
        return "Input your choice (number only):";
    }
}