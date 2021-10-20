public class Square{
    int Id;
    int PriceTag;
    int Rent;
    int Landlord;

    public void setOwner(int id) {
        /**
         * This function set the owner of the property.
         */
        Landlord = id;
    }

    public int getOwner() {
        /**
         * This function will return the player id who own this square.
         */
        return Landlord;
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

    public int getPrice() {
        /**
         * This function will find the Price of its square and return it.
         */
        return 0;
    }

    public int getRent() {
        /**
         * This function will find the Rent of its square and return it.
         */
        return 0;
    }

    public int getEffect() {
        /**
         * This function will return a integer, for Go, Change, Income tax.
         * 0 for other or no effect squares.
         * Go to jail will be consider as no effect.
         */
        return 0;
    }
}