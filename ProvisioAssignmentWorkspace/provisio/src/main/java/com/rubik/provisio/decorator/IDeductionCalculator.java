package com.rubik.provisio.decorator;

import com.rubik.provisio.input.WelfareUserInfo;

public interface IDeductionCalculator {
	public void calculateEntitlementAfterDeduction(WelfareUserInfo welfareUserInfo);
}
