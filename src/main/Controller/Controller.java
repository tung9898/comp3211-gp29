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

    public static int[] rollingDice() {
        /*
          This function will randomly generate 2 number in a 4 side of die.
         */
        int numberOfDice = 2;
        int numberOfSide = 4;

        int[] r = new int[numberOfDice];
        for(int i = 0; i < numberOfDice; i++)
            r[i] = getRandom(numberOfSide)+1;
        return r;
    }
}
