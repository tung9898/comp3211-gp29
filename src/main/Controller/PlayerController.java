package Controller;
import Model.Player;

public class PlayerController {
    /** 
      *  This controller mainly for actions relates to player
     */

    protected Player model;
    //private PlayerView view;

    public PlayerController(Player model/* , PlayerView view */){
        this.model = model;
        //this.view = view;
    }

    public void createPlayer(){
        
    }

    public void removePlater(){

    }

    public void retirePlayer(){
        
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
        //
    }

    public void receiveTax(){
        //
    }
}
