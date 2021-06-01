package com.zopa.quotefinder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zopa.quotefinder.service.QuotefinderServiceImpl;
import com.zopa.quotefinder.valueobject.LenderDetails;
import com.zopa.quotefinder.valueobject.QuoteDetails;

@RestController
public class QuotefinderController {
	
	@Autowired
	private QuotefinderServiceImpl quotefinderService;

	@GetMapping(value = "/zopaRate/{loanAmount}", produces = MediaType.APPLICATION_JSON_VALUE)
	public QuoteDetails getZopaLenderQuote(@PathVariable String loanAmount, @RequestBody List<LenderDetails> lenderDetailsList) {
		
		return quotefinderService.getLowestLenderQuote(loanAmount, lenderDetailsList);
	}
	
}
