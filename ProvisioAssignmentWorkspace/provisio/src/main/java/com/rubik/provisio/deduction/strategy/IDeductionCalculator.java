package com.rubik.provisio.deduction.strategy;

import com.rubik.provisio.input.WelfareUserInfo;

/**
 * Deduction calculator interface using strategy pattern to let the program change
 * the algorithm runtime. 
 */
public interface IDeductionCalculator {
	
	/**
	 * Calculate entitlement after deduction
	 * @param welfareUserInfo
	 */
	public void calculateEntitlementAfterDeduction(WelfareUserInfo welfareUserInfo);
}
