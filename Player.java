public class Player{
    int Money;
    int CurrentSquare;
    int CurrentStatus;
    int Id;
    String Name;
    int[] Property;

    public void setMoney(int money){
        /**
         * This function will set the player's money.
         * This function will also declare bankruptcy if money is less than 0.
         */
        Money = money;
    }

    public int getMoney() {
        /**
         * This function will return the player's money.
         */
        return Money;
    }

    public void setCurrentSquare(int currentSquare) {
        /**
         * This function will set the player location where he/she current on.
         */
        CurrentSquare = currentSquare;
    }

    public int getCurrentSquare() {
        return CurrentSquare;
    }

    public void setCurrentStatus(int currentStatus) {
        CurrentStatus = currentStatus;
    }

    public int getCurrentStatus() {
        return CurrentStatus;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setProperty(int[] property) {
         /*
            Changed from string array to integer array.
            -2 : Government Property
            -1 : Unowned Property
            >0 : Property that owned by that Id player 

            
         */
        Property = property;
    }

    public int[] getProperty() {
        return Property;
    }
}