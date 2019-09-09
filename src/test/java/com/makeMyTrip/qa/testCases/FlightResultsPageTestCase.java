package com.makeMyTrip.qa.testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.makeMyTrip.qa.Base.TestBase;
import com.makeMyTrip.qa.pages.FlightResultsPage;
import com.makeMyTrip.qa.pages.HomePage;
import com.makeMyTrip.qa.testData.DataProvider;
import com.makeMyTrip.qa.util.TestUtil;


public class FlightResultsPageTestCase extends TestBase{

	private HomePage homepage;
	 
	 public static FlightResultsPage flightresults=null;
	
	@BeforeMethod
	public void Setup() throws InterruptedException{
		homepage=new HomePage();
		//flightresults=homepage.search();
	}
	@Test(priority=1)
	public void numberofFlights(){
		flightresults=homepage.obj;
		TestUtil.ScrollDownComplete();
		flightresults.numberofDepartingFlights();
		flightresults.numberofArrivalFlights();
	}
	 @Test(priority=2)
		public void ValidateNumberOfFlightWith_NonStop() {
			System.out.println("***********No of flights after NonStop filter applied****************");
			Reporter.log("***********No of flights after NonStop filter applied***************");
			TestUtil.ScrollUPComplete();
			flightresults.StopsFlights("non stops");
			TestUtil.ScrollDownComplete();
	     	flightresults.numberofDepartingFlights();
			flightresults.numberofArrivalFlights();
			
		}
		
		@Test(priority=3)
		public void ValidateNumberOfFlightWith_OneStop() {
			System.out.println("*********** No of flights after One STOP filter applied***************");
			Reporter.log("***********No of flights after One STOP filter applied****************");
			TestUtil.ScrollUPComplete();
			flightresults.StopsFlights("one stop");
			TestUtil.ScrollDownComplete();
			flightresults.numberofDepartingFlights();
			flightresults.numberofArrivalFlights();
			TestUtil.ScrollUPComplete();
			flightresults.ClearStopFilter();
			TestUtil.ScrollDownComplete();
			TestUtil.ScrollUPComplete();
		}
		
		@Test(priority=4)
		public void SelectFlightAndValidateTotal() {
			
			flightresults.SelectDepartureFlight(2);
			flightresults.SelectDArrivalFlight(3);
			//Console printing
			System.out.println("Selected Arrival flight Name: "+flightresults.getArrival_Flightname());
			System.out.println("Selected Arrival flight Price: "+flightresults.getArrival_FlightPrice());
			System.out.println("Selected Departure flight Name: "+flightresults.getDepart_Flightname());
			System.out.println("Selected departure flight Price: "+flightresults.getDepart_FlightPrice());
			
			//TestNg Emailable printing
			Reporter.log("Selected Arrival flight Name: "+flightresults.getArrival_Flightname());
			Reporter.log("Selected Arrival flight Name: "+flightresults.getArrival_FlightPrice());
			Reporter.log("Selected Arrival flight Name: "+flightresults.getDepart_Flightname());
			Reporter.log("Selected Arrival flight Name: "+flightresults.getDepart_FlightPrice());
			
			//Extent Reporting printing
			/*test.get().info("Selected Arrival flight Name: "+flightresults.getArrival_Flightname());
			test.get().info("Selected Arrival flight Name: "+flightresults.getArrival_FlightPrice());
			test.get().info("Selected Arrival flight Name: "+flightresults.getDepart_Flightname());
			test.get().info("Selected Arrival flight Name: "+flightresults.getDepart_FlightPrice());*/
			
			

			Validate_SelectedFlightDetails();
			ValidateTotalFare();
			
		}
		
		
		private void Validate_SelectedFlightDetails() {
			Assert.assertTrue(flightresults.ValidateSelectet_DepartureFlightDetails());
			Assert.assertTrue(flightresults.ValidateSelectet_DepartureFlightDetails());

		}

		
		private void ValidateTotalFare() {
			Assert.assertTrue(flightresults.validate_TotalFare());
		}
	 @AfterMethod
	 public void tearDown(){
		// driver.close();
	 }
}
