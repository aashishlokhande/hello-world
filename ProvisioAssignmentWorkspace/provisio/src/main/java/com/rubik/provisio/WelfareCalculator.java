package com.rubik.provisio;

import java.time.LocalDate;
import com.rubik.provisio.decorator.BaseEntitlements;
import com.rubik.provisio.factory.DeductionFactory;
import com.rubik.provisio.input.ReadInputProperties;
import com.rubik.provisio.input.WelfareUserInfo;

/**
 * Welfare Calculator Program to generate entitlement for 10yrs tenure
 * 
 */
public class WelfareCalculator {
	
	private WelfareUserInfo welfareUser;
	/**
	 * Welfare Entitlement Calculator
	 * 1. Reads input from prop file
	 * 2. Calculate total income for tenure
	 * 3. Calculate total deductions for tenure
	 * 4. Calculate final welfare entitlement
	 * @return finalEntitlementAmount
	 */
	public double calculateWelfareEntitlement() {
		
		
		LocalDate tempDate = welfareUser.getStartDate();
		// Calculation to be done for Year provided OR default next 10 years
		int calculateForYears = welfareUser.getCalculateTillYear()!=0 ? welfareUser.getCalculateTillYear() - tempDate.getYear() : 10;
		LocalDate lastWorkYear=tempDate.plusYears(welfareUser.getWorkTenureYear()).minusDays(1);
		for(int i = 0; i < calculateForYears; i++){
			if(i>0){
				if(welfareUser.getAssetIncrementPercentage()>0)
					welfareUser.setTotalAsset(welfareUser.getTotalAsset() + (welfareUser.getTotalAsset() * (welfareUser.getAssetIncrementPercentage() / 100)));
				welfareUser.setWelfareEntitlement(welfareUser.getWelfareEntitlement()+getBaseEntitlement(welfareUser.isSingle()));
			}
			if(tempDate.isAfter(lastWorkYear))
				welfareUser.setIncome(0);
			DeductionFactory.getDeductionCalculator(tempDate, false).calculateEntitlementAfterDeduction(welfareUser);
			DeductionFactory.getDeductionCalculator(tempDate, true).calculateEntitlementAfterDeduction(welfareUser);
			System.out.println(tempDate+" "+welfareUser.getIncome()+" "+welfareUser.getTotalAsset()+" "+welfareUser.getWelfareEntitlement());
			// increment processing year by 1
			tempDate=tempDate.plusYears(1);
			//annual asset increment
			
		}
		System.out.println(welfareUser.getWelfareEntitlement());
		return welfareUser.getWelfareEntitlement();
	}

	private double getBaseEntitlement(boolean isSingle) {
		return isSingle ? BaseEntitlements.Single.getBaseRate() : BaseEntitlements.Couple.getBaseRate();
	}

	public void setWelfareUser(WelfareUserInfo welfareUser) {
		this.welfareUser = welfareUser;
	}
	
	
}
