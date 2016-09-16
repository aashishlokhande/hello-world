package com.rubik.provisio.deduction.strategy.asset;

import com.rubik.provisio.decorator.IDeductionCalculator;

public abstract class AssetBasedDeductionCalculator implements IDeductionCalculator{

	double calculateAssetBasedDeduction(double assetsworth){
		double deduction=Math.log10(assetsworth)*10;
		return deduction;
	}
}
