import java.util.Vector;
import java.util.Arrays;
/**
 * This class handles rules for the game
 */
public class Test {

	public static void main(String[] args){

		int i = 0;
		int status = 0;
		int[] tom = new int[0];
		Vector<int[]> temp;
		Vector<int[]> playerVector;
		System.out.println("Start test of Exbot");
		Exbot eb = new Exbot();
   
   		// Plays 5 matches of 3 games each
   		while (i < 1){
   
   			// Loops until game victory
   			while (status < 1) {
    			// Gets a vector of possible moves
       			temp = eb.getPossibleMoves();
    			System.out.println("Player " + eb.getGame()[52] + ":");
    			System.out.println("GetGame: " + Arrays.toString(eb.getGame()));
    			playerVector = Utility.playerStatus(eb.getGame());
    			playerVector.toString();
    			for(int[] p: playerVector)
    				System.out.println(Arrays.toString(p)); 
    			double [] nninput = Utility.boardToVector(eb.getGame()[52],eb.getGame());
    			System.out.println(Arrays.toString(nninput) + "\n");		
    			//temp.toString();
    			//for(int[] m: temp)
    			//System.out.println(Arrays.toString(m));
   				//System.out.println();
   				//System.out.println("Prefered Move: " + Arrays.toString(eb.gtePreferedMove()));
    			if (temp.size()!=0){
     				// takes the first move in vector and uses it
     				//status = eb.makeMoves(temp.get(0));
     				if(eb.getGame()[52] == PLAYER_1)
						status = eb.makeMoves(eb.getPreferedMove());
					else
						status = eb.makeMoves(temp.get(0));
    			} else {
     				status = eb.makeMoves(tom);
    			}
   			}
   
   			// Prints out the how the game looked when finnished
   			System.out.println("Won " + eb.getGame()[52] + " with the score:  " + status + "\n\n");
   			// Checks if matchs finnished and sets upp next game/match
   			i += Math.abs(eb.resolveVictory());
   			status = 0;
   		}
  	}

}