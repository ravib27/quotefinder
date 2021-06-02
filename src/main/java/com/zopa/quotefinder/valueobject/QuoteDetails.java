package com.zopa.quotefinder.valueobject;

public class QuoteDetails {

	private String message;
	private String requestedAmt;
	private String annualIntRate;
	private String monthlyRepaymentAmt;
	private String totalRepaymentAmt;
	
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
