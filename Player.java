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
        /**
         * This function will return the location where the player is current on.
         */
        return CurrentSquare;
    }

    public void setCurrentStatus(int currentStatus) {
        /**
         * This funtion set the player status, the status are as follows:
         * -1 : bankruptcy
         *  0 : no effect
         * >0 : days of in jail.
         */
        CurrentStatus = currentStatus;
    }

    public int getCurrentStatus() {
        /**
         * This function will return the current status of the player
         */
        return CurrentStatus;
    }

    public void setId(int id) {
        /**
         * This function set the player ID.
         */
        Id = id;
    }

    public int getId() {
        /**
         * This function will return the ID of the player
         */
        return Id;
    }

    public void setName(String name) {
        /**
         * This function set the player name.
         */
        Name = name;
    }

    public String getName() {
        /**
         * This function will return the name of the player
         */
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