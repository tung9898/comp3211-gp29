package View;

import Controller.SquareController;

public class SquareView extends UserInterface{
    
    public String printPassGO(int salary){ 
        //SquareController.GoSalary()
        return "Passing Go, gain " + salary + " HKD"; 
    }

    public String printNoEffect(int sid){
        return "This square has no effect" + nl;
    }

    public String printSquarePurchaseConfirm(String name, int price){
        //SquareController.SquareName(sid)
        return "Are you sure you want to buy " +  name + " with " + price + " HKD?";
    }

    public String printSquarePurchaseFailed(String name){
        return "Sorry, you don't have enough money to purchase " +  name + nl;
    }

    public String printSquarePayRentMessage(int boardOwner, int squareRent){
        //int sid SquareController.getBoardOwner(sid)
        return "Oh NO! This property is belongs to player " + boardOwner + ", you need to pay him/her " + squareRent + " HKD";
    }

    public String printSquareOwnerChanged(int boardOwner, String squareName){
        //int sid) SquareController.getBoardOwner(sid)
        if(boardOwner != -1){
            return squareName + " is belongs to player " + boardOwner + " now";
        }
        return squareName + " is belongs to the government now";
    }

    public String printBankruptcyMessage(int pid, int[] pprop, String squareName){
        //SquareController.SquareName(i)
        String msg = "Player " + (pid+1) + "is forced to declare bankruptcy, all your property will be return to government, including: " + nl;
        for(int i = 0; i < pprop.length; i++){
            msg += squareName + tab;
        }
        return msg+nl;
    }

    public String printSquarePurchase(int squarePrice, String squareName){
        if(squarePrice > -1)
            return squareName + tab + squareName+ nl + "Press ENTER to make the deal.";
        return "This property belongs to the government, you cannot purchase this land.";
    }
}
