package stepDefinations;

import functions.bookingFunctions;
import io.cucumber.java.en.*;

public class booking_steps {
	
	bookingFunctions objbooking = new bookingFunctions() ;
	
	@Given("^User logged-in to Ryanair Application$")
	public void User_logged_in_to_Ryanair_Application() {
		objbooking.initializeBrowser();
		objbooking.login();	
	}
	
	
	@And("^User makes a booking from \"(.*)\" to \"(.*)\" for \"(.*)\" Adults and \"(.*)\" children$")
	public void User_enters_Booking_details_and_passenger_details(String strFrom, String strTo, int adultscount , int childrencount ) throws InterruptedException {
		objbooking.bookingHomepage(strFrom, strTo, adultscount, childrencount);
		objbooking.booking2ndpage();
		objbooking.enterPassengerDetails();
		objbooking.selectSeats();
		objbooking.cabinbagselection();
	}
	
	
	@When("User pay for booking with card details \"(.*)\" \"(.*)\" and \"(.*)\" ")
	public void User_pay_for_booking(int cardno, int expirydetails, int cvvdetails) {
		objbooking.checkoutPaymentpage();
		objbooking.paymentsdetailsenter(cardno,expirydetails,cvvdetails );
	}
	
	
	 @Then("User should get a payment decline message")
	 public void Validate_payment_decline_message() {
		 objbooking.validateDeclinepayment();
	 
	}
	
	
	
	
	
	
	
	
}

