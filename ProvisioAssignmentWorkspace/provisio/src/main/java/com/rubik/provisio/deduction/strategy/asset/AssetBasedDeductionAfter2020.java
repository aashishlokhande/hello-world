package com.rubik.provisio.deduction.strategy.asset;

import com.rubik.provisio.input.WelfareUserInfo;

public class AssetBasedDeductionAfter2020 extends AssetBasedDeductionCalculator{

	public void calculateEntitlementAfterDeduction(WelfareUserInfo welfareUserInfo) {
		double totalDeductions = super.calculateAssetBasedDeduction(welfareUserInfo.getTotalAsset());
		double entitlement=welfareUserInfo.getWelfareEntitlement()-totalDeductions;
		welfareUserInfo.setWelfareEntitlement(entitlement>0?entitlement:0);
	}

	
}
