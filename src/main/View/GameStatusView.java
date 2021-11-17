package View;

import Controller.GameStatusController;

public class GameStatusView extends UserInterface{
    /* 
     * This class will print the view of the game status.
    */

    public String printRoundStarted(int r){
        return 
        "\n\n\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
        "+++++                   Round " + r + " Start                             +++++\n" +
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
