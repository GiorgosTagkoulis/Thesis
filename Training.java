import java.util.Vector;
import java.util.Arrays;
import java.io.IOException;
import java.io.StreamTokenizer;

/**
 * This class handles rules for the game
 */
public class Training {

	public static void main(String[] args) throws IOException{

		// Set the number of games that by self play will train the NN
		int games = 1000;
		//Initialize the variables responsible for the training
		int i = 0; int k = 0;
		int status1 = 0;
		int status2 = 0;
		//int status = 0;
		int[] tom = new int[0];
		Vector<int[]> temp;
		System.out.println("Start the training of the Neural Network");
		Exbot eb = new Exbot();
   		Player player1 = new Player(0.7,0.1,true, eb);
   		Player player2 = new Player(0.7,0.1,true, eb);
   		// Plays 5 matches of 3 games each
   		while (i < games){
   
   			// Loops until game victory
   			while (status1 < 1 && status2 < 1) {
   			
    			// Gets a vector of possible moves
       			temp = eb.getPossibleMoves();
    			
    			if (temp.size()!=0){

     				if(eb.getGame()[52] == 1){			
						int [] bestMove = player1.move(temp, eb.getGame());
                        status1 = eb.makeMoves(bestMove);
					}
					else{
						status2 = eb.makeMoves(player2.move(temp, eb.getGame()));
					}
    			} else {
     				if(eb.getGame()[52] == 1)
     					status1 = eb.makeMoves(tom);
     				else
     					status2 = eb.makeMoves(tom);
    			}
   			}

   			if (status1 > 0){
   				k++;
   				player1.won(eb.getGame());
   				player2.lost(eb.getGame());
   			}
   			else if (status2 > 0){
   				player2.won(eb.getGame());
   				player1.lost(eb.getGame());
   			}

   			// Checks if matchs finnished and sets up next game/match
   			i += Math.abs(eb.resolveVictory());
   			if (i%100 == 0){
   				System.out.println(i);
   			}
   			status1 = 0; status2 = 0;
   		}

   		player1.net.writeTo("SavedNN");
  	}
}