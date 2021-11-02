package View;

import java.util.List;

import Controller.StatusController;
import Service.BoardService;
import Service.PlayerService;

public class SystemMessageView extends UserInterface{
    /* 
     * This class will print the message of system.
    */

    public enum SystemMessage {
        TurnStarted, 
        TurnEnded, 
        RoundStarted, 
        RoundEnded, 
        RoundOver100,
        printDice,
        RollDiceResult, 
        InJailDays, 
        SquareOwnerChanged, 
        Bankruptcy,
        Winner
    }

    public String printTurnStarted(int i){ 
        return nl + "------------------------‖ It is player " + i + "'s turn ‖------------------------" + nl; 
    }

    public String printTurnEnded(){ 
        return nl + "------------------------------‖ Turn end ‖------------------------------" + nl; 
    }

    public String printRoundStarted(){ 
        return 
        "\n\n\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
        "+++++                   Round " + StatusController.getRounds() + " Start                             +++++\n" +
        "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n"; 
    }

    public String printRoundEnded(){ 
        return 
        "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
        "+++++                         Round End                             +++++\n" +
        "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n\n";
    }

    public String printRoundOver100(){ 
        return "Game Over, round is over 100.";
    }

    public String printDice(int i){
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

    public String printRollDiceResult(int[] d){
        String msg = "";
        for(int i = 0; i < d.length; i++){
            msg += "Dice " + (i+1) + " : " + printDice(d[i]) + "\n";
        }
        return msg;
    }

    public String printInJailDays(){ 
        int pid = PlayerService.currentPlayer;
        String msg = "";
        if(PlayerService.getDaysInJail(pid) > -1){
            msg = "Player " + (pid+1) + ", you are in jail for " + PlayerService.getDaysInJail(pid) + " days." + nl;
        }else{
            msg = "Player " + (pid+1) + ", you are not in jail." + nl;
        }
        return msg; 
    }

    public String printSquareOwnerChanged(int sid){
        if(BoardService.getBoardOwner(sid) != -1){
            return BoardService.getBoardName(sid) + " is belongs to player " + BoardService.getBoardOwner(sid) + " now";
        }
        return BoardService.getBoardName(sid) + " is belongs to the government now";
    }

    public String PrintBankruptcyMessage(int pid, int[] pprop){
        String msg = "Player " + (pid+1) + "is forced to declare bankruptcy, all your property will be return to government, including: " + nl;
        for(int i = 0; i < pprop.length; i++){
            msg += BoardService.getBoardName(i) + tab;
        }
        return msg+nl;
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

    /* public String printPlayerJsonData(String name, int id, int money, int currentSquare, int daysInJail, boolean bankruptcy){
        return 
            "\nName: " + name +
            "\nId: " + name + 
            "\nMoney: " + money + 
            "\nCurrentSquare: " + currentSquare +
            "\nDaysInJail: " + daysInJail +
            "\nBankruptcy: " + bankruptcy;
    } */
}