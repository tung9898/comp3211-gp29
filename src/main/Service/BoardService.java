package Service;
import Model.Square;

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
        return board[number].getName();
    }

    public static int getBoardOwner(int number){
        return board[number].getOwner();
    }

    public static int getBoardPrice(int number){
        return board[number].getPrice();
    }

    public static int getBoardRent(int number){
        return board[number].getRent();
    }

}