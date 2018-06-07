import java.util.Vector;
import net.*;
import java.io.File;
import java.io.IOException;

public class Player{
	
	//static as it is shared between the two players
	public static NeuralNetwork net = new NeuralNetwork(196, new int[] {40, 1});
	//private final boolean learningMode;
	//private double LAMBDA, ALPHA, BETA;
	//Not static since each player have its own copy of eligibility traces
	//private double [][] Ew = new double[net.hidden[0].length][net.hidden[1].length];
	//private double [][][] Ev = new double[net.input.length][net.hidden[0].length][net.hidden[1].length];
	Exbot eb;

	public Player(/*double lambda, double alpha, boolean learningmode,*/ Exbot eb){
		this.eb = eb;
		/*for (int j = 0; j < net.hidden[0].length; j++)
            for (int k = 0; k < net.hidden[1].length; k++) {
                Ew[j][k]=0.0;
                for (int i = 0; i < net.input.length; i++) Ev[i][j][k] = 0.0;
            }
        LAMBDA = lambda;
        ALPHA = alpha*0.8;
        BETA = alpha*0.2;
        learningMode = learningmode;

        if (new File("SavedNN").isFile()){
            try {
                net = NeuralNetwork.readFrom("SavedNN");
                System.out.println("Import of old NN successful");
            } catch (ClassNotFoundException  e) {
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }*/
	}


	public int[] move(){
		int[] bestmove = null;
		int[] originalBoard = eb.getGame();
		Vector <int[]> temp = eb.getPossibleMoves();
		double expectedUtility = -1.0;
		for (int[] m : temp){
			eb.setGame(originalBoard);
			eb.makeMoves(m);
			double[] output = net.getValue(Utility.boardToVector(eb.getGame()[52], eb.getGame()));
			double utility = Utility.computeUtility(output);
			if(utility > expectedUtility){
				bestmove = m;
				expectedUtility = utility;
			}
		}
		eb.setGame(originalBoard);

		return bestmove;
	}

}