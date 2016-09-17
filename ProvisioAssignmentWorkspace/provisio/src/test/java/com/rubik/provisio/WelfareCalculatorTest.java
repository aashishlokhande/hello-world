package com.rubik.provisio;

import java.time.LocalDate;

import com.rubik.provisio.input.ReadInputProperties;
import com.rubik.provisio.input.WelfareUserInfo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Welfare Calculator.
 */
public class WelfareCalculatorTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public WelfareCalculatorTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( WelfareCalculatorTest.class );
    }

    /**
     * Test if ReadInputProperties is fetching the right data from resource file
     */
    public void testInputReader(){
    	String propFileName = "userInput.properties";
    	WelfareUserInfo welfareUser = new ReadInputProperties().getWelfareUser(propFileName);
    	assertEquals(3.5,welfareUser.getAssetIncrementPercentage());
    	assertEquals(5000.0,welfareUser.getIncome());
    	assertEquals(40000.00,welfareUser.getTotalAsset());
    	assertEquals(true,welfareUser.isSingle());
    	assertEquals(LocalDate.of(2015, 7, 1),welfareUser.getStartDate());
    	assertEquals(2016,welfareUser.getCalculateTillYear());
    }
    
    /**
     * Test welfare calculation for current year
     */
    public void testEntitlementForCurrentYear()
    {
    	String propFileName = "userInput.properties";
    	WelfareUserInfo welfareUser = new ReadInputProperties().getWelfareUser(propFileName);
        WelfareCalculator calculator=new WelfareCalculator();
        calculator.setWelfareUser(welfareUser);
		double entitlement = calculator.calculateWelfareEntitlement();
		assertEquals(0.0,entitlement);
	}
    
    /**
     * Test welfare calculation for 10 years 
     */
    public void testEntitlementFor10Years()
    {
    	String propFileName = "userInput10Years.properties";
    	WelfareUserInfo welfareUser = new ReadInputProperties().getWelfareUser(propFileName);
        WelfareCalculator calculator=new WelfareCalculator();
        calculator.setWelfareUser(welfareUser);
		double entitlement = calculator.calculateWelfareEntitlement();
		assertEquals(2828.352516,entitlement);
	}
}
