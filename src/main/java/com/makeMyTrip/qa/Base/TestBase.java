package com.makeMyTrip.qa.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;



public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase() {
		prop=new Properties();
		FileInputStream io;
		try {
			io = new FileInputStream("F:/Eclipse_Workspace/makeMyTrip/src/main/java/com/makeMyTrip/qa/Config/config.properties");
			try {
				prop.load(io);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static void initialisation(){
		String Browser=prop.getProperty("browser");
		if (Browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\PC\\Downloads\\chromedriver_win32\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
			
		}
		
		String url=prop.getProperty("URL");
		System.out.println("url::" + url);
		driver.get(url);	


		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
}
