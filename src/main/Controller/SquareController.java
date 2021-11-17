package Controller;

import Model.Square;

public class SquareController extends Controller{
    /**
     * This function is mainly return value or data of the game back to other function.
     */
    protected static Square[] board = new Square[20];

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
        /*
          call the model square to set the square id
         */
        model.setId(id);
    }

    public int getId() {
        /*
          call the model square to get the square id
         */
        return model.getId();
    }

    public void setOwner(int id) {
        /*
          This function will call model square to set the owner of the property to the owner ID.
          -1 means unowned.
         */
        model.setOwner(id);
    }

    public int getOwner() {
        /*
          This function will call model square to return the player id who own this square.
         */
        return model.getOwner();
    }

    public int GoSalary() {
         /*
           This function is give a player salary if he/she pass though the GO square.
           return the salary to set the money.
          */
        return 1500;
    }

    public int ChanceSalary() {
         /*
           This function randomly pick an integer in a specific range.
           return the value the player win or lose to set the money.
          */
        int upperbound = 200;
        int lowerbound = -300;
        upperbound /= 10;
        lowerbound /= 10;
        //int r = Monopoly.rand.nextInt(50)-30; // random integer number from 20 to -30
        int r = getRandom(Math.abs(upperbound+lowerbound))+lowerbound;

        return r * 10;
    }

    public int SquarePrice(int id) {
         /*
           This function return the price of its square.
          */
        return Price[id];
    }

    public int SquareRent(int id) {
         /*
           This function return the rent of its square.
          */
        return Rent[id];
    }

    public String SquareName(int id) {
        /*
           This function return the name of its square.
          */
        return Name[id];
    }

    public Square[] getSquare(){
        /*
           This function return the list of Square
        */
        return board;
    }

    public void setSquare(int number){
        /*
           This function will create the list of Square
          */
        board[number] = new Square(number);
    }

    public void setBoardId(int number, int id){
        /*
           This function will set the id of Square
          */
        board[number].setId(id);
    }

    public void setBoardOwner(int number, int owner){
        /*
           This function will set the owner of Square
          */
        board[number].setOwner(owner);
    }

/*     public static String getBoardName(int number){
        return SquareName(number);
    } */

    public int getBoardOwner(int number){
        /*
           This function will get the owner of Square
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