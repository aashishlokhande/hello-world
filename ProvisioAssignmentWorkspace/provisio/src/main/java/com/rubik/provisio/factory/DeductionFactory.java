package com.rubik.provisio.factory;

import java.time.LocalDate;

import com.rubik.provisio.decorator.IDeductionCalculator;
import com.rubik.provisio.deduction.strategy.asset.AssetBasedDeductionAfter2020;
import com.rubik.provisio.deduction.strategy.asset.AssetBasedDeductionBefore2020;
import com.rubik.provisio.deduction.strategy.income.IncomeDeductionsAfter2018;
import com.rubik.provisio.deduction.strategy.income.IncomeDeductionsPriorTo2018;

public class DeductionFactory {
	private static final LocalDate incomeDeductionChangeYear= LocalDate.of(2018, 1, 1);
	private static final LocalDate assetDeductionChangeYear= LocalDate.of(2020, 1, 1);
	
	public static IDeductionCalculator getDeductionCalculator(LocalDate startDate,boolean isForAsset){
		IDeductionCalculator deductionCalculator;
		if(!isForAsset){
			if(startDate.isBefore(incomeDeductionChangeYear)){
				deductionCalculator= new IncomeDeductionsPriorTo2018();
			}else{
				deductionCalculator= new IncomeDeductionsAfter2018();
			}
		}else{
			if(startDate.isBefore(assetDeductionChangeYear))
				deductionCalculator =new AssetBasedDeductionBefore2020();
			else	
				deductionCalculator =new AssetBasedDeductionAfter2020();
		}	
		return deductionCalculator;
	}

}