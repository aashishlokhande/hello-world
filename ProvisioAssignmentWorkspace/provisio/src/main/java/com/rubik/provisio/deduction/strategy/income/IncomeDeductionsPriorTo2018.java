package com.rubik.provisio.deduction.strategy.income;

import com.rubik.provisio.input.WelfareUserInfo;

public class IncomeDeductionsPriorTo2018 extends BaseIncomeDeductionCalculator {
	private static final double singleLimit=1500;
	private static final double coupleLimit=2500;
	private static final double deductionRate=0.4;
	private static final double perUnit=1;
	
	
	public void calculateEntitlementAfterDeduction(WelfareUserInfo welfareUserInfo) {
		double excessIncome=0;
		
		if(welfareUserInfo.isSingle() && welfareUserInfo.getIncome()>singleLimit)
			excessIncome=welfareUserInfo.getIncome()-singleLimit;
		else if(!welfareUserInfo.isSingle() && welfareUserInfo.getIncome()>coupleLimit)
			excessIncome=welfareUserInfo.getIncome()-coupleLimit;
		double deduction = super.calculateDeduction(excessIncome, deductionRate, perUnit);
		double entitlement=welfareUserInfo.getWelfareEntitlement()-deduction;
		welfareUserInfo.setWelfareEntitlement(entitlement>0?entitlement:0);
		
	}
}
