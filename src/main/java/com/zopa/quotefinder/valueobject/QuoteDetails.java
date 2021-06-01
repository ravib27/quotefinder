package com.zopa.quotefinder.valueobject;

public class QuoteDetails {

	private final String message;
	private final String requestedAmt;
	private final String annualIntRate;
	private final String monthlyRepaymentAmt;
	private final String totalRepaymentAmt;
	
	public QuoteDetails(String message, String requestedAmt, String annualIntRate, String monthlyRepaymentAmt, String totalRepaymentAmt) {
		this.message = message;
		this.requestedAmt = requestedAmt;
		this.annualIntRate = annualIntRate;
		this.monthlyRepaymentAmt = monthlyRepaymentAmt;
		this.totalRepaymentAmt = totalRepaymentAmt;
	}
	
	public String getMessage() {
		return message;
	}

	public String getRequestedAmt() {
		return requestedAmt;
	}

	public String getAnnualIntRate() {
		return annualIntRate;
	}

	public String getMonthlyRepaymentAmt() {
		return monthlyRepaymentAmt;
	}

	public String getTotalRepaymentAmt() {
		return totalRepaymentAmt;
	}
	
}
