package View;

import Controller.SquareController;
import Controller.PlayerController;

public class GameStatusView extends UserInterface{
    /* 
     * This class will print the view of the game status.
    */

    public enum GameStatus {
        Welcome, 
        MonopolyBoard, 
        AllPlayerStatus, 
        printPlayerPosition,
        PlayerPositionInMP,
        Menu
    };

    public String printWelcome(){
        return
        tab + tab + tab + "╔═══════════════════════════════════════╗" + nl +
        tab + tab + tab + "║                                       ║" + nl +
        tab + tab + tab + "║                                       ║" + nl +
        tab + tab + tab + "║                                       ║" + nl +
        tab + tab + tab + "║                                       ║" + nl +
        tab + tab + tab + "║     Welcome to the Monopoly game!!    ║" + nl +
        tab + tab + tab + "║                                       ║" + nl +
        tab + tab + tab + "║              2 - 6 Players            ║" + nl +
        tab + tab + tab + "║                                       ║" + nl +
        tab + tab + tab + "║           [1] START GAME              ║" + nl +
        tab + tab + tab + "║           [2] LOAD GAME               ║" + nl +
        tab + tab + tab + "║                                       ║" + nl +
        tab + tab + tab + "╚═══════════════════════════════════════╝" + nl + nl +
        "====================================================" + nl +
        " INSTRUCTION" + nl +
        "   Description: " + nl +
        "     This is a Monopoly Board Game. Please Enjoy it ~" + nl + nl +
        "   Details: " + nl +
        "====================================================" + nl;
    }

    public String printMonopolyBoard(){ 
        return
        nl +
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

    public String printPlayerPositionInMP(int currentPos[]){ 
        return
        "\t\t\t ╔══════╤══════╤══════╤══════╤══════╤══════╗ \n" +
        "\t\t\t ║  "+(currentPos[10] == 0?"11":" *")+
        "  │  "+(currentPos[11] == 0?"12":" *")+
        "  │  "+(currentPos[12] == 0?"13":" *")+
        "  │  "+(currentPos[13] == 0?"14":" *")+
        "  │  "+(currentPos[14] == 0?"15":" *")+
        "  │  "+(currentPos[15] == 0?"16":" *")+"  ║  \n" +
        "\t\t\t ║──────┼──────┴──────┴──────┴──────┼──────╢  \n" +
        "\t\t\t ║  "+(currentPos[9] == 0?"10":" *")+
        "  │                           │  "+(currentPos[16] == 0?"10":" *")+"  ║  \n" +
        "\t\t\t ║──────┤                           ├──────║  \n" +
        "\t\t\t ║  "+(currentPos[8] == 0?"09":" *")+
        "  │                           │  "+(currentPos[17] == 0?"09":" *")+"  ║  \n" +
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

    public String printPlayerPosition(){ 
        int pid = PlayerController.getCurrentPlayer();
        String msg = 
            "Player " + pid;

        int pos = PlayerController.getPlayerCurrentSquare(pid);
        msg += ", your position is in board " + pos + nl;

        int currentPos[] = new int[20];
        currentPos[pos] = 1;
        msg += printPlayerPositionInMP(currentPos);

        if(SquareController.SquareName(pos) != "-1"){
            msg += "Square Name: " + SquareController.SquareName(pos) + nl;
            msg += "Square Price: " + SquareController.SquarePrice(pos) + nl;
            msg += "Square Rent: " + SquareController.SquareRent(pos) + nl;
        }
        return msg;
    }

    public String printMenu(){ 
        return 
        "**************************\n"+
        "*          MENU          *\n"+
        "**************************\n"+
        "* Options:               *\n" +
        "*      [1] Save file     *\n" +
        "*      [2] Load file     *\n" +
        "**************************\n"; 
    }

}
