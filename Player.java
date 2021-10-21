public class Player{
    String Name;
    int Id;
    int Money;
    int CurrentSquare;
    int DaysInJail = -1;
    boolean bankruptcy = false;

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

    public void setDaysInJail(int daysInJail) {
        /**
         * This funtion set the player in jail days.
         */
        DaysInJail = daysInJail;
    }

    public int getDaysInJail() {
        /**
         * This function will return days of in jail of the player.
         */
        return DaysInJail;
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
}