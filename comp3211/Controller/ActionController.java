package comp3211.Controller;

import java.util.Random;

public class ActionController {
    static Random rand = new Random();
    
    public int[] rollingDice() {
        /**
         * This function will randomly generate 2 number in a specific range.
         */
        int[] r = new int[2];
        r[0] = rand.nextInt(4);
        r[1] = rand.nextInt(4);
        return r;
    }

    public Random getRandomInteger() {
        Random rand = new Random();
        return rand;
    }

}
