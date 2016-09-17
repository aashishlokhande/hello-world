package com.rubik.provisio.deduction.strategy.asset;

import com.rubik.provisio.decorator.IDeductionCalculator;

/**
 * Abstract class defining action to be taken for deductions made on assets
 */
public abstract class AssetBasedDeductionCalculator implements IDeductionCalculator{
	
	/**
	 * Legislated rule | Deduction based on the asset value the person has. 
	 * The deduction from the base rate is 10 fold of log(assetValue)
	 * @param assetsworth
	 * @return deduction
	 */
	double calculateAssetBasedDeduction(double assetsworth){
		double deduction=Math.log10(assetsworth)*10;
		return deduction;
	}
}
