package com.zopa.quotefinder.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.zopa.quotefinder.valueobject.LenderDetails;
import com.zopa.quotefinder.valueobject.QuoteDetails;

@Service
public class QuotefinderServiceImpl implements QuotefinderService {
	
	Logger logger = LoggerFactory.getLogger(QuotefinderServiceImpl.class);
	
	private int MONTHS_IN_YEAR = 12;
	private int LOAN_TENURE_IN_YEARS = 3;

	@Override
	public QuoteDetails getLowestRateLenderQuote(String loanAmount, List<LenderDetails> lenderDetailsList) 
	{		
		BigDecimal principalLoanAmtRequested = BigDecimal.ZERO;
	
		String message = "";
		String requestedAmount = "NA";
		String annualInterestRate = "NA";
		String monthlyRepayment = "NA";
		String totalRepayment = "NA";
		
		try {
			principalLoanAmtRequested = loanAmount != null && !loanAmount.equals("") ? new BigDecimal(loanAmount) : BigDecimal.ZERO;
		} catch(NumberFormatException nfe) {
			principalLoanAmtRequested = BigDecimal.ZERO;
		}
		
		if(principalLoanAmtRequested.compareTo(BigDecimal.ZERO) != 0 && principalLoanAmtRequested.remainder(new BigDecimal(100)).compareTo(BigDecimal.ZERO) == 0) {
			
			// Sorting the lender details list based on lowest rate
			Collections.sort(lenderDetailsList);
			
			Map<String, LenderDetails> availableLendersMap = getAvailableLendersWithLowestRate(lenderDetailsList, principalLoanAmtRequested);
			
			if(availableLendersMap != null && !availableLendersMap.isEmpty()) {
							
				message = "Below are the quote details";
				
				//logger.info("number of availableLenders ::> " + availableLendersMap.size());
				
				BigDecimal annualIntRate = (calculateWeightedInterestRate(availableLendersMap, principalLoanAmtRequested).setScale(4, RoundingMode.HALF_UP).multiply(new BigDecimal(100))).setScale(1, RoundingMode.HALF_UP);
			    //logger.info("annualIntRate ::> " + annualIntRate);
			    
			    BigDecimal monthlyIntRate = annualIntRate.divide(new BigDecimal(100)).divide(new BigDecimal(MONTHS_IN_YEAR), MathContext.DECIMAL128);
			    
			    //logger.info("monthlyIntRate ::> " + monthlyIntRate);
			      
			    BigDecimal loanTenureInMonths = new BigDecimal(LOAN_TENURE_IN_YEARS).multiply(new BigDecimal(MONTHS_IN_YEAR));
			      
			    BigDecimal monthlyPayment = getMonthlyRepaymentAmount(principalLoanAmtRequested, monthlyIntRate, loanTenureInMonths);
			    
			    requestedAmount = "£" + principalLoanAmtRequested;
				annualInterestRate = annualIntRate + "%";
				monthlyRepayment = "£" + monthlyPayment;
				totalRepayment = "\u00a3" + monthlyPayment.multiply(loanTenureInMonths).setScale(2, RoundingMode.HALF_UP);
				
				logger.info("Requested Amount: " + requestedAmount);
				logger.info("Annual Interest Rate: " + annualInterestRate);
				logger.info("Monthly repayment: " + monthlyRepayment);
				logger.info("Total repayment: " + totalRepayment);
				
			}
			else {
				message = "It is not possible to provide a quote";
			}
			
		}
		else
		{
			message = "Requested Loan Amount is not valid. It should be in multiples of 100 and between 1000 and 15000";
		}
		
		return new QuoteDetails(message, requestedAmount, annualInterestRate, monthlyRepayment, totalRepayment);
	}
	
