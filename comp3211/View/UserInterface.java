package comp3211.View;
import java.util.List;

import comp3211.Monopoly;
import comp3211.Controller.StatusController;
import comp3211.Model.SquareEffect;

public class UserInterface{
    String nl = "\n";
    String tab = "\t";
    String space = " ";
    String colon = ":";
    String to = "-";
    
    public String PrintWelcomeMessage(){
        return "Welcome to the Monopoly game!!";
    }

    public String PrintNumberOfPlayerInputMessage(int min, int max){
        String msg = "Please enter the number of players from range of ";
        msg += min + to + max + colon;
        return msg;
    }

    public String PrintTurnStartedMessage(int pid){
        return "It is player" + pid + "turn" + nl;
    }

    public String PrintTurnEndedMessage(){
        return "Turn end" + nl;
    }

    public String PrintRoundStartedMessage(){
        StatusController controller = new StatusController();
        return "Round " + controller.getRounds() + "Start" + nl;
    }

    public String PrintRoundEndedMessage(){
        return "Round End" + nl;
    }

    public String PrintRoundOver100Message(){
        return "Game Over, round is over 100." + nl;
    }

    public String PrintWinnerMessage(List<Integer> w){
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

    public String PrintRequestRollDiceMessage(){
        return "Press enter to poll the dice: ";
    }

    public String PrintRollDiceResultMessage(int[] d){
        String msg = "";
        for(int i = 0; i < d.length; i++){
            msg += "Dice " + (i+1) + " : " + d[i] + nl;
        }
        return msg;
    }

    public String PrintCurrentPositionMessage(int pid){
        String msg = "Player " + pid;
        int pos = Monopoly.players[pid].getCurrentSquare();
        msg += ", your position is in board " + pos + nl;
        if(SquareEffect.SquareName(pos) != "-1"){
            msg += "Square Name: " + SquareEffect.SquareName(pos) + nl;
            msg += "Square Price: " + SquareEffect.SquarePrice(pos) + nl;
            msg += "Square Rent: " + SquareEffect.SquareRent(pos) + nl;
        }
        return msg;
    }

    public String PrintInJailDaysMessage(int pid){
        String msg = "";
        if(Monopoly.players[pid].getDaysInJail() > -1){
            msg = "Player " + (pid+1) + ", you are in jail for " + Monopoly.players[pid].getDaysInJail() + " days." + nl;
        }else{
            msg = "Player " + (pid+1) + ", you are not in jail." + nl;
        }
        return msg;
    }

    public String PrintSquarePurchaseMessage(int sid){
        if(Monopoly.board[sid].getPrice() > -1)
            return Monopoly.board[sid].getName() + tab + Monopoly.board[sid].getPrice() + nl + "Press enter to make the deal.";
        return "This property is belong to the government, you cannot purchase this land.";
    }

    public String PrintSquarePurchaseConfirmMessage(int sid){
        return "Are you sure you want to buy " +  Monopoly.board[sid].getName() + " with " + Monopoly.board[sid].getPrice() + " HKD?";
    }

    public String PrintSquareOwnerChangedMessage(int sid){
        if(Monopoly.board[sid].getOwner() != -1){
            return Monopoly.board[sid].getName() + " is belongs to player " + Monopoly.board[sid].getOwner() + " now";
        }
        return Monopoly.board[sid].getName() + " is belongs to the government now";
    }

    public String PrintSquarePurchaseFailedMessage(int sid){
        return "Sorry, you don't have enough money to purchase " +  Monopoly.board[sid].getName() + nl;
    }

    public String PrintSquarePayRentMessage(int sid){
        return "Oh NO! This property is belongs to player " + Monopoly.board[sid].getOwner() + ", you need to pay him/her " + Monopoly.board[sid].getRent() + " HKD";
    }

    public String PrintBankruptcyWarningMessage(int pid){
        return "Your money is not under 0, make your choice wisely.";
    }

    public String PrintBankruptcyMessage(int pid, int[] pprop){
        String msg = "Player " + (pid+1) + "is forced to declare bankruptcy, all your property will be return to government, including: " + nl;
        for(int i = 0; i < pprop.length; i++){
            msg += Monopoly.board[i].getName() + tab;
        }
        return msg+nl;
    }

    // Square Effect
    public String PrintPassGOMessage(){
        return "Passing Go, gain " + SquareEffect.GoSalary() + " HKD";
    }

    public String PrintChancepositiveMessage(int m){
        return "Congratulations!! you won " + m + " HKD!!" + nl;
    }

    public String PrintChancenegativeMessage(int m){
        return "Bad luck! you need to pay the bank " + m + " HKD." + nl;
    }

    public String PrintPayTaxMessage(int pid){
        return "Income Tax: " + (Monopoly.players[pid].getMoney() - SquareEffect.TaxCalculate(pid)) + nl;
    }

    public String PrintNoEffectMessage(int sid){
        return "This square has no effect" + nl;
    }
}