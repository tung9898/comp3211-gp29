package View;

import Service.BoardService;

public class AlertMessageView extends UserInterface{
    /* 
     * This class will print the view of alert message.
    */
    
    public enum AlertMessage {
        SquarePurchaseConfirm, 
        SquarePurchaseFailed, 
        BankruptcyWarning,
        SquarePayRent,
        LoadFileSuccess,
        FileExistMessage
    };

    public String printSquarePurchaseConfirm(int sid){
        return "Are you sure you want to buy " +  BoardService.getBoardName(sid) + " with " + BoardService.getBoardPrice(sid) + " HKD?";
    }

    public String printSquarePurchaseFailed(int sid){
        return "Sorry, you don't have enough money to purchase " +  BoardService.getBoardName(sid) + nl;
    }

    public String printBankruptcyWarning(){ 
        return "Your money is not under 0, make your choice wisely."; 
    }

    public String printSquarePayRentMessage(int sid){
        return "Oh NO! This property is belongs to player " + BoardService.getBoardOwner(sid) + ", you need to pay him/her " + BoardService.getBoardRent(sid) + " HKD";
    }

    public String printLoadFileSuccessMessage(){
        return "Load Successfully~";
    }

    public String printFileExistMessage(){
        return "Here are the files:";
    }
    
}