package com.rubik.provisio.decorator;

/**
 * Store Base rate based on relationship state.
 * 
 *
 */
public enum BaseEntitlements {
  Single(1000),
  Couple(1200);
  
  private double baseRate;
  
  private BaseEntitlements(double baseRate){
	  this.baseRate=baseRate;
  }
  
  public double getBaseRate(){
	  return baseRate;
  }
}