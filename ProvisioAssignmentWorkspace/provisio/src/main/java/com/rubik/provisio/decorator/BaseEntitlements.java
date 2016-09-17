package com.rubik.provisio.decorator;

/**
 * Enumeration to store base rate per year depending on marital status
 * 1. Single - 1000
 * 2. Couple - 2000
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