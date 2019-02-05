package com.lnkd.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lnkd.qa.util.ListenerHelper;
import com.lnkd.qa.util.TestUtil;

public class TestBase {
public static WebDriver driver;
public static WebDriverWait wait;
public static Properties prop;
public TestBase() throws IOException{
	try {
		//create object for Properties class
		prop=new Properties();
		//read the file to program
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\lnkd\\qa\\config\\config.properties");
		//load the properties
		prop.load(fis);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
}

public static void initialization() throws IOException{
	String browserName=prop.getProperty("browser");

	if (browserName.equalsIgnoreCase("firefox")) {
		System.setProperty("webdriver.gecko.driver","D:\\BrowserExeFiles\\geckodriver-v0.20.1-win64\\geckodriver.exe");
		// launch the firefox browser
		// interface refvar=new implementingclass();
		driver = new FirefoxDriver();
	} else if (browserName.equalsIgnoreCase("chrome")) {
		// set the chromedriver.exe path
		System.setProperty("webdriver.chrome.driver", "D:\\BrowserExeFiles\\chromedriver_win32\\chromedriver.exe");
		// interfacename refvar=new implementingclass();
		driver = new ChromeDriver();
	} else if (browserName.equalsIgnoreCase("internetExplorer")) {
		System.setProperty("webdriver.ie.driver","D:\\BrowserExeFiles\\IEDriverServer_Win32_3.11.1\\IEDriverServer.exe");

		// launch the browser
		driver = new InternetExplorerDriver();
	} else if (browserName.equalsIgnoreCase("edge")) {
		System.setProperty("webdriver.edge.driver", "D:\\BrowserExeFiles\\MicrosoftWebDriver.exe");

		// launch the Edge browser
		driver = new EdgeDriver();
	}
	// maximize the window
	driver.manage().window().maximize();
	// open the url
	driver.get(prop.getProperty("url"));

	// add implicitWait
	driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

	// delete al lthe cookies
	//driver.manage().deleteAllCookies();
  wait=new WebDriverWait(driver,TestUtil.EXPLICIT_WAIT);
// Create object for ListenerHelper inside TestBase class
		ListenerHelper listen = new ListenerHelper();
		//Create Object for EventFiringWebDriver it accepts the driver as parameter
		EventFiringWebDriver browser = new EventFiringWebDriver(driver);
		//Register the Listener onto EventFiringWebDriver object
		browser.register(listen);
		driver=browser;
}



}
