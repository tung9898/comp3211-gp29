package Controller;

import Model.Square;

public class SquareController extends Controller{
    /**
      * This function is mainly return value or data of the game back to other function.
    */
    protected static Square[] board = new Square[20];
      
    // data for each Properties that marked by a colored stripe
    protected static int[]PropertyPos = {2, 3, 5, 7, 8, 10, 12, 14, 15, 17, 18, 20};
    protected static String[]Name = {"GO", "Central", "Wan Chai", "INCOME TAX", "Stanley", "JUST VISITING / IN JAIL", "Shek O", "Mong Kok", "CHANCE", "Tsing Yi", "FREE PARKING", "Shatin", "CHANCE", "Tuen Mun", "Tai Po", "GO TO JAIL", "Sai Kung", "Yuen Long", "CHANCE", "Tai O"};
    protected static int[]Price = {-1, 800, 700, -1, 600, -1, 400, 500, -1, 400, -1, 700, -1, 400, 500, -1, 400, 400, -1, 600};
    protected static int[]Rent = {-1, 90, 65, -1, 60, -1, 10, 40, -1, 15, -1, 75, -1, 20, 25, -1, 10, 25, -1, 25};
    
    protected Square model;
    //private SquareView view;

    public SquareController(){}
    public SquareController(Square model/* , SquareView view */){
        this.model = model;
        //this.view = view;
    }

    public static void initBoard(){
        int squareNumber = 20;
        for(int i = 0; i < squareNumber; i++){
            board[i] = new Square(i, -1, Name[i], Price[i], Rent[i]);
        }
    }

    public void setId(int id) {
        /**
         * call the model square to set the square id
         */
        model.setId(id);
    }

    public int getId() {
        /**
         * call the model square to get the square id
         */
        return model.getId();
    }

    public void setOwner(int id) {
        /**
         * This function will call model square to set the owner of the property to the owner Id.
         * -1 means unowned.
         */
        model.setOwner(id);
    }

    public int getOwner() {
        /**
         * This function will call model square to return the player id who own this square.
         */
        return model.getOwner();
    }

     public int GoSalary() {
         /**
          * This function is give a player salary if he/she pass though the GO square.
          * return the salary to set the money.
          */
        return 1500;
     }

     public int ChanceSalary() {
         /**
          * This function randomly pick a integer in a specific range.
          * return the value the player win or lose to set the money.
          */
        int upperbound = 200;
        int lowerbound = -300;
        upperbound /= 10;
        lowerbound /= 10;
        //int r = Monopoly.rand.nextInt(50)-30; // random integer number from 20 to -30
        int r = getRandom(Math.abs(upperbound+lowerbound))+lowerbound;
        
        return r * 10;
     }

     public int NoEffect() {
         /**
          * No effect square, this function do nothing, just the its name.
          */
         return 0;
     }

     public int SquarePrice(int id) {
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

     public int SquareRent(int id) {
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

    public String SquareName(int id) {
        /**
          * This function return the name of its square.
          */
        //*** is checking id==-1 necessary? id is initialized as 0 in Square class
        if(id==-1) return "-1";
        return Name[id];
    }

    public boolean isGovernmentProperty(int id) {
        for(int i = 0; i < PropertyPos.length; i++)
            if(id+1 == PropertyPos[i]) return false;
        return true;
    }

    public int getEffect() {
        /**
         * This function will return a integer, for Go, Change, Income tax.
         * 0 for other or no effect squares.
         * Go to jail will be consider as no effect.
         */
        return 0;
    }

    public Square[] getSquare(){
        /**
          * This function return the list of Square
        */
        return board;
    }

    public void setSquare(int number){
        /**
          * This function will create the list of Square
          */
        board[number] = new Square(number);
    }

    public void setBoardId(int number, int id){
        /**
          * This function will set the id of Square
          */
        board[number].setId(id);
    }

    public void setBoardOwner(int number, int owner){
        /**
          * This function will set the owner of Square
          */
        board[number].setOwner(owner);
    }

/*     public static String getBoardName(int number){
        return SquareName(number);
    } */

    public int getBoardOwner(int number){
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
