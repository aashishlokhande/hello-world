package com.rubik.provisio.deduction.strategy.asset;

import java.math.BigDecimal;

import com.rubik.provisio.input.WelfareUserInfo;

/**
 * Concrete class implementation for deduction on assets AFTER year 2020
 */
public class AssetBasedDeductionAfter2020 extends AssetBasedDeductionCalculator {

	public void calculateEntitlementAfterDeduction(WelfareUserInfo welfareUserInfo) {
		double totalDeductions = super.calculateAssetBasedDeduction(welfareUserInfo.getTotalAsset());
		double entitlement = welfareUserInfo.getWelfareEntitlement() - totalDeductions;
		entitlement = new BigDecimal(entitlement).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		welfareUserInfo.setWelfareEntitlement(entitlement > 0 ? entitlement : 0);
	}
}
