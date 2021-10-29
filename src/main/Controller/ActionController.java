package Controller;

import java.util.Random;

public class ActionController {
    static Random rand = new Random();

    public static int getRandom(int min, int max){
        return rand.nextInt(max - min + 1) + min;
    }
    
    public static int[] rollingDice() {
        /**
         * This function will randomly generate 2 number in a specific range.
         */
        int[] r = new int[2];
        r[0] = rand.nextInt(4);
        r[1] = rand.nextInt(4);
        return r;
    }
 
}
