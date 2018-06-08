import java.util.Vector;
import net.*;
import java.io.File;
import java.io.IOException;

import java.util.Arrays;

public class Player{
	
	//static as it is shared between the two players
	public static NeuralNetwork net = new NeuralNetwork(196, new int[] {40, 1});
	private final boolean learningMode;
	private double LAMBDA, ALPHA, BETA;
	//Not static since each player have its own copy of eligibility traces
	private double [][] Ew = new double[net.hidden[0].length][net.hidden[1].length];
	private double [][][] Ev = new double[net.input.length][net.hidden[0].length][net.hidden[1].length];
	Exbot eb;

	public Player(double lambda, double alpha, boolean learningmode, Exbot eb){
		this.eb = eb;
		for (int j = 0; j < net.hidden[0].length; j++)
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
        }
	}


	public int[] move(Vector <int[]> temp, int[] getGame){
		int[] bestmove = null;
		//int[] originalBoard = eb.getGame();
		int[] originalBoard = getGame;
		int[] nextBoard = null;
		int currentPlayer = getGame[52];		
		//Vector <int[]> temp = eb.getPossibleMoves();
		double expectedUtility = -1.0;
		for (int[] m : temp){
			//System.out.println(Arrays.toString(m));
			int move = eb.makeMoves(m);
			switch(move){
				case 0: 	double[] output = net.getValue(Utility.boardToVector(eb.getGame()[52], eb.getGame()));
							double utility = Utility.computeUtility(output);
							if(utility > expectedUtility){
								bestmove = m;
								expectedUtility = utility;
								nextBoard = eb.getGame();
							}
							eb.setGame(originalBoard);
				case 1: 	bestmove = m;
							nextBoard = eb.getGame();
							eb.setGame(originalBoard);
				case 2: 	bestmove = m;
							nextBoard = eb.getGame();				
							eb.setGame(originalBoard);
				case 3: 	bestmove = m;
							nextBoard = eb.getGame();				
							eb.setGame(originalBoard);
				case 4: 	bestmove = m;
							nextBoard = eb.getGame();				
							eb.setGame(originalBoard);
				case 6:		bestmove = m;
							nextBoard = eb.getGame();				
							eb.setGame(originalBoard);
				default:	eb.setGame(originalBoard);
					
			}
		}
		
		if(learningMode){
			double [] currentInput = Utility.boardToVector(currentPlayer, originalBoard);
			double [] currentOutput = net.getValue(currentInput);
			double[] nextOutput = net.getValue(Utility.boardToVector(currentPlayer, nextBoard));
			backprop(currentInput, currentOutput, nextOutput);
		}

		return bestmove;
	}


	   public static double gradient(HiddenUnit u) {
        return u.getValue() * (1.0 - u.getValue());
    }



    /* Ew and Ev must be set up somewhere to the proper size and set to 0 */
    public void backprop(double[] in, double[] out, double[] expected) {
        /* compute eligibility traces */
        for (int j = 0; j < net.hidden[0].length; j++)
            for (int k = 0; k < out.length; k++) {
                /* ew[j][k] = (lambda * ew[j][k]) + (gradient(k)*hidden_j) */
                Ew[j][k] = (LAMBDA * Ew[j][k]) + (gradient(net.hidden[1][k]) * net.hidden[0][j].getValue());
                for (int i = 0; i < in.length; i++)
                    /* ev[i][j][k] = (lambda * ev[i][j][k]) + (gradient(k)+w[j][k]+gradient(j)+input_i)*/
                    Ev[i][j][k] = ( ( LAMBDA * Ev[i][j][k] ) + ( gradient(net.hidden[1][k]) * net.hidden[1][k].weights[j] * gradient(net.hidden[0][j])* in[i]));
            }
        double error[] = new double[out.length];
        for (int k =0; k < out.length; k++)
            error[k] = expected[k] - out[k];
        for (int j = 0; j < net.hidden[0].length; j++)
            for (int k = 0; k < out.length; k++) {
                /* weight from j to k, shown with learning param of BETA */
                net.hidden[1][k].weights[j] += BETA * error[k] * Ew[j][k];
                for (int i = 0; i < in.length; i ++) {
                    net.hidden[0][j].weights[i] += ALPHA * error[k] * Ev[i][j][k];
                }
            }
    }

}