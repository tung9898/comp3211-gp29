package Controller;

import java.util.Random;

import View.UserInterface;

public class Controller {
    /** 
      *  This controller mainly for player's action
     */

    protected static Random rand = new Random();

    public static int getRandom(int number){
        return rand.nextInt(number);
    }

    public static int getRandom(int min, int max){
        return rand.nextInt(max - min + 1) + min;
    }
    
    public static int[] rollingDice() {
        /**
         * This function will randomly generate 2 number in a specific range.
         */
        int numberOfDice = 2;
        int numberOfSide = 4;

        int[] r = new int[numberOfDice];
        r[0] = rand.nextInt(numberOfSide)+1;
        r[1] = rand.nextInt(numberOfSide)+1;
        return r;
    }
}
