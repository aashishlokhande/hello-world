package com.rubik.provisio.deduction.strategy.income;

import com.rubik.provisio.decorator.IDeductionCalculator;

/**
 * Abstract class defining action to be taken for deductions made on income
 */
public abstract class BaseIncomeDeductionCalculator implements IDeductionCalculator {

	double calculateDeduction(double excessIncome, double deductionRate, double unit) {
		return (excessIncome * (deductionRate / unit));
	}
}
