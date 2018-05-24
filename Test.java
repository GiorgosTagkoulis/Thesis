import java.util.Vector;
import java.util.Arrays;
/**
 * This class handles rules for the game
 */
public class Test {
 public static void main(String[] args)
  {
   int i = 0;
   int status = 0;
   int[] tom = new int[0];
   Vector<int[]> temp;
   System.out.println("Start test of Exbot");
   Exbot eb = new Exbot();
   
   // Plays 5 matches of 3 games each
   while (i < 1){
   
   	// Loops until game victory
   	while (status < 1) {
    	// Gets a vector of possible moves
    	temp = eb.getPossibleMoves();
    	temp.toString();
    	System.out.println("Possible Moves are:");
   		for(int[] k : temp)
   			System.out.print(Arrays.toString(k) + "\t");
   		System.out.println();
    	if (temp.size()!=0) {
     		// takes the first move in vector and uses it
     		//status = eb.makeMoves(temp.get(0));
     		status = eb.makeMoves(eb.getPreferedMove());
    	} else {
     		status = eb.makeMoves(tom);
    	}
   }
   
   // Prints out the how the game looked when finnished
   System.out.println(eb);

   // Checks if matchs finnished and sets upp next game/match
   i += Math.abs(eb.resolveVictory());
   status = 0;
   }
  }
}
