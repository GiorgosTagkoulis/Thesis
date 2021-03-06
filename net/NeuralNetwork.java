package net;

import java.io.*;

public class NeuralNetwork implements Serializable {
  
  // serialver for backwards compatibility
  private static final long serialVersionUID = 1165374168397424904L;

  // the layers of the network
  public InputUnit[] input;
  public HiddenUnit[][] hidden;
  
  /**
   * Builds a neural network with the given number of input
   * units, hidden units, and output units.  Thus, calling
   * 
   * new NeuralNetwork(10, new int[] {20, 5});
   *
   * creates a neural network with 10 input units, a layer of
   * 20 hidden units, and then 5 output units.
   *
   * @param input The number of input units
   * @param hidden The number of hidden units, as well as the number of layers
   */
  public NeuralNetwork(int input, int[] hidden) {
    this.input = new InputUnit[input];
    this.hidden = new HiddenUnit[hidden.length][];
    
    for (int i=0; i<hidden.length; i++)
      this.hidden[i] = new HiddenUnit[hidden[i]];
        
    for (int i=0; i<input; i++) 
      this.input[i] = new InputUnit();
    
    for (int i=0; i<hidden.length; i++)
      for (int j=0; j<hidden[i]; j++)
        if (i == 0)
          this.hidden[i][j] = new HiddenUnit(this.input);
        else
          this.hidden[i][j] = new HiddenUnit(this.hidden[i-1]);
  }

  
  /**
   * Calculates the network value given the provided input
   *
   * @param input The input to check
   * @return The network value from this input
   */
  public double[] getValue(double[] input) {
    double[] result = new double[hidden[hidden.length-1].length];
    
    for (int i=0; i<input.length; i++)
      this.input[i].setValue(input[i]);
    
    for (int i=0; i<hidden.length; i++)
      for (int j=0; j<hidden[i].length; j++)
        this.hidden[i][j].recompute();
    
    for (int j=0; j<hidden[hidden.length-1].length; j++) 
      result[j] = this.hidden[hidden.length-1][j].getValue();
    
    return result;
  }
  
  /**
   * Method which writes this network to the given file
   *
   * @param filename The file to write to
   */
  public void writeTo(String filename) throws IOException {
    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
    oos.writeObject(this);
    oos.flush();
    oos.close();
  }
  
  /**
   * Method which reads and returns a network from the given file
   *
   * @param filename The file to read from
   */
  public static NeuralNetwork readFrom(String filename) throws IOException, ClassNotFoundException {
    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
    NeuralNetwork net = (NeuralNetwork) ois.readObject();
    ois.close();
    
    return net;
  }
}