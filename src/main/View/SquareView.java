package View;

import Controller.SquareController;

public class SquareView extends UserInterface{
    
    public String printPassGO(){ 
        return "Passing Go, gain " + SquareController.GoSalary() + " HKD"; 
    }

    public String printNoEffect(int sid){
        return "This square has no effect" + nl;
    }

    public String printSquarePurchaseConfirm(int sid){
        return "Are you sure you want to buy " +  SquareController.SquareName(sid) + " with " + SquareController.SquarePrice(sid) + " HKD?";
    }

    public String printSquarePurchaseFailed(int sid){
        return "Sorry, you don't have enough money to purchase " +  SquareController.SquareName(sid) + nl;
    }

    public String printSquarePayRentMessage(int sid){
        return "Oh NO! This property is belongs to player " + SquareController.getBoardOwner(sid) + ", you need to pay him/her " + SquareController.SquareRent(sid) + " HKD";
    }

    public String printSquareOwnerChanged(int sid){
        if(SquareController.getBoardOwner(sid) != -1){
            return SquareController.SquareName(sid) + " is belongs to player " + SquareController.getBoardOwner(sid) + " now";
        }
        return SquareController.SquareName(sid) + " is belongs to the government now";
    }

    public String printBankruptcyMessage(int pid, int[] pprop){
        String msg = "Player " + (pid+1) + "is forced to declare bankruptcy, all your property will be return to government, including: " + nl;
        for(int i = 0; i < pprop.length; i++){
            msg += SquareController.SquareName(i) + tab;
        }
        return msg+nl;
    }

    public String printSquarePurchase(int sid){
        if(SquareController.SquarePrice(sid) > -1)
            return SquareController.SquareName(sid) + tab + SquareController.SquarePrice(sid) + nl + "Press ENTER to make the deal.";
        return "This property belongs to the government, you cannot purchase this land.";
    }
}
