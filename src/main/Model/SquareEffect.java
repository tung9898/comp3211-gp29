package Model;

import Controller.ActionController;

import Service.PlayerService;

public class SquareEffect extends Square {

     public SquareEffect(int id) {
        super(id);
    }

    /**
      * This function is mainly return value or data of the game back to other function.
      */
    static int[] Price = {800, 700, 600, 400, 500, 400, 700, 400, 500, 400, 400, 600};
    static int[] Rent = {90, 65, 60, 10, 40, 15, 75, 20, 25, 10, 25, 25};
    static String[] Name = {"Central", "Wan Chai", "Stanley", "Shek O", "Mong Kok", "Tsing Yi", "Shatin", "Tuen Mun", "Tai Po", "Sai Kung", "Yuen Long", "Tai O"};

    public int getEffect() {
        /**
         * This function will return a integer, for Go, Change, Income tax.
         * 0 for other or no effect squares.
         * Go to jail will be consider as no effect.
         */
        return 0;
    }

     public static void EffectCenter(int squareId) {
         /**
          * This function is to redirect the sqaure to its belonging effect
          */
         switch(squareId+1){    
             case 1:    
                 GoSalary();
             break;
 
             case 9:
             case 13:
             case 19:
                 ChanceSalary();
             break;
 
             case 4:
                 PayTax();
             break;
 
             default:     
                 NoEffect();
             break;
        }    
     }

     public static int GoSalary() {
         /**
          * This function is give a player salary if he/she pass though the GO square.
          * return the salary to set the money.
          */
        return 1500;
     }

     public static int ChanceSalary() {
         /**
          * This function randomly pick a integer in a specific range.
          * return the value the player win or lose to set the money.
          */
        //int r = Monopoly.rand.nextInt(50)-30; // random integer number from 20 to -30
        int r = ActionController.getRandom(-30, 20);
        return r * 10;
     }

    //***not yet tested
    public static int PayTax() {
        /*
         * This function will take the player 10% of his/her money for tax.
         * */
        return TaxCalculate(PlayerService.getCurrentPlayer());
    }

    public static int TaxCalculate(int id) {
       int percent = 10;
       int x = PlayerService.getPlayerMoney(id);
       x = x / (100 - percent);
       x = x - x % 10;
       return x;
    }

     public static int NoEffect() {
         /**
          * No effect square, this function do nothing, just the its name.
          */
         return 0;
     }

     public static int SquarePrice(int id) {
         /**
          * This function return the price of its square.
          */
         return Price[id];
     }

     public static int SquareRent(int id) {
         /**
          * This function return the rent of its square.
          */
         return Rent[id];
     }

    public static String SquareName(int id) {
        /**
          * This function return the name of its square.
          */
        return Name[id];
    }
}
