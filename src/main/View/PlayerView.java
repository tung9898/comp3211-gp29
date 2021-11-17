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

    public String printPlayerPosition(String name, int price, int rent){ 
        //SquareController.SquareName(pos)
        int pid = PlayerController.getCurrentPlayer();
        String msg = 
            "Player " + pid;

        int pos = PlayerController.getPlayerCurrentSquare(pid);
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
}
