package View;

import Controller.SquareController;
import Controller.PlayerController;

public class SquareEffectMessageView extends UserInterface{
    /* 
     * This class will print the view of the square effect.
    */

    public enum SquareEffectMessage {
        PassGO, 
        Chancepositive, 
        Chancenegative, 
        PayTax, 
        NoEffect
    }

    public String printPassGO(){ 
        return "Passing Go, gain " + SquareController.GoSalary() + " HKD"; 
    }

    public String printChancepositive(int m){
        return "Congratulations!! you won " + m + " HKD!!" + nl;
    }

    public String printChancenegative(int m){
        return "Bad luck! you need to pay the bank " + m + " HKD." + nl;
    }

    public String printPayTax(int pid){
        return "Income Tax: " + (PlayerController.getPlayerMoney(pid) - SquareController.TaxCalculate(pid)) + nl;
    }

    public String printNoEffect(int sid){
        return "This square has no effect" + nl;
    }
}