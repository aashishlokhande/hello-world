package com.rubik.provisio.input;

import java.time.LocalDate;

import com.rubik.provisio.decorator.BaseEntitlements;

/**
 * Bean class to store individual user details
 */
public class WelfareUserInfo {
	
	private int calculateTillYear;
	private LocalDate startDate;
	private double income;
	private int workTenureYear;
	private double totalAsset;
	private double assetIncrementPercentage;
	private boolean isSingle;
	private double welfareEntitlement;

	// Overloaded constructor
	public WelfareUserInfo(int calculateTillYear, LocalDate startDate, double income, int workTenureYear, double totalAsset, double assetIncrementPercentage, boolean isSingle){
		this.calculateTillYear = calculateTillYear;
		this.startDate = startDate;
		this.income = income;
		this.workTenureYear = workTenureYear;
		this.totalAsset = totalAsset;
		this.assetIncrementPercentage = assetIncrementPercentage;
		this.isSingle = isSingle;
		welfareEntitlement=isSingle?BaseEntitlements.Single.getBaseRate():BaseEntitlements.Couple.getBaseRate();
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public int getWorkTenureYear() {
		return workTenureYear;
	}
	public void setWorkTenureYear(int workTenureYear) {
		this.workTenureYear = workTenureYear;
	}
	public double getTotalAsset() {
		return totalAsset;
	}
	public void setTotalAsset(double totalAsset) {
		this.totalAsset = totalAsset;
	}
	public double getAssetIncrementPercentage() {
		return assetIncrementPercentage;
	}
	public void setAssetIncrementPercentage(double assetIncrementPercentage) {
		this.assetIncrementPercentage = assetIncrementPercentage;
	}
	public boolean isSingle() {
		return isSingle;
	}
	public void setSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}

	public double getWelfareEntitlement() {
		return welfareEntitlement;
	}

	public void setWelfareEntitlement(double welfareEntitlement) {
		this.welfareEntitlement = welfareEntitlement;
	}

	public int getCalculateTillYear() {
		return calculateTillYear;
	}

	public void setCalculateTillYear(int calculateTillYear) {
		this.calculateTillYear = calculateTillYear;
	}
}
