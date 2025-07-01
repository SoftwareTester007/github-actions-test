package sos.baseConfig;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import sos.pageObjects.CheckoutPage;
import sos.pageObjects.LandingPage;
import utility.ReadPropertiesFile;

public class BaseClass {

	private Playwright playwright;
	private Page page;
	private Browser browser;
	private BrowserContext context;
	Dimension screenSize;
	public ReadPropertiesFile readProp;
	public LandingPage landingPage;
	public CheckoutPage checkoutPage;
	
	public BaseClass(String filePath) {

		readProp = new ReadPropertiesFile(filePath);
	}
	
	@BeforeTest
	@Parameters({"browserName","offerURL"})
	public void setUP(String browserName, String offerURL) {

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)screenSize.getHeight();
		int width = (int)screenSize.getWidth();
				
		
		String browserFromSystem = System.getProperty("browser");
		String browserFromPropertiesFile = readProp.getPropertyKey("browser");
		
		String browser_Name = browserFromSystem != null ? browserFromSystem : browserFromPropertiesFile != null ? browserFromPropertiesFile : browserName;
		
		
		switch(browser_Name.toLowerCase()) {

		case "chrome":
			startPlaywright(browserName, false, width, height);
			break;

		case "firefox":
			startPlaywright(browserName, false , width, height);
			break;

		default:
			System.out.println("Browser not getting from start plywright method...");	
			break;

		}
		
		page.navigate(offerURL);
	}

	public void startPlaywright(String browserName,boolean headless,int width,int height) {

		if(browserName.equalsIgnoreCase("chrome")) {

			playwright = Playwright.create();
			browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel(browserName).setHeadless(headless));
			context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width,height));
			page = context.newPage();
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) {

			playwright = Playwright.create();
			browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setChannel(browserName).setHeadless(headless));
			context = browser.newContext(new Browser.NewContextOptions().setViewportSize(width,height));
			page = context.newPage();
			
		}

		else {

			throw new RuntimeException("Not get Browser configuration");
		}
		
		
	}
	
	public Page getPage() {
		
		return page;
	}
}
