package sos.testClass;

import java.io.File;

import org.testng.annotations.Test;

import sos.baseConfig.BaseClass;
import sos.pageObjects.LandingPage;
import sos.reusableComponents.ReusableComponents;

public class Test_Free_SteelRriver_Midnight_Stealth_Stiletto_Baton extends BaseClass{

	
	public Test_Free_SteelRriver_Midnight_Stealth_Stiletto_Baton() {
		
		super("resource"+File.separator+"input.properties");
		landingPage = new LandingPage(getPage());
	}
	
	
	@Test(priority = 1)
	public void click_Claim_My_Free_Gift_Button() {
		
		checkoutPage = landingPage.click_FreeGift_Button(getPage());
	}
	
	@Test(priority = 2)
	public void enterShippingDetails() {
		if(readProp.getPropertyKey("Is_Shipping_SameAs_Billing").equalsIgnoreCase("yes")) {
			
		
		checkoutPage.enterShippingInfo(
				readProp.getPropertyKey("Ship_FirstName"),
				readProp.getPropertyKey("Ship_LastName"), 
				readProp.getPropertyKey("Ship_Email"), 
				readProp.getPropertyKey("Ship_Phone"), 
				readProp.getPropertyKey("Ship_Address")
				);
		}
		else if(readProp.getPropertyKey("Is_Shipping_SameAs_Billing").equalsIgnoreCase("no")) {
			
			checkoutPage.enterShippingInfo(
					readProp.getPropertyKey("Ship_FirstName"),
					readProp.getPropertyKey("Ship_LastName"), 
					readProp.getPropertyKey("Ship_Email"), 
					readProp.getPropertyKey("Ship_Phone"), 
					readProp.getPropertyKey("Ship_Address"));
					
			
			getPage().locator(checkoutPage.isShippingSameAsBilling).uncheck();
			
			checkoutPage.enterBillingInfo(
					readProp.getPropertyKey("Bill_FirstName"), 
					readProp.getPropertyKey("Bill_LastName"), 
					readProp.getPropertyKey("Bill_Address"));
		}
		else {
			
			System.out.println("Unable to enter Shipping & Billing Address");
		}
		
	}
	
	@Test(priority = 3)
	public void enterCardDetailsForPayment() {
		
		checkoutPage.enterCardInfo(
				readProp.getPropertyKey("card_Number"), 
				readProp.getPropertyKey("security_code"), 
				readProp.getPropertyKey("exp_month"), 
				readProp.getPropertyKey("exp_year"));
	}
	
	@Test(priority = 4)
	public void selectCrossSellsProducts() {
		
		checkoutPage.selectCrosssSellsProducts(Integer.parseInt(readProp.getPropertyKey("crosssellProducts")));
		System.out.println("Total Price :" +ReusableComponents.getCheckoutPrice());
	}
}
