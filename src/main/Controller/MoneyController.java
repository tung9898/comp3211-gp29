package Controller;

import Model.Player;

public class MoneyController {
    /** 
      *  This controller mainly for actions relates to money
     */

    private Player model;

    public MoneyController(Player model){
        this.model = model;
        //this.view = view;
    }

    public void addMoney(int income){
        /**
         * This function will add the player's money by the income.
         */
        int money = model.getMoney();
        money += income;
        model.setMoney(money);
    }

    public void reduceMoney(int outcome){
        /**
         * This function will subtract the player's money by the income.
         */
        int money = model.getMoney();
        money -= outcome;
        model.setMoney(money);
    }

    public void payTax(){

    }

    public void receiveTax(){
        
    }

}
