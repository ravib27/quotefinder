package com.zopa.quotefinder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zopa.quotefinder.controller.QuotefinderController;
import com.zopa.quotefinder.service.QuotefinderService;
import com.zopa.quotefinder.valueobject.QuoteDetails;

@RunWith(SpringRunner.class)
@WebMvcTest(value = QuotefinderController.class)
public class QuotefinderApplicationUnitTests {

	Logger logger = LoggerFactory.getLogger(QuotefinderApplicationUnitTests.class);
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private QuotefinderService quotefinderService;
	
	@Test
	public void getSuccessfullLowestRateLenderQuoteForLoanAmount1000() throws Exception {
	
		QuoteDetails mockQuoteDetails = new QuoteDetails("Below are the quote details", "£1000", "7.0%", "£30.88", "£1111.68");
		
		String contentJson = "[{\"name\":\"Jane\",\"rate\":\"0.069\",\"availableAmount\":\"480\"},"
				+ "{\"name\":\"Fred\",\"rate\":\"0.071\",\"availableAmount\":\"520\"}]";

		Mockito.when(
				quotefinderService.getLowestRateLenderQuote(Mockito.anyString(), Mockito.anyList())).thenReturn(mockQuoteDetails);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/zopaRate/1000")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(contentJson)
				.contentType(MediaType.APPLICATION_JSON);
				

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);

		logger.info(result.getResponse().getContentLength()+"");
		String expected = "{\"message\":\"Below are the quote details\",\"requestedAmt\":\"£1000\",\"annualIntRate\":\"7.0%\",\"monthlyRepaymentAmt\":\"£30.88\",\"totalRepaymentAmt\":\"£1111.68\"}";
		logger.info("result.getResponse() ::> " + result.getResponse().getContentAsString() + " // Context Over");
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void getUnSuccessfullLowestRateLenderQuoteForNonNumericLoanAmountabcd() throws Exception {
	
		QuoteDetails mockQuoteDetails = new QuoteDetails("Requested Loan Amount is not valid. It should be in multiples of 100 and between 1000 and 15000", "NA", "NA", "NA", "NA");
		
		String contentJson = "[{\"name\":\"Jane\",\"rate\":\"0.069\",\"availableAmount\":\"480\"},"
				+ "{\"name\":\"Fred\",\"rate\":\"0.071\",\"availableAmount\":\"520\"}]";

		Mockito.when(
				quotefinderService.getLowestRateLenderQuote(Mockito.anyString(), Mockito.anyList())).thenReturn(mockQuoteDetails);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/zopaRate/abcd")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(contentJson)
				.contentType(MediaType.APPLICATION_JSON);
				

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);

		logger.info(result.getResponse().getContentLength()+"");
		String expected = "{\"message\":\"Requested Loan Amount is not valid. It should be in multiples of 100 and between 1000 and 15000\",\"requestedAmt\":\"NA\",\"annualIntRate\":\"NA\",\"monthlyRepaymentAmt\":\"NA\",\"totalRepaymentAmt\":\"NA\"}";
		logger.info("result.getResponse() ::> " + result.getResponse().getContentAsString() + " // Context Over");
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void getUnSuccessfullLowestRateLenderQuoteForLoanAmount1710() throws Exception {
	
		QuoteDetails mockQuoteDetails = new QuoteDetails("Requested Loan Amount is not valid. It should be in multiples of 100 and between 1000 and 15000", "NA", "NA", "NA", "NA");
		
		String contentJson = "[{\"name\":\"Jane\",\"rate\":\"0.069\",\"availableAmount\":\"480\"},"
				+ "{\"name\":\"Fred\",\"rate\":\"0.071\",\"availableAmount\":\"520\"}]";

		Mockito.when(
				quotefinderService.getLowestRateLenderQuote(Mockito.anyString(), Mockito.anyList())).thenReturn(mockQuoteDetails);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/zopaRate/1710")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(contentJson)
				.contentType(MediaType.APPLICATION_JSON);
				

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);

		logger.info(result.getResponse().getContentLength()+"");
		String expected = "{\"message\":\"Requested Loan Amount is not valid. It should be in multiples of 100 and between 1000 and 15000\",\"requestedAmt\":\"NA\",\"annualIntRate\":\"NA\",\"monthlyRepaymentAmt\":\"NA\",\"totalRepaymentAmt\":\"NA\"}";
		logger.info("result.getResponse() ::> " + result.getResponse().getContentAsString() + " // Context Over");
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void getSuccessfullLowestRateLenderQuoteForLoanAmount1700() throws Exception {
		
		QuoteDetails mockQuoteDetails = new QuoteDetails("Below are the quote details", "£1700", "7.2%", "£52.65", "£1895.40");
		
		String contentJson = "[{\"name\":\"Bob\",\"rate\":\"0.075\",\"availableAmount\":\"640\"},"
				+ "{\"name\":\"Jane\",\"rate\":\"0.069\",\"availableAmount\":\"480\"},"
				+ "{\"name\":\"Fred\",\"rate\":\"0.071\",\"availableAmount\":\"520\"},"
				+ "{\"name\":\"Mary\",\"rate\":\"0.104\",\"availableAmount\":\"170\"},"
				+ "{\"name\":\"John\",\"rate\":\"0.081\",\"availableAmount\":\"320\"},"
				+ "{\"name\":\"Dave\",\"rate\":\"0.074\",\"availableAmount\":\"140\"},"
				+ "{\"name\":\"Angela\",\"rate\":\"0.071\",\"availableAmount\":\"60\"}]";

		Mockito.when(
				quotefinderService.getLowestRateLenderQuote(Mockito.anyString(), Mockito.anyList())).thenReturn(mockQuoteDetails);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/zopaRate/1700")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(contentJson)
				.contentType(MediaType.APPLICATION_JSON);
				

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);

		logger.info(result.getResponse().getContentLength()+"");
		String expected = "{\"message\":\"Below are the quote details\",\"requestedAmt\":\"£1700\",\"annualIntRate\":\"7.2%\",\"monthlyRepaymentAmt\":\"£52.65\",\"totalRepaymentAmt\":\"£1895.40\"}";
		logger.info("result.getResponse() ::> " + result.getResponse().getContentAsString() + " // Context Over");
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	
	@Test
	public void getUnSuccessfullLowestRateLenderQuoteForLoanAmount1700() throws Exception {
	
		QuoteDetails mockQuoteDetails = new QuoteDetails("It is not possible to provide a quote", "NA", "NA", "NA", "NA");
		
		String contentJson = "[{\"name\":\"Jane\",\"rate\":\"0.069\",\"availableAmount\":\"480\"},"
				+ "{\"name\":\"Fred\",\"rate\":\"0.071\",\"availableAmount\":\"520\"}]";

		Mockito.when(
				quotefinderService.getLowestRateLenderQuote(Mockito.anyString(), Mockito.anyList())).thenReturn(mockQuoteDetails);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/zopaRate/1700")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(contentJson)
				.contentType(MediaType.APPLICATION_JSON);
				

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		int status = result.getResponse().getStatus();
		assertEquals(200, status);

		logger.info(result.getResponse().getContentLength()+"");
		String expected = "{\"message\":\"It is not possible to provide a quote\",\"requestedAmt\":\"NA\",\"annualIntRate\":\"NA\",\"monthlyRepaymentAmt\":\"NA\",\"totalRepaymentAmt\":\"NA\"}";
		logger.info("result.getResponse() ::> " + result.getResponse().getContentAsString() + " // Context Over");
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
