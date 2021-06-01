package com.zopa.quotefinder.service;

import java.util.List;

import com.zopa.quotefinder.valueobject.LenderDetails;
import com.zopa.quotefinder.valueobject.QuoteDetails;

public interface QuotefinderService {

	public QuoteDetails getLowestRateLenderQuote(String loanAmount, List<LenderDetails> lenderDetailsList);
}
