package sos.pageObjects;

import com.microsoft.playwright.Page;

public class LandingPage {

	private Page page;
	public LandingPage(Page page) {
		this.page = page;
	}
	
	String freeGiftButton = "(//div[@class='elementor-button-wrapper']//a)[1]";
	

	public CheckoutPage click_FreeGift_Button(Page page) {
		
		page.locator(freeGiftButton).click();
		return new CheckoutPage(page);
	}
}
