package View;

import Controller.SquareController;

public class SquareView extends UserInterface{

    public String printPassGO(int salary){
        return "Passing through GO, gain $" + salary + nl;
    }

    public String printBankruptcyMessage(int pid, int[] pprop, String squareName){
        //SquareController.SquareName(i)
        String msg = "Player " + (pid+1) + "is forced to declare bankruptcy, all your property will be return to government, including: " + nl;
        for(int i = 0; i < pprop.length; i++){
            msg += squareName + tab;
        }
        return msg+nl;
    }

    public String printSquarePurchase(String squareName,int squarePrice, int balance){
        return squareName + " is unowned" + nl + "Price of " + squareName + " is $" + squarePrice + nl + "You have $" + balance + nl;
    }

    public String printSquarePurchaseSuccess(String squareName, int balance){
        return "You have bought " + squareName + nl + "Remaining amount of money: $" + balance + nl;
    }

    public String printSquarePurchaseFailed(String squareName, int balance){
        return "Sorry, you don't have enough money to purchase " + squareName + nl + "You only have $" + balance + nl;
    }

    public String printSquarePurchaseNo(String squareName){
        return "You chose not to buy " + squareName + ". No effect." + nl;
    }

    public String printSquarePayRentMessage(String squareName,int squareOwner, int squareRent){
        return squareName+" is owned by player " + squareOwner + nl +
                "You have to pay the rent of $" + squareRent + " to player " + squareOwner +nl;
    }
}
