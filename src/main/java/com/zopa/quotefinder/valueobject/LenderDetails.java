package com.zopa.quotefinder.valueobject;

public class LenderDetails implements Comparable<LenderDetails> {

	private String name;
	private String rate;
	private String availableAmount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(String availableAmount) {
		this.availableAmount = availableAmount;
	}
	
	@Override
    public int compareTo(LenderDetails o) {
        return this.getRate().compareTo(o.getRate());
    }
}
