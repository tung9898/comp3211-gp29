package View;

import java.util.List;
import Model.*;
import Controller.*;
import Service.*;

public class UserInterface {
    String nl = "\n";
    String tab = "\t";
    String space = " ";
    String colon = ":";
    String to = "-";
    
    /* public void printPosition(){

    }

    public void printMonopolyBoard(){

    }

    public void printActionChoice(){

    } */

    public static String PrintWelcomeMessage(){
        //return "================================== \n Welcome to the Monopoly game!! \n==================================\n" +
        return
        "\t\t\t ╔═══════════════════════════════════════╗ \n" +
        "\t\t\t ║                                       ║  \n" +
        "\t\t\t ║                                       ║  \n" +
        "\t\t\t ║                                       ║  \n" +
        "\t\t\t ║                                       ║  \n" +
        "\t\t\t ║     Welcome to the Monopoly game!!    ║  \n" +
        "\t\t\t ║                                       ║  \n" +
        "\t\t\t ║              2 - 6 Players            ║  \n" +
        "\t\t\t ║                                       ║  \n" +
        "\t\t\t ║           [1] START GAME              ║  \n" +
        "\t\t\t ║           [2] LOAD GAME               ║  \n" +
        "\t\t\t ║                                       ║  \n" +
        "\t\t\t ╚═══════════════════════════════════════╝  \n";
    }

    public static String PrintMonopolyBoard(){
        return
        "1 GO\t 2 CENTRAL\t 3 Wan Chai\t 4 INCOME TAX(PAY 10%)\t 5 Stanley\n"+
        "6 JUST VISITING (IN JAIL)\t 7 Shek O\t 8 Mong Kok\t 9 ? CHANCE\t 10 Tsing Yi\n"+
        "11 FREE PARKING\t 12 Shatin\t 13 ? CHANCE\t 14 Tuen Mun\t 15 Tai Po\n"+
        "16 GO TO JAIL\t 17 Sai Kung\t 18 Yuen Long\t 19 ? CHANCE\t 20 Tai O\n"+
        "\t\t\t ╔══════╤══════╤══════╤══════╤══════╤══════╗ \n" +
        "\t\t\t ║  11  │  12  │  13  │  14  │  15  │  16  ║  \n" +
        "\t\t\t ║──────┼──────┴──────┴──────┴──────┼──────╢  \n" +
        "\t\t\t ║  10  │                           │  17  ║  \n" +
        "\t\t\t ║──────┤                           ├──────║  \n" +
        "\t\t\t ║  09  │                           │  18  ║  \n" +
        "\t\t\t ║──────┤          MONOPOLY         ├──────║  \n" +
        "\t\t\t ║  08  │                           │  19  ║  \n" +
        "\t\t\t ║──────┤                           ├──────║  \n" +
        "\t\t\t ║  07  │                           │  20  ║  \n" +
        "\t\t\t ║──────┼──────┬──────┬──────┬──────┼──────║  \n" +
        "\t\t\t ║  06  │  05  │  04  │  03  │  02  │  01  ║  \n" +
        "\t\t\t ╚══════╧══════╧══════╧══════╧══════╧══════╝  \n";
    }

    public static String PrintActionInputMessage(){
        return "Input Your Action (1 or 2) : ";
    }

    public static String PrintMenuInputMessage(){
        return "Press P for menu";
    }

    public static String PrintMenu(){
        return "****** MENU ******\n"+
        "[1] Save file\n" +
        "[2] Load file\n";
    }

    public static String PrintNumberOfPlayerInputMessage(int min, int max){
        /* String msg = "------------------------------------------\nPlease enter the number of players from range of ";
        msg += min + " to " + max + ":";
        return msg; */
        String msg = "------------------------------------------\nHow many players ? ";
        msg += "(" + min + " to " + max + ") : ";
        return msg;
    }

    public static String PrintTurnStartedMessage(int pid){
        return "\nIt is player " + pid + "'s turn";
    }

    public static String PrintTurnEndedMessage(){
        return "------ Turn end ------";
    }

    public static String PrintRoundStartedMessage(){
        return "\n +++++++++++ Round " + StatusController.getRounds() + " Start +++++++++++\n";
    }

    public static String PrintRoundEndedMessage(){
        return "\n +++++++++++ Round End +++++++++++\n";
    }

    public static String PrintRoundOver100Message(){
        return "Game Over, round is over 100.";
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

    public static String PrintRequestRollDiceMessage(){
        return "Press enter to roll the dice: ";
    }

    public static String PrintRollDiceResultMessage(int[] d){
        String msg = "";
        for(int i = 0; i < d.length; i++){
            msg += "Dice " + (i+1) + " : " + d[i] + "\n";
        }
        return msg;
    }

    public String PrintCurrentPositionMessage(int pid){
        String msg = "Player " + pid;
        
        int pos = PlayerService.getPlayerCurrentSquare(pid);
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
        if(PlayerService.getDaysInJail(pid) > -1){
            msg = "Player " + (pid+1) + ", you are in jail for " + PlayerService.getDaysInJail(pid) + " days." + nl;
        }else{
            msg = "Player " + (pid+1) + ", you are not in jail." + nl;
        }
        return msg;
    }

    public String PrintSquarePurchaseMessage(int sid){
        if(BoardService.getBoardPrice(sid) > -1)
            return BoardService.getBoardName(sid) + tab + BoardService.getBoardPrice(sid) + nl + "Press enter to make the deal.";
        return "This property belongs to the government, you cannot purchase this land.";
    }

    public String PrintSquarePurchaseConfirmMessage(int sid){
        return "Are you sure you want to buy " +  BoardService.getBoardName(sid) + " with " + BoardService.getBoardPrice(sid) + " HKD?";
    }

    public String PrintSquareOwnerChangedMessage(int sid){
        if(BoardService.getBoardOwner(sid) != -1){
            return BoardService.getBoardName(sid) + " is belongs to player " + BoardService.getBoardOwner(sid) + " now";
        }
        return BoardService.getBoardName(sid) + " is belongs to the government now";
    }

    public String PrintSquarePurchaseFailedMessage(int sid){
        return "Sorry, you don't have enough money to purchase " +  BoardService.getBoardName(sid) + nl;
    }

    public String PrintSquarePayRentMessage(int sid){
        return "Oh NO! This property is belongs to player " + BoardService.getBoardOwner(sid) + ", you need to pay him/her " + BoardService.getBoardRent(sid) + " HKD";
    }

    public String PrintBankruptcyWarningMessage(int pid){
        return "Your money is not under 0, make your choice wisely.";
    }

    public String PrintBankruptcyMessage(int pid, int[] pprop){
        String msg = "Player " + (pid+1) + "is forced to declare bankruptcy, all your property will be return to government, including: " + nl;
        for(int i = 0; i < pprop.length; i++){
            msg += BoardService.getBoardName(i) + tab;
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
        return "Income Tax: " + (PlayerService.getPlayerMoney(pid) - SquareEffect.TaxCalculate(pid)) + nl;
    }

    public String PrintNoEffectMessage(int sid){
        return "This square has no effect" + nl;
    }
}
