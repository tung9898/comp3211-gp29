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

    public String printPayTax(int currentPlayerMoney, int tax){
        return "Income Tax: " + (currentPlayerMoney - tax) + nl;
    }

    public String printInJailDays(int currentPlayer, int currentPlayerMoneyDaysInJail){ 
        int pid = currentPlayer;
        String msg = "";
        if(currentPlayerMoneyDaysInJail > -1){
            msg = "Player " + (pid+1) + ", you are in jail for " + currentPlayerMoneyDaysInJail + " days." + nl;
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

    public String printAllPlayerStatus(){
        return "";
    }

    public String printPlayerPosition(int currentPlayer, int currentSquare,String name, int price, int rent){ 
        //SquareController.SquareName(pos)
        int pid = currentPlayer;
        String msg = 
            "Player " + pid;

        int pos = currentSquare;
        msg += ", your position is in board " + pos + nl;

        int currentPos[] = new int[20];
        currentPos[pos] = 1;
        msg += printPlayerPositionInMP(currentPos);

        if(name != "-1"){
            msg += "Square Name: " + name + nl;
            msg += "Square Price: " + price + nl;
            msg += "Square Rent: " + rent + nl;
        }
        return msg;
    }

    public String printLeaderBoard(int[][] leaderboard){
        String msg = "Rank" + tab + "Player ID" + tab + "Money" + nl;
        for (int i = 0; i < leaderboard.length; ++i) {
            msg += leaderboard[i][0] + tab + (leaderboard[i][1] + 1) + tab + tab + leaderboard[i][2] + nl;
        }
        return msg;
    }
}
