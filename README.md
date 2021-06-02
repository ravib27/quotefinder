# QuoteFinder

It has been developed as a Spring Boot Rest Service to find a quote from market of lenders for 36-month loans that apply interest on a monthly basis.

It is a Spring Boot - Maven project which could be downloaded from the following github url : https://github.com/ravib27/quotefinder/tree/master and ran in local environment as a Spring Boot App.

Once the application is up and running, following endpoint should be used from Postman or Insomina client to find the loan quote : http://localhost:9090/zopaRate/{loanAmount}

It makes a post request with request body where the input lender details shall be provided as json content like below:

[{"name":"Jane","rate":"0.069","availableAmount":"480"},{"name":"Fred","rate":"0.071","availableAmount":"520"}]

The response would be available as below json content:

{
  "message": "Below are the quote details",
  "requestedAmt": "£1000",
  "annualIntRate": "7.0%",
  "monthlyRepaymentAmt": "£30.88",
  "totalRepaymentAmt": "£1111.68"
}
