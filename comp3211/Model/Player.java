package comp3211.Model;

public class Player{
    protected String Name;
    protected int Id;
    protected int Money;
    protected int CurrentSquare;
    protected int DaysInJail = -1;
    protected boolean Bankruptcy = false;

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

    public void setBankruptcy(boolean bankruptcy){
        /**
         * This funtion set the bankruptcy status of the player.
         */
        Bankruptcy = bankruptcy;
    }

    public boolean getBankruptcy(){
        /**
         * This function will return the bankruptcy status of the player.
         */
        return Bankruptcy;
    }

}