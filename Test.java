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
		//Vector<int[]> playerVector;
		System.out.println("Start test of Exbot");
		Exbot eb = new Exbot();
   		Player player = new Player(eb);
   		// Plays 5 matches of 3 games each
   		while (i < 5){
   
   			// Loops until game victory
   			while (status < 1) {
    			// Gets a vector of possible moves
       			temp = eb.getPossibleMoves();
    			
    			if (temp.size()!=0){
     				// takes the first move in vector and uses it
     				//status = eb.makeMoves(temp.get(0));
     				if(eb.getGame()[52] == 1){			
						int [] bestMove = player.move(temp, eb.getGame());
						//System.out.println("Move from NN: " + Arrays.toString(bestMove));
						//System.out.println("Player1 status before: " + status);	
						//System.out.println(Arrays.toString(bestMove));
						//System.out.println("Player1 getGame before: " + Arrays.toString(eb.getGame()));
     					//System.out.println("Player " + eb.getGame()[52]+": " +Arrays.toString(bestMove));
						//status = eb.makeMoves(bestMove);
						status = eb.makeMoves(eb.getPreferedMove());
						//System.out.println("Player1 status after: " + status);
						//System.out.println("Player1 getGame after: " + Arrays.toString(eb.getGame()));
					}
					else{
						//System.out.println("Player " + eb.getGame()[52]+": " +Arrays.toString(temp.get(0)));
						//System.out.println("Player2 Status before: " + status);
						status = eb.makeMoves(temp.get(0));
						//status = eb.makeMoves(eb.getPreferedMove());
						//System.out.println("Player2 status after: " + status + "\n\n");
					}
    			} else {
     				status = eb.makeMoves(tom);
    			}
   			}
   
   			// Prints out the how the game looked when finnished
   			System.out.println("Won " + eb.getGame()[52] + " with the score:  "+
   								 status);
   			// Checks if matchs finnished and sets up next game/match
   			i += Math.abs(eb.resolveVictory());
   			status = 0;
   		}
  	}
}