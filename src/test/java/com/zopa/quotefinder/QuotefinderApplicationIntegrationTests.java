package com.zopa.quotefinder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = QuotefinderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuotefinderApplicationIntegrationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void getSuccessfullLowestRateLenderQuoteForLoanAmount1000() throws JSONException, URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
	     
		final String baseUrl = createURLWithPort("/zopaRate/1000");
		URI uri = new URI(baseUrl);
		     
		HttpHeaders headers = new HttpHeaders();
		List mediaTypeLst = new ArrayList();
		mediaTypeLst.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypeLst);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String contentJson = "[{\"name\":\"Jane\",\"rate\":\"0.069\",\"availableAmount\":\"480\"},"
				+ "{\"name\":\"Fred\",\"rate\":\"0.071\",\"availableAmount\":\"520\"}]";
		 
		HttpEntity<String> requestEntity = new HttpEntity<>(contentJson, headers);
		System.out.println("Has Body ::> " + requestEntity.hasBody());
		System.out.println("Request Body ::> " + requestEntity.getBody());
		 
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		     
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		
		//Verify result body matches the expected output
		String expected = "{\"message\":\"Below are the quote details\",\"requestedAmt\":\"£1000\",\"annualIntRate\":\"7.0%\",\"monthlyRepaymentAmt\":\"£30.88\",\"totalRepaymentAmt\":\"£1111.68\"}";
		JSONAssert.assertEquals(expected, result.getBody(), false);
	}
	
	@Test
	public void getUnSuccessfullLowestRateLenderQuoteForNonNumericLoanAmountabcd() throws JSONException, URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
	     
		final String baseUrl = createURLWithPort("/zopaRate/abcd");
		URI uri = new URI(baseUrl);
		     
		HttpHeaders headers = new HttpHeaders();
		List mediaTypeLst = new ArrayList();
		mediaTypeLst.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypeLst);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String contentJson = "[{\"name\":\"Jane\",\"rate\":\"0.069\",\"availableAmount\":\"480\"},"
				+ "{\"name\":\"Fred\",\"rate\":\"0.071\",\"availableAmount\":\"520\"}]";
		 
		HttpEntity<String> requestEntity = new HttpEntity<>(contentJson, headers);
		System.out.println("Has Body ::> " + requestEntity.hasBody());
		System.out.println("Request Body ::> " + requestEntity.getBody());
		 
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		     
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		
		//Verify result body matches the expected output
		String expected = "{\"message\":\"Requested Loan Amount is not valid. It should be in multiples of 100 and between 1000 and 15000\",\"requestedAmt\":\"NA\",\"annualIntRate\":\"NA\",\"monthlyRepaymentAmt\":\"NA\",\"totalRepaymentAmt\":\"NA\"}";
		JSONAssert.assertEquals(expected, result.getBody(), false);
	}
	
	@Test
	public void getUnSuccessfullLowestRateLenderQuoteForLoanAmount1710() throws JSONException, URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
	     
		final String baseUrl = createURLWithPort("/zopaRate/1710");
		URI uri = new URI(baseUrl);
		     
		HttpHeaders headers = new HttpHeaders();
		List mediaTypeLst = new ArrayList();
		mediaTypeLst.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypeLst);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String contentJson = "[{\"name\":\"Jane\",\"rate\":\"0.069\",\"availableAmount\":\"480\"},"
				+ "{\"name\":\"Fred\",\"rate\":\"0.071\",\"availableAmount\":\"520\"}]";
		 
		HttpEntity<String> requestEntity = new HttpEntity<>(contentJson, headers);
		System.out.println("Has Body ::> " + requestEntity.hasBody());
		System.out.println("Request Body ::> " + requestEntity.getBody());
		 
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		     
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		
		//Verify result body matches the expected output
		String expected = "{\"message\":\"Requested Loan Amount is not valid. It should be in multiples of 100 and between 1000 and 15000\",\"requestedAmt\":\"NA\",\"annualIntRate\":\"NA\",\"monthlyRepaymentAmt\":\"NA\",\"totalRepaymentAmt\":\"NA\"}";
		JSONAssert.assertEquals(expected, result.getBody(), false);
	}
	
	@Test
	public void getUnSuccessfullLowestRateLenderQuoteForLoanAmount1700() throws JSONException, URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
	     
		final String baseUrl = createURLWithPort("/zopaRate/1700");
		URI uri = new URI(baseUrl);
		     
		HttpHeaders headers = new HttpHeaders();
		List mediaTypeLst = new ArrayList();
		mediaTypeLst.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypeLst);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String contentJson = "[{\"name\":\"Jane\",\"rate\":\"0.069\",\"availableAmount\":\"480\"},"
				+ "{\"name\":\"Fred\",\"rate\":\"0.071\",\"availableAmount\":\"520\"}]";
		 
		HttpEntity<String> requestEntity = new HttpEntity<>(contentJson, headers);
		System.out.println("Has Body ::> " + requestEntity.hasBody());
		System.out.println("Request Body ::> " + requestEntity.getBody());
		 
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		     
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		
		//Verify result body matches the expected output
		String expected = "{\"message\":\"It is not possible to provide a quote\",\"requestedAmt\":\"NA\",\"annualIntRate\":\"NA\",\"monthlyRepaymentAmt\":\"NA\",\"totalRepaymentAmt\":\"NA\"}";
		JSONAssert.assertEquals(expected, result.getBody(), false);
	}
	
	@Test
	public void getSuccessfullLowestRateLenderQuoteForLoanAmount1700() throws JSONException, URISyntaxException {

		RestTemplate restTemplate = new RestTemplate();
	     
		final String baseUrl = createURLWithPort("/zopaRate/1700");
		URI uri = new URI(baseUrl);
		     
		HttpHeaders headers = new HttpHeaders();
		List mediaTypeLst = new ArrayList();
		mediaTypeLst.add(MediaType.APPLICATION_JSON);
		headers.setAccept(mediaTypeLst);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String contentJson = "[{\"name\":\"Bob\",\"rate\":\"0.075\",\"availableAmount\":\"640\"},"
				+ "{\"name\":\"Jane\",\"rate\":\"0.069\",\"availableAmount\":\"480\"},"
				+ "{\"name\":\"Fred\",\"rate\":\"0.071\",\"availableAmount\":\"520\"},"
				+ "{\"name\":\"Mary\",\"rate\":\"0.104\",\"availableAmount\":\"170\"},"
				+ "{\"name\":\"John\",\"rate\":\"0.081\",\"availableAmount\":\"320\"},"
				+ "{\"name\":\"Dave\",\"rate\":\"0.074\",\"availableAmount\":\"140\"},"
				+ "{\"name\":\"Angela\",\"rate\":\"0.071\",\"availableAmount\":\"60\"}]";
		 
		HttpEntity<String> requestEntity = new HttpEntity<>(contentJson, headers);
		System.out.println("Has Body ::> " + requestEntity.hasBody());
		System.out.println("Request Body ::> " + requestEntity.getBody());
		 
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
		     
		//Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		
		//Verify result body matches the expected output
		String expected = "{\"message\":\"Below are the quote details\",\"requestedAmt\":\"£1700\",\"annualIntRate\":\"7.2%\",\"monthlyRepaymentAmt\":\"£52.65\",\"totalRepaymentAmt\":\"£1895.40\"}";
		JSONAssert.assertEquals(expected, result.getBody(), false);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
