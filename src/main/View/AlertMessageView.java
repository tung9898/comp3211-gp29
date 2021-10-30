package View;

import Service.BoardService;

public class AlertMessageView extends UserInterface{
    
    public enum AlertMessage {
        SquarePurchaseConfirm, 
        SquarePurchaseFailed, 
        BankruptcyWarning,
        SquarePayRent
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

    public String PrintSquarePayRentMessage(int sid){
        return "Oh NO! This property is belongs to player " + BoardService.getBoardOwner(sid) + ", you need to pay him/her " + BoardService.getBoardRent(sid) + " HKD";
    }
}