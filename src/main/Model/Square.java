 package Model;

public class Square{
    protected int Id;
    protected int Owner;
    protected String Name;
    protected int Price;
    protected int Rent;

    public Square(int id){
        setId(id);
        setOwner(-1);
    }

    public Square(int id, int owner, String name, int price, int rent){
        Id = id;
        Owner = owner;
        Name = name;
        Price = price;
        Rent = rent;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getRent() {
        return Rent;
    }

    public void setRent(int rent) {
        Rent = rent;
    }
}