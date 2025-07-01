package sos.pageObjects;


import com.microsoft.playwright.Page;

import sos.reusableComponents.ReusableComponents;

public class CheckoutPage {

	private Page page;
	public CheckoutPage(Page page) {
		
		this.page = page;
	}
	
	String shipping_FirstName = "#mm_field_shipping_first_name_h8j09";
	String shipping_LastName = "#mm_field_shipping_last_name_h8j09";
	String emailAddress = "#mm_field_shipping_email_h8j09";
	String shippingPhone = "#mm_field_shipping_phone_h8j09";
	String shippingAddress = "#mm_field_shipping_address_h8j09";
	
	public String isShippingSameAsBilling = "#mm_checkbox_billing_equals_shipping";
	
	String billingFirstName = "#mm_field_first_name_h8j09";
	String billingLastName = "#mm_field_last_name_h8j09";
	String billingAddress = "#mm_field_billing_address_h8j09";
	
	String cardNumber = "#mm_field_cc_number_h8j09";
	String securityCode = "#mm_field_cc_cvv_h8j09";
	String cardExpMonth = "#mm_field_cc_exp_month_h8j09";
	String cardExpYear = "#mm_field_cc_exp_year_h8j09";
	String crossSell_Products = "//div[@class='col-md-12 cross_product']//label//input[@type='checkbox']";
	String checkoutPrice = "//span[@id='mm_label_total_price']";
	
	
	public CheckoutPage enterShippingInfo(String firstName, String lastName, String emailId, String phoneNumber, String shipAddrres) {
		
		page.locator(shipping_FirstName).fill(firstName);
		page.locator(shipping_LastName).fill(lastName);
		page.locator(emailAddress).fill(emailId);
		page.locator(shippingPhone).fill(phoneNumber);
		page.locator(shippingAddress).fill(shipAddrres);
		ReusableComponents.useStaticWait(4000);
		page.keyboard().press("ArrowDown");
		page.keyboard().press("Enter");
		return this;
		
	}
	
	public CheckoutPage enterBillingInfo(String billFirstName, String billLastName, String billAddress) {
		
		page.locator(billingFirstName).fill(billFirstName);
		page.locator(billingLastName).fill(billLastName);
		page.locator(billingAddress).fill(billAddress);
		ReusableComponents.useStaticWait(4000);
		page.keyboard().press("ArrowDown");
		page.keyboard().press("Enter");
		return this;
	}
	
	public CheckoutPage enterCardInfo(String card_Number, String cvv, String card_Exp_Month, String card_Exp_Year) {
		
		page.locator(cardNumber).fill(card_Number);
		page.locator(securityCode).fill(cvv);
		ReusableComponents.useStaticWait(2000);
		ReusableComponents.setDropdownValue(page, cardExpMonth, card_Exp_Month);
		ReusableComponents.useStaticWait(2000);
		ReusableComponents.setDropdownValue(page, cardExpYear, card_Exp_Year);
		return this;
	}
	
	public CheckoutPage selectCrosssSellsProducts(int productCount) {
		
		
		ReusableComponents.selectCrossSellsProducts(page, crossSell_Products, productCount);
		ReusableComponents.useStaticWait(2000);
		ReusableComponents.get_checkoutPrice_UsingRegex(page.locator(checkoutPrice).textContent());
		return this;
	}
}
