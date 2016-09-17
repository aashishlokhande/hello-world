package com.rubik.provisio;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rubik.provisio.decorator.BaseEntitlements;
import com.rubik.provisio.factory.DeductionFactory;
import com.rubik.provisio.input.WelfareUserInfo;

/**
 * Welfare Calculator Program to generate entitlement for provided tenure
 * 1. Get user information
 * 2. Calculate total income for tenure
 * 3. Calculate total deductions for tenure
 * 4. Return final welfare entitlement
 */
public class WelfareCalculator {

	private WelfareUserInfo welfareUser;
	static final Logger logger = LogManager.getLogger(WelfareCalculator.class.getName());
	
	/**
	 * 1. Get user information in a bean
	 * @param welfareUser
	 */
	public void setWelfareUser(WelfareUserInfo welfareUser) {
		this.welfareUser = welfareUser;
	}
	
	/**
	 * Welfare calculator
	 * @return final welfare entitlement amount
	 */
	public double calculateWelfareEntitlement() {
		
		// temporary date objects for looping through calculations
		LocalDate tempDate = welfareUser.getStartDate();
		LocalDate lastWorkYear=tempDate.plusYears(welfareUser.getWorkTenureYear()).minusDays(1);
		
		// calculation to be done for Year provided OR default next 10 years
		int calculateForYears = welfareUser.getCalculateTillYear()!=0 ? welfareUser.getCalculateTillYear() - tempDate.getYear() : 10;
		
		logger.trace("----------------------------------------");
		logger.trace("Started welfare entitlement calculation");
		logger.trace("----------------------------------------");
		// process data for number of years to calculate
		for(int i = 0; i < calculateForYears; i++){
			if(i>0){
				// 2. Calculate total income for tenure
				if(welfareUser.getAssetIncrementPercentage()>0)
					welfareUser.setTotalAsset(welfareUser.getTotalAsset() + (welfareUser.getTotalAsset() * (welfareUser.getAssetIncrementPercentage() / 100)));
				welfareUser.setWelfareEntitlement(welfareUser.getWelfareEntitlement()+getBaseEntitlement(welfareUser.isSingle()));
			}
			
			if(tempDate.isAfter(lastWorkYear))
				welfareUser.setIncome(0);
			
			// 3. Calculate total deductions for tenure
			DeductionFactory.getDeductionCalculator(tempDate, false).calculateEntitlementAfterDeduction(welfareUser);
			DeductionFactory.getDeductionCalculator(tempDate, true).calculateEntitlementAfterDeduction(welfareUser);
			logger.trace("Status at the end of : " + tempDate + "\n" +
						 "\tIncome : " + welfareUser.getIncome()+"\n"+
					     "\tTotal Assets : " + welfareUser.getTotalAsset() + "\n" +
						 "\tWelfare Entitlement : " + welfareUser.getWelfareEntitlement());
			// increment processing year by 1
			tempDate=tempDate.plusYears(1);
		}
		logger.trace("Final Welfare Entitlement : " + welfareUser.getWelfareEntitlement());
		logger.trace("--------------------------------------");
		logger.trace("Ended welfare entitlement calculation");
		logger.trace("--------------------------------------");
		// 4. Return final welfare entitlement
		return welfareUser.getWelfareEntitlement();
	}

	/**
	 * Method returns the base rate user is entitled to based on marital status
	 * @param isSingle
	 * @return base entitlement rate
	 */
	private double getBaseEntitlement(boolean isSingle) {
		return isSingle ? BaseEntitlements.Single.getBaseRate() : BaseEntitlements.Couple.getBaseRate();
	}
	
}
