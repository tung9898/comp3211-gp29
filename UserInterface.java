import java.util.List;

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
        return "Round " + GameStatus.rounds + "Start" + nl;
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
        int pos = Monopoly.players[pid].CurrentSquare;
        msg += ", your position is in board " + pos + nl;
        if(SquareEffect.SquareName(pid) != "-1"){
            msg += "Square Name: " + SquareEffect.SquareName(pid) + nl;
            msg += "Square Price: " + SquareEffect.SquarePrice(pid) + nl;
            msg += "Square Rent: " + SquareEffect.SquareRent(pid) + nl;
        }
        return msg;
    }

    public String PrintInJailDaysMessage(int pid){
        return "";
    }

    public String PrintSquarePurchaseMessage(int pid){
        return "";
    }

    public String PrintSquarePurchaseConfirmMessage(int pid){
        return "";
    }

    public String PrintSquarePurchaseSuccessfulMessage(int pid){
        return "";
    }

    public String PrintSquarePurchaseFailedMessage(int pid){
        return "";
    }

    public String PrintSquarePayRentMessage(int pid){
        return "";
    }

    public String PrintBankruptcyWarningMessage(int pid){
        return "";
    }

    public String PrintBankruptcyMessage(int pid, int[] pprop){
        return "";
    }

    // Square Effect
    public String PrintPassGOMessage(){
        return "";
    }

    public String PrintChanceMessage(){
        return "";
    }

    public String PrintPayTaxMessage(){
        return "";
    }

    public String PrintNoEffectMessage(int sid){
        return "";
    }

    public String PrintSquareInfoMessage(int sid){
        return "";
    }

    
}