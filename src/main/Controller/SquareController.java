package Controller;

import Model.Square;
import Service.PlayerService;

public class SquareController {
    /**
      * This function is mainly return value or data of the game back to other function.
      */
      
    // data for each Properties that marked by a colored stripe
    private static int[]PropertyPos = {2, 3, 5, 7, 8, 10, 12, 14, 15, 17, 18, 20};
    private static String[]Name = {"GO", "Central", "Wan Chai", "INCOME TAX", "Stanley", "JUST VISITING / IN JAIL", "Shek O", "Mong Kok", "CHANCE", "Tsing Yi", "FREE PARKING", "Shatin", "CHANCE", "Tuen Mun", "Tai Po", "GO TO JAIL", "Sai Kung", "Yuen Long", "CHANCE", "Tai O"};
    private static int[]Price = {-1, 800, 700, -1, 600, -1, 400, 500, -1, 400, -1, 700, -1, 400, 500, -1, 400, 400, -1, 600};
    private static int[]Rent = {-1, 90, 65, -1, 60, -1, 10, 40, -1, 15, -1, 75, -1, 20, 25, -1, 10, 25, -1, 25};

    private Square model;
    //private SquareView view;

    public SquareController(Square model/* , SquareView view */){
        this.model = model;
        //this.view = view;
    }

     public static int EffectCenter(int squareId) {
         /**
          * This function is to redirect the sqaure to its belonging effect
          */
         switch(squareId+1){    
             case 1:    
                 return GoSalary();
             case 9:
             case 13:
             case 19:
                 return ChanceSalary();
             case 4:
                 return PayTax();
             default:     
                 return NoEffect();
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
        int upperbound = 200;
        int lowerbound = -300;
        upperbound /= 10;
        lowerbound /= 10;
        //int r = Monopoly.rand.nextInt(50)-30; // random integer number from 20 to -30
        int r = ActionController.getRandom(Math.abs(upperbound+lowerbound))+lowerbound;
        
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
        //*** is checking id==-1 necessary? id is initialized as 0 in Square class
        if(id==-1) return -1;
        if (!isGovernmentProperty(id)){
            return Price[id];
        }
        return -1;
     }

     public static int SquareRent(int id) {
         /**
          * This function return the rent of its square.
          */
        //*** is checking id==-1 necessary? id is initialized as 0 in Square class
        if(id==-1) return -1;
        if (!isGovernmentProperty(id)){
            return Rent[id];
        }
        return -1;
     }

    public static String SquareName(int id) {
        /**
          * This function return the name of its square.
          */
        //*** is checking id==-1 necessary? id is initialized as 0 in Square class
        if(id==-1) return "-1";
        return Name[id];
    }

    public static boolean isGovernmentProperty(int id) {
        for(int i = 0; i < PropertyPos.length; i++)
            if(id+1 == PropertyPos[i]) return false;
        return true;
    }

    //***not yet tested
    public int getEffect() {
        /**
         * This function will return a integer, for Go, Change, Income tax.
         * 0 for other or no effect squares.
         * Go to jail will be consider as no effect.
         */
        return 0;
    }
    
}
