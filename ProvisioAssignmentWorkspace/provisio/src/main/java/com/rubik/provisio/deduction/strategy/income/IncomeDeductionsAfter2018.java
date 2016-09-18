package com.rubik.provisio.deduction.strategy.income;
import java.math.BigDecimal;

import com.rubik.provisio.input.WelfareUserInfo;

/**
 * Concrete class implementation for deduction on income AFTER 1/1/2018
 */
public class IncomeDeductionsAfter2018 extends BaseIncomeDeductionCalculator {
	
	// constraint variables 
	private static final double singleLimit=1500;
	private static final double coupleLimit=2500;
	private static final double deductionRate=0.5;
	private static final double perUnit=1;
	
	// based on income limit, deduct the excess income from entitlement
	public void calculateEntitlementAfterDeduction(WelfareUserInfo welfareUserInfo) {
		double excessIncome=0;
		if(welfareUserInfo.isSingle() && welfareUserInfo.getIncome()>singleLimit)
			excessIncome=welfareUserInfo.getIncome()-singleLimit;
		else if(!welfareUserInfo.isSingle() && welfareUserInfo.getIncome()>coupleLimit)
			excessIncome=welfareUserInfo.getIncome()-coupleLimit;
		double deduction = super.calculateDeduction(excessIncome, deductionRate, perUnit);
		double entitlement=welfareUserInfo.getWelfareEntitlement()-deduction;
		entitlement = new BigDecimal(entitlement).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		// final entitlement value shouldn't be less than zero
		welfareUserInfo.setWelfareEntitlement(entitlement>0?entitlement:0);
	}
}
