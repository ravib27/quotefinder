package com.zopa.quotefinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zopa.quotefinder.service.QuotefinderService;
import com.zopa.quotefinder.valueobject.LenderDetails;
import com.zopa.quotefinder.valueobject.QuoteDetails;

@RestController
public class QuotefinderController {
	
	@Autowired
	private QuotefinderService quotefinderService;

	@PostMapping(value = "/zopaRate/{loanAmount}", produces = MediaType.APPLICATION_JSON_VALUE)
	public QuoteDetails getZopaLenderQuote(@PathVariable String loanAmount, @RequestBody List<LenderDetails> lenderDetailsList) {
		QuoteDetails quoteDetails = quotefinderService.getLowestRateLenderQuote(loanAmount, lenderDetailsList);
		return quoteDetails;
	}
	
}
