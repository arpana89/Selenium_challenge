package com.makeMyTrip.qa.testCases;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.makeMyTrip.qa.Base.TestBase;
import com.makeMyTrip.qa.pages.HomePage;
import com.makeMyTrip.qa.util.TestUtil;


public class HomePageTestCase extends TestBase {
	private HomePage homePage;

	public HomePageTestCase() throws IOException {
		super();
		
	}
	@BeforeMethod
	public void setUp(){
		initialisation();
		homePage=new HomePage();
	}
	/*@Test(priority=1)
	public void testTitle(){
		System.out.println("driverhere::"+driver.getTitle());
		//driver.switchTo().frame(0);
		String title=homePage.newTitle();
		
		System.out.println(title);
		
	}*/
/*	@Test(priority = 1)
	public void ValidateHomePage() {
		homePage.ValidateTiltle();
	}*/
    @Test(priority=1)
    public void Search() throws InterruptedException{
    	
    	homePage.selectMenu("Flights");
    	homePage.TripWay("two");
    	Assert.assertTrue(homePage.EnterFromCity("Delhi"));
		Assert.assertTrue(homePage.EnterToCity("Bangalore"));
		// final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date date1 = new Date();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		c.setTime(c.getTime());
		date1=c.getTime();
		System.out.println("date1::"+date1);
		c.add(Calendar.DATE, 7);//adding 7 days to first date
		Date date2 = c.getTime();
		System.out.println("date1::"+date1);
		System.out.println("date2::"+date2);
		Assert.assertTrue(homePage.DatePicker(date1,date2));
    	homePage.search();
    }
   
    
}
