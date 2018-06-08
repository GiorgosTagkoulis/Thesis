import java.util.Vector;
import java.util.Arrays;

/**
 * This class handles rules for the game
 */
public class Test {

	public static void main(String[] args){

		int i = 0;
		int status1 = 0;
		int status2 = 0;
		//int status = 0;
		int[] tom = new int[0];
		Vector<int[]> temp;
		//Vector<int[]> playerVector;
		System.out.println("Start test of Exbot");
		Exbot eb = new Exbot();
   		Player player1 = new Player(0,0,false, eb);
   		Player player2 = new Player(0,0,false, eb);
   		// Plays 5 matches of 3 games each
   		while (i < 5){
   
   			// Loops until game victory
   			while (status1 < 1 && status2 < 1) {
   			//while (status < 1){
    			// Gets a vector of possible moves
       			temp = eb.getPossibleMoves();
    			
    			if (temp.size()!=0){
     				// takes the first move in vector and uses it
     				//status = eb.makeMoves(temp.get(0));
     				if(eb.getGame()[52] == 1){			
						int [] bestMove = player1.move(temp, eb.getGame());

						status1 = eb.makeMoves(bestMove);
					}
					else{
						//status = eb.makeMoves(eb.getPreferedMove());
						//status2 = eb.makeMoves(temp.get(0));
						status2 = eb.makeMoves(player2.move(temp, eb.getGame()));
					}
    			} else {
     				if(eb.getGame()[52] == 1)
     					status1 = eb.makeMoves(tom);
     				else
     					status2 = eb.makeMoves(tom);
    			}
   			}

   			if (status1 > 0)
   				System.out.println("Player 1 won with the score: "+ status1);
   			else if (status2 > 0)
   				System.out.println("Player 2 won with the score: "+ status2);
			
   
   		/*	// Prints out the how the game looked when finnished
   			System.out.println("Won " + eb.getGame()[52] + " with the score:  "+
   								 status); */
   			// Checks if matchs finnished and sets up next game/match
   			i += Math.abs(eb.resolveVictory());
   			status1 = 0; status2 = 0;
   			//status = 0;
   		}
  	}
}