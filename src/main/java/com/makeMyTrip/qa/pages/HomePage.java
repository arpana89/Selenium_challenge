package com.makeMyTrip.qa.pages;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.makeMyTrip.qa.Base.TestBase;
import com.makeMyTrip.qa.util.TestUtil;



public class HomePage extends TestBase {
	 public static FlightResultsPage obj=null;
	@FindBy(xpath="//a[contains(@href,'flights') and contains(@class,'hrtlCenter')]")
	WebElement FlightsTab;
	@FindBy(xpath="//a[@class='mmtLogo makeFlex']")
	WebElement logoImage;
	@FindBy(xpath="//div[@class='widgetSection appendBottom40']//li[2]//span[@class='tabsCircle appendRight5']")
	WebElement roundTrip;
	@FindBy(xpath="//input[@id='fromCity']")
	WebElement fromCity;
    @FindBy(xpath="//label[@for='toCity']")
    WebElement toCity;
    @FindBy(xpath="//label[@for='departure']")
    WebElement departureDate;
    @FindBy(xpath="//label[@for='return']")
    WebElement returnDate;
 /*   @FindBy(className="fsw_inputBox flightTravllers inactiveWidget")
    WebElement travellerCount;*/
    @FindBy(xpath="//a[text()='Search']")
    WebElement searchButton;
    @FindBy(xpath="//input[@placeholder='From']")
	WebElement FromCityEnter;
		
	@FindBy(xpath="//input[(@type='text' )and contains(@class,'react-autosuggest__input react-autosuggest__input--open')]")
	WebElement ToCityEnter;
	@FindBy(xpath = "//ul[@class='makeFlex font12']/li")
	private List<WebElement> MMT_Menu = new ArrayList<WebElement>();
	@FindBy(xpath = "//*[@id='react-autowhatever-1']")
	private WebElement CitySuggestion;
	@FindBy(xpath = "//div[@class='react-autosuggest__section-container react-autosuggest__section-container--first']/ul/li")
	private List<WebElement> Suggestion = new ArrayList<WebElement>();
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
    
    public String newTitle(){
    	System.out.println("driver::"+driver.toString());
    	System.out.println(driver.getTitle());
    	return driver.getTitle();
    }
	public void ValidateTiltle() {
        Reporter.log("validating Page title...");
		Assert.assertEquals(driver.getTitle(),
				"MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights &amp; Holiday");
		//test.get().info("validated Page title...");
	}
	 public void selectMenu(String menu){
		  {
				for (WebElement e : MMT_Menu) {
					if (e.getText().contains(menu)) {
						e.click();
						Reporter.log("Clicked on Menu "+ menu);
						//test.get().info("Clicked on Menu "+ menu);
						break;
					}
				
			}
		  }
	 }
	 public void TripWay(String way) {
			if (way.equalsIgnoreCase("two")) {
				roundTrip.click();
				Reporter.log("Select on Stopway filter "+ way);
				//test.get().info("Select on Stopway filter "+ way);
			} /*else if (way.equalsIgnoreCase("one")) {
				one.click();
				Reporter.log("select on Stopway filter "+ way);
				test.get().info("Select on Stopway filter "+ way);
			} else {
				MutiCity.click();
				Reporter.log("select on Stopway filter "+ way);
				test.get().info("Select on Stopway filter "+ way);
			}*/
		}
    public FlightResultsPage search() throws InterruptedException{
    	searchButton.click();
    	Thread.sleep(2000);
    	return HomePage.obj= new FlightResultsPage();
    	
		
    	
    }
    public boolean EnterFromCity(String FromCityName) throws InterruptedException {

		boolean flag = false;
Thread.sleep(1000);
		fromCity.sendKeys(FromCityName, Keys.ENTER);
		Reporter.log("Enter Depature City "+ FromCityName);
		//test.get().info("Enter Depature City "+ FromCityName);
		System.out.println("FromCityName"+FromCityName);
		TestUtil.Explicitwait(10, CitySuggestion);
		Reporter.log("waiting to populate Auto Suggestion after entering City..");
		//test.get().info("waiting to populate Auto Suggestion after entering City..");

		for (WebElement e : Suggestion) {


			if (e.getText().toUpperCase().contains(FromCityName.toUpperCase())) {
				e.click();
				Reporter.log("selected city...");
				//test.get().info("selected city...");
				flag = true;
				break;
			}
		}

		return flag;

	}

