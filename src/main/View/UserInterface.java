package View;

public class UserInterface {
    protected static String nl = "\n";
    protected static String tab = "\t";
    protected static String space = " ";
    protected static String colon = ":";
    protected static String to = "-";

    public static String printBeginActionInput(){
        return "Input Your Action : ";
    }

    public static String printBeginActionInputError(){
        return "Error. Please input again";
    }

    public static String printMenuInput(){ 
        return "Press P for menu"; 
    }

    public static String printRequestRollDice(){ 
        return "Press ENTER to roll the dice: "; 
    }

    public static String printTurnStarted(int i, String name){ 
        return nl + "------------------------------ It is player " + i + " (" + name + ") 's turn ---------------------------------" + nl; 
    }

    public static String printTurnEnded(){ 
        return nl + "------------------------- Turn end -----------------------------" + nl; 
    }

    public static String printDice(int i){
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

    public static String printRollDiceResult(int[] d){
        String msg = "";
        for(int i = 0; i < d.length; i++){
            msg += "Dice " + (i+1) + " : " + printDice(d[i]) + "\n";
        }
        return msg;
    }

    public String printWelcome(){
        return
                "======================================================================================" + nl +
                "     Command-line Monopoly game version 1.0.5" + nl +
                "     Copyright (C) 2021 POLYU SE Group29" + nl + nl +
                "     This is a free Monopoly Board Game. It supports 2-6 multi-players." + nl + nl + nl +
                "***   Game Rules   ***" + nl +
                "-- You can buy property that have price on it" + nl +
                "-- You must pay rent if you step on a property that owned by other player" + nl +
                "-- If you got into jail and you want to pay to leave, you must pay before rolling the dice."  + nl +
                "-- The randomness of rolling the dice and chances is generated by \"java.util.Random\""   + nl +
                "-- After finishing a turn and that player have a negative amount of money, that player will automatically declare bankruptcies." + nl +
                "-- After 100 rounds or the current player that have more that 0 dollars is left to one, the game will finish." + nl +
                        nl + nl + nl +
                "-- Please have fun while playing the game ~!" + nl +
                "======================================================================================" + nl+
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
                tab + tab + tab + "╚═══════════════════════════════════════╝" + nl + nl;
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

    public String printMenu_Style_1(){ 
        return 
        "Options:\n" +
        "[1] Roll the dice\n"+
        "[2] Show leaderboard\n"+
        "[3] Save game\n" +
        "[4] Save & exit the game"; 
    }

    public String printMenu_Style_2(){ 
        return 
        "Options:\n" +
        "[1] Play a NEW game\n" +
        "[2] Exit the game"; 
    }

    public String printNumberOfPlayerInputError(int min, int max){
        return 
        nl +
        " ┌──────────────────────────────────────────────────────────────────────────────────┐" + nl +
        " │ Error: The game only accommodates "+ min +" to " + max +" players. Enter again." + tab + tab + "    │" + nl +
        " └──────────────────────────────────────────────────────────────────────────────────┘" + nl;
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

    public String printRoundEnded(){ 
        return 
        "\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
        "+++++                         Round End                             +++++\n" +
        "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n\n";
    }
}