	private BigDecimal calculateWeightedInterestRate(Map<String, LenderDetails> availableLendersMap, BigDecimal originalReqLoanAmt) {
		
		BigDecimal weightedInterestAmountTotal = availableLendersMap.entrySet().stream().map(e -> new BigDecimal(e.getValue().getAvailableAmount()).multiply(new BigDecimal(e.getValue().getRate()))).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return weightedInterestAmountTotal.divide(new BigDecimal("" + originalReqLoanAmt), MathContext.DECIMAL128);
	}
	
	private Map<String, LenderDetails> getAvailableLendersWithLowestRate(List<LenderDetails> lenderDetailsList, BigDecimal originalReqLoanAmt) {
		
		Map<String, LenderDetails> availableLendersMap = new HashMap<String, LenderDetails>();
		
		BigDecimal remainingAmountForPrincipal = originalReqLoanAmt;
		
		for(LenderDetails lenderDtls : lenderDetailsList) {
			BigDecimal availableLenderAmount = new BigDecimal(lenderDtls.getAvailableAmount());
			
			if(remainingAmountForPrincipal.compareTo(BigDecimal.ZERO) > 0) {
				if(availableLenderAmount.compareTo(remainingAmountForPrincipal) <= 0) {
					remainingAmountForPrincipal = remainingAmountForPrincipal.subtract(availableLenderAmount);
					availableLendersMap.put(availableLenderAmount.toString()+":"+lenderDtls.getRate(), lenderDtls);
				}
				else {
					
					BigDecimal excessAmountToBeRemoved = availableLenderAmount.subtract(remainingAmountForPrincipal);
					
					long matchingKeyCount = availableLendersMap.keySet().stream()
			                .filter(key -> key.startsWith(excessAmountToBeRemoved.toString())).count(); 
					
					String keyToBeRemoved = matchingKeyCount > 0 ? 
												availableLendersMap.keySet().stream()
													.filter(key -> key.startsWith(excessAmountToBeRemoved.toString()))
													.collect(Collectors.toSet()).stream()
													.max((entry1,entry2) -> new BigDecimal(entry1.split(":")[1]).compareTo(new BigDecimal(entry2.split(":")[1])) > 0  ? 1 : -1).get().toString() 
											: "";
	                
					if(keyToBeRemoved != null && !keyToBeRemoved.isEmpty() && availableLendersMap.containsKey(keyToBeRemoved)) {
						availableLendersMap.remove(keyToBeRemoved);
						remainingAmountForPrincipal = remainingAmountForPrincipal.add(excessAmountToBeRemoved);
						availableLendersMap.put(availableLenderAmount.toString()+":"+lenderDtls.getRate(), lenderDtls);
						remainingAmountForPrincipal = remainingAmountForPrincipal.subtract(availableLenderAmount);
					}
				}
				
				if(remainingAmountForPrincipal.compareTo(BigDecimal.ZERO) == 0)
					break;
			}
		}
		
		return remainingAmountForPrincipal.compareTo(BigDecimal.ZERO) == 0 ? availableLendersMap : new HashMap<String, LenderDetails>();
		
	}
	
	private BigDecimal getMonthlyRepaymentAmount(BigDecimal principalLoanAmtRequested, BigDecimal monthlyIntRate, BigDecimal loanTenureInMonths) {
	
		// M = P [{r*(1+r)^n}/{(1+r)^n – 1}]
	    
		//BigDecimal upperPartExpo = (BigDecimal.ONE.add(monthlyIntRate)).pow(loanTenureInMonths.intValue());
	    BigDecimal dividend = principalLoanAmtRequested.multiply(monthlyIntRate.multiply((BigDecimal.ONE.add(monthlyIntRate)).pow(loanTenureInMonths.intValue())));
	    BigDecimal divisor = ((BigDecimal.ONE.add(monthlyIntRate)).pow(loanTenureInMonths.intValue())).subtract(BigDecimal.ONE);
	    BigDecimal monthlyPayment = dividend.divide(divisor, 2, RoundingMode.HALF_UP);
	    
	    return monthlyPayment;
	}
}
