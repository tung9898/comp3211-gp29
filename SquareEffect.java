public class SquareEffect {
     /**
      * This function is mainly return value or data of the game back to other function.
      */
    
      // data for each Properties that marked by a colored stripe
     static int[]Pos = {2, 3, 5, 7, 8, 10, 12, 14, 15, 17, 18, 20};
     static String[]Name = {"Central", "Wan Chai", "Stanley", "Shek O", "Mong Kok", "Tsing Yi", "Shatin", "Tuen Mun", "Tai Po", "Sai Kung", "Yuen Long", "Tai O"};
     static int[]Price = {800, 700, 600, 400, 500, 400, 700, 400, 500, 400, 400, 600};
     static int[]Rent = {90, 65, 60, 10, 40, 15, 75, 20, 25, 10, 25, 25};
    
     public static int EffectCenter(int squareId) {
         /**
          * This function is to redirect the sqaure to its belonging effect
          */
         switch(squareId+1){    
             case 1:    
                 return GoSalary();
             case 9:
             case 13:
             case 19:
                 return ChanceSalary();
             case 4:
                 return PayTax();
             default:     
                 return NoEffect();
        }    
     }

     public static int GoSalary() {
         /**
          * This function is give a player salary if he/she pass though the GO square.
          * return the salary to set the money.
          */
        return 1500;
     }

     public static int ChanceSalary() {
         /**
          * This function randomly pick a integer in a specific range.
          * return the value the player win or lose to set the money.
          */
         int upperbound = 200;
         int lowerbound = -300;
         upperbound /= 10;
         lowerbound /= 10;
         int r = Monopoly.rand.nextInt(Math.abs(upperbound+lowerbound))+lowerbound; // random integer number from 20 to -30
         return r * 10;
     }

     public static int PayTax() {
         /*
          * This function will take the player 10% of his/her money for tax.
          * */
         return TaxCalculate(Monopoly.CurrentPlayer);
     }

     public static int TaxCalculate(int id) {
        int percent = 10;
        int x = Monopoly.players[id].Money;
        x = x / (100 - percent);
        x = x - x % 10;
        return x;
     }

     public static int NoEffect() {
         /**
          * No effect square, this function do nothing, just the its name.
          */
         return 0;
     }

     public static int SquarePrice(int id) {
         /**
          * This function return the price of its square.
          */
          if(id==-1) return -1;
         return Price[IdtoPos(id)];
     }

     public static int SquareRent(int id) {
         /**
          * This function return the rent of its square.
          */
          if(id==-1) return -1;
         return Rent[IdtoPos(id)];
     }

    public static String SquareName(int id) {
        /**
          * This function return the name of its square.
          */
          if(id==-1) return "-1";
        return Name[IdtoPos(id)];
    }

    public static int IdtoPos(int id){
        for(int i = 0; i < Pos.length; i++){
            if(id+1 == Pos[i]) return i;
        }
        return -1;
    }
}