	public boolean EnterToCity(String ToCityName) throws InterruptedException {

		boolean flag = false;
		toCity.sendKeys(Keys.TAB);
		Thread.sleep(6000);
		toCity.sendKeys(ToCityName, Keys.ENTER);
		System.out.println("ToCityName"+ToCityName);
		Reporter.log("Enter Arrival City "+ ToCityName);
		//test.get().info("Enter Arrival City "+ ToCityName);

		Thread.sleep(6000);
		Reporter.log("waiting to populate Auto Suggestion after entering City..");
	//	test.get().info("waiting to populate Auto Suggestion after entering City..");

		for (WebElement e : Suggestion) {

			if (e.getText().toUpperCase().contains(ToCityName.toUpperCase())) {
				e.click();
				Reporter.log("selected city...");
				//test.get().info("selected city...");
				flag = true;
				break;
			}
		}

		return flag;

	}
    public boolean DatePicker(Date DepartureDate, Date ArrivateDate) {


		if (DepartureDate.compareTo(ArrivateDate) > 0 || daysBetween(DepartureDate, ArrivateDate) > 7) {
			throw new SkipException(
					"This Method is restericated to accept 7 days difference between departure and Arrival date. Please check Given Date again. !!Contact Git Admin for more info");

		}

		boolean StatusFlag = false;

		SimpleDateFormat formatNowDay = new SimpleDateFormat("dd");
		SimpleDateFormat formatNowMonth = new SimpleDateFormat("MMMM");
		SimpleDateFormat formatNowYear = new SimpleDateFormat("YYYY");

		// Clicking on DepartureDate
		String Day1 = formatNowDay.format(DepartureDate);
		String Month1 = formatNowMonth.format(DepartureDate);
		String Year1 = formatNowYear.format(DepartureDate);
		if (Day1.startsWith("0")) {
			Day1 = Day1.substring(1);
		}
		try {
			String DepartureDateXpath = "//*[@class='DayPicker-Months']/div[*]/div/div[text()='" + Month1
					+ "']/span[text()='" + Year1 + "']//../../following-sibling::div[@class='DayPicker-Body']"
					+ "/div/div/div/p[text()='" + Day1 + "']/../..";

			
			departureDate.click();

			Reporter.log("Clicking on date " + Day1+"/"+Month1+"/"+Year1);
			WebElement DepDate = driver.findElement(By.xpath(DepartureDateXpath));
			if (DepDate.isDisplayed()) {
				//DepDate.click();
				TestUtil.JavaScriptClick(DepDate);
			
			}

			// Clicking on ArrivalDate
			String Day2 = formatNowDay.format(ArrivateDate);
			String Month2 = formatNowMonth.format(ArrivateDate);
			String Year2 = formatNowYear.format(ArrivateDate);

			if (Day2.startsWith("0")) {
				Day2 = Day2.substring(1);
			}

			String ArrivalDateXpath = "//*[@class='DayPicker-Months']/div[*]/div/div[text()='" + Month2
					+ "']/span[text()='" + Year2 + "']//../../following-sibling::div[@class='DayPicker-Body']"
					+ "/div/div/div/p[text()='" + Day2 + "']/../..";

			
			
			Reporter.log("Clicking on date " + Day2+"/"+Month2+"/"+Year2);
			WebElement ArrivalDate = driver.findElement(By.xpath(ArrivalDateXpath));
			
			if (ArrivalDate.isDisplayed()) {
				TestUtil.JavaScriptClick(ArrivalDate);
			}
			StatusFlag = true;

		} catch (Exception e) {
			Reporter.log(e.getMessage());
			e.printStackTrace();

		}

		return StatusFlag;

	}
    private static long daysBetween(Date one, Date two) {
		long difference = (one.getTime() - two.getTime()) / 86400000;
		return Math.abs(difference);
	}
}
