package Service;
import Model.Square;
import Controller.SquareController;

public class BoardService {

    public static Square[] board = new Square[20];

    public static Square[] getSquare(){
        return board;
    }

    public static void setSquare(int number){
        board[number] = new Square(number);
    }

    public static void setBoardId(int number, int id){
        board[number].setId(id);
    }

    public static void setBoardOwner(int number, int owner){
        board[number].setOwner(owner);
    }

    public static String getBoardName(int number){
        return SquareController.SquareName(number);
    }

    public static int getBoardOwner(int number){
        return board[number].getOwner();
    }

    public static int getBoardPrice(int number){
        return SquareController.SquarePrice(number);
    }

    public static int getBoardRent(int number){
        return SquareController.SquareRent(number);
    }

}