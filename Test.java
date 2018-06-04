import java.util.Vector;
import java.util.Arrays;
/**
 * This class handles rules for the game
 */
public class Test {

 private static final int PLAYER_1 = 1;
 private static final int PLAYER_2 = -1;

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
    		System.out.println("Player " + activePlayer(eb.getGame()[52]) + ":");
    		playerVector = playerStatus(eb.getGame());
    		playerVector.toString();
    		for(int[] p: playerVector)
    			System.out.println(Arrays.toString(p));    		
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
   System.out.println("Won " + eb.getGame()[52] + " with the score:  " + status);
   // Checks if matchs finnished and sets upp next game/match
   i += Math.abs(eb.resolveVictory());
   status = 0;
   }
  }

  public static Vector<int[]> playerStatus(int[] getGame){
  	Vector<int[]> players = new Vector<int[]>(2);
  	int [] player1 = new int[24];
  	int [] player2 = new int[24];
  	if(getGame[52] == PLAYER_1){
  		System.arraycopy(getGame, 1, player1, 0, 24);
  		System.arraycopy(getGame, 27, player2, 0, 24);
  		players.add(player1); players.add(player2);
  		return players;
  	}else{
  		//Player 1
  		for (int i=0; i<12; i++)
  			player1[i] = getGame[i+13];
  		for (int i=12; i<24; i++)
  			player1[i] = getGame[i-11];
  		//Player 2
  		for (int i=0; i<12; i++)
  			player2[i] = getGame[i+39];
  		for (int i=12; i<24; i++)
  			player2[i] = getGame[i+15];
  		players.add(player2); players.add(player1);
  	}
  	return players;
  }

  public static int activePlayer(int player){
  	if (player == PLAYER_1)
  		return PLAYER_1;
  	else
  		return PLAYER_2;
  }
}