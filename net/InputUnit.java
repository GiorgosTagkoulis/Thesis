package net;

public class InputUnit implements Unit {
  
  // the current value of the input
  protected double value;
  
  /**
   * Builds a hidden unit taking the provided number of
   * inputs.  Sets the initial weights to be random
   * values, using the provided RNG.
   */
  protected InputUnit() {
  }
  
  /**
   * Sets the value of this input unit
   *
   * @return the value
   */
  protected void setValue(double value) {
    this.value = value;
  }
  
  /**
   * Recomputes the value of this hidden unit, querying it's
   * prior inputs.
   */
  public void recompute() {
  }
  
  /**
   * Returns the current value of this input
   *
   * @return The current value of this input
   */
  public double getValue() {
    return value;
  }
}