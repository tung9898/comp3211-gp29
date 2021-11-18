package View;

import java.util.List;

import Controller.PlayerController;

public class PlayerView extends UserInterface{

    public String printBankruptcyWarning(){
        return "Your money is now under 0, make your choice wisely.";
    }

    public String printMoney(int money){
        return "You now have $" + money + nl;
    }

    public String printChancePositive(int m){
        return "Congratulations!! you won $" + m + "!" + nl;
    }

    public String printChanceNegative(int m){
        return "Sorry! you lost $" + m + "." + nl;
    }

    public String printPayTax(int tax){
        return "You have to pay $" + tax + " tax." + nl;
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

    public String printNumberOfPlayerInput(int min, int max){
        /* String msg = "------------------------------------------\nPlease enter the number of players from range of ";
        msg += min + " to " + max + ":";
        return msg; */
        String msg = "How many players ? ";
        msg += "(" + min + " to " + max + ") : ";
        return msg;
    }

    public String printPlayerPositionInMP(int currentPos[]){ 
        return
        "1 GO\t 2 CENTRAL\t 3 Wan Chai\t 4 INCOME TAX(PAY 10%)\t 5 Stanley\n"+
        "6 JUST VISITING (IN JAIL)\t 7 Shek O\t 8 Mong Kok\t 9 ? CHANCE\t 10 Tsing Yi\n"+
        "11 FREE PARKING\t 12 Shatin\t 13 ? CHANCE\t 14 Tuen Mun\t 15 Tai Po\n"+
        "16 GO TO JAIL\t 17 Sai Kung\t 18 Yuen Long\t 19 ? CHANCE\t 20 Tai O\n\n"+
        "\t\t\t ╔══════╤══════╤══════╤══════╤══════╤══════╗ \n" +
        "\t\t\t ║  "+(currentPos[10] == 0?"11":" *")+
        "  │  "+(currentPos[11] == 0?"12":" *")+
        "  │  "+(currentPos[12] == 0?"13":" *")+
        "  │  "+(currentPos[13] == 0?"14":" *")+
        "  │  "+(currentPos[14] == 0?"15":" *")+
        "  │  "+(currentPos[15] == 0?"16":" *")+"  ║  \n" +
        "\t\t\t ║──────┼──────┴──────┴──────┴──────┼──────╢  \n" +
        "\t\t\t ║  "+(currentPos[9] == 0?"10":" *")+
        "  │                           │  "+(currentPos[16] == 0?"17":" *")+"  ║  \n" +
        "\t\t\t ║──────┤                           ├──────║  \n" +
        "\t\t\t ║  "+(currentPos[8] == 0?"09":" *")+
        "  │                           │  "+(currentPos[17] == 0?"18":" *")+"  ║  \n" +
        "\t\t\t ║──────┤          MONOPOLY         ├──────║  \n" +
        "\t\t\t ║  "+(currentPos[7] == 0?"08":" *")+
        "  │                           │  "+(currentPos[18] == 0?"19":" *")+"  ║  \n" +
        "\t\t\t ║──────┤                           ├──────║  \n" +
        "\t\t\t ║  "+(currentPos[6] == 0?"07":" *")+
        "  │                           │  "+(currentPos[19] == 0?"20":" *")+"  ║  \n" +
        "\t\t\t ║──────┼──────┬──────┬──────┬──────┼──────║  \n" +
        "\t\t\t ║  "+(currentPos[5] == 0?"06":" *")+
        "  │  "+(currentPos[4] == 0?"05":" *")+
        "  │  "+(currentPos[3] == 0?"04":" *")+
        "  │  "+(currentPos[2] == 0?"03":" *")+
        "  │  "+(currentPos[1] == 0?"02":" *")+
        "  │  "+(currentPos[0] == 0?"01":" *")+"  ║  \n" +
        "\t\t\t ╚══════╧══════╧══════╧══════╧══════╧══════╝  \n";
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
        String msg = "==============================================\n";
        msg += "Rank" + tab + "Player ID" + tab + "Money" + nl;
        for (int i = 0; i < leaderboard.length; ++i) {
            // leaderboard[i] is the i rank in the LeaderBoard
            // leaderboard[i][0] is the rank number, they will be the same if they own the same balance.
            // leaderboard[i][1] is the player ID and leaderboard[i][2] is the player balance.
            msg += leaderboard[i][0] + tab + tab + (leaderboard[i][1] + 1) + tab + "$" + leaderboard[i][2] + nl;
        }
        msg += "==============================================\n";
        return msg;
    }

    public String printWinner(List<String> winnerList){
        String msg = "Winners List" + nl;
        for (int i = 0; i < winnerList.size(); ++i) {
            msg += "Player " + winnerList.get(i) + nl;
        }
        return msg;
    }
}
