 package Model;

public class Square{
    protected int Id;
    protected int Owner;

    public Square(int id){
        setId(id);
        setOwner(-1);
    }
    
    public void setId(int id) {
        /**
         * set the square id
         */
        Id = id;
    }

    public int getId() {
        /**
         * get the square id
         * (should be useless)
         */
        return Id;
    }

    public void setOwner(int id) {
        /**
         * This function set the owner of the property to the owner Id.
         * -1 means unowned.
         */
        Owner = id;
    }

    public int getOwner() {
        /**
         * This function will return the player id who own this square.
         */
        return Owner;
    }
}