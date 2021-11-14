package Controller;

import Model.Square;

public class SquareController {
    /**
      * This function is mainly return value or data of the game back to other function.
    */
    protected static Square[] board = new Square[20];
      
    // data for each Properties that marked by a colored stripe
    protected static String[]Name = {"GO", "Central", "Wan Chai", "INCOME TAX", "Stanley", "JUST VISITING / IN JAIL", "Shek O", "Mong Kok", "CHANCE", "Tsing Yi", "FREE PARKING", "Shatin", "CHANCE", "Tuen Mun", "Tai Po", "GO TO JAIL", "Sai Kung", "Yuen Long", "CHANCE", "Tai O"};
    protected static int[]Price = {-1, 800, 700, -1, 600, -1, 400, 500, -1, 400, -1, 700, -1, 400, 500, -1, 400, 400, -1, 600};
    protected static int[]Rent = {-1, 90, 65, -1, 60, -1, 10, 40, -1, 15, -1, 75, -1, 20, 25, -1, 10, 25, -1, 25};
    
    protected Square model;
    //private SquareView view;

    public SquareController(Square model/* , SquareView view */){
        this.model = model;
        //this.view = view;
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

    public static Square[] getSquare(){
        /**
          * This function return the list of Square
        */
        return board;
    }

    public static void setSquare(int number){
        /**
          * This function will create the list of Square
          */
        board[number] = new Square(number);
    }

    public static void setBoardId(int number, int id){
        /**
          * This function will set the id of Square
          */
        board[number].setId(id);
    }

    public static void setBoardOwner(int number, int owner){
        /**
          * This function will set the owner of Square
          */
        board[number].setOwner(owner);
    }

/*     public static String getBoardName(int number){
        return SquareName(number);
    } */

    public static int getBoardOwner(int number){
        /**
          * This function will get the owner of Square
          */
        return board[number].getOwner();
    }

    /* public static int getBoardPrice(int number){
        return SquarePrice(number);
    }

    public static int getBoardRent(int number){
        return SquareRent(number);
    } */
    
}
