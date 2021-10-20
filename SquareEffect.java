public class SquareEffect {
    /**
     * This function is mainly return value or data of the game back to other function.
     */

     public void EffectCenter(int squareId) {
        /**
         * This function is to redirect the sqaure to its belonging effect
         */
        switch(squareId+1){    
            case 1:    
                GoSalary();
            break;

            case 9:
            case 13:
            case 19:
                ChanceSalary();
            break;

            case 4:
                PayTax();
            break;

            default:     
                NoEffect();
            break;
            }    
     }

     public int GoSalary() {
         /**
          * This function is give a player salary if he/she pass though the GO square.
          * return the salary to set the money.
          */
        return 1500;
     }

     public int ChanceSalary() {
         /**
          * This function randomly pick a integer in a specific range.
          return the value the player win or lose to set the money.
          */
        int r = 0; // random integer number from 20 to -30
        return r * 10;
     }

     public int PayTax() {
        /*This function will take the player 10% of his/her money for tax.
         * x = x / 10 * 9;
         * x = x - x % 10;
         * setMoney(x)
         * */
        return 0;
     }

     public int NoEffect() {
         /**
          * No effect square, this function do nothing, just the its name.
          */
         return 0;
     }
}
