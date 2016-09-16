package com.rubik.provisio.deduction.strategy.income;

import com.rubik.provisio.decorator.IDeductionCalculator;

public abstract class BaseIncomeDeductionCalculator implements IDeductionCalculator{

   double calculateDeduction(double excessIncome,double deductionRate,double unit){
		return (excessIncome * (deductionRate/unit));
	}
}
