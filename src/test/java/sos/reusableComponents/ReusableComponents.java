package sos.reusableComponents;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

public class ReusableComponents {

	private static double checkoutPrice;
	private static double totalPrice=0;
	private static double individual_OTO_Price;
	private static double formattedPrice;


	public static void setDropdownValue(Page page, String elementLocator, String optionValue ) {

		page.locator(elementLocator).selectOption(new SelectOption().setLabel(optionValue.trim()));
	}

	public static void selectCrossSellsProducts(Page page, String elementLocator, int prodCount) {

		List<Locator> products = page.locator(elementLocator).all();
		for(int i=0; i<=prodCount; i++) {

			if(products.get(i).isChecked()) {

				continue;
			}
			products.get(i).click();
		}

	}

	public static void useStaticWait(long wait_ms) {
		try {
			Thread.sleep(wait_ms);
		}
		catch(Exception e) {

			System.out.println(e.getMessage());
		}
	}


	public static void get_checkoutPrice_UsingRegex(String extractPrice) {

		Pattern pattern = Pattern.compile("\\$(\\d+\\.\\d{2})");
		Matcher matcher = pattern.matcher(extractPrice);

		if(matcher.find()) {
			String price_String = matcher.group(1);
			checkoutPrice = Double.parseDouble(price_String);
			//totalPrice = totalPrice+individualPrice;
			//formattedPrice = Double.parseDouble(String.format("%.2f", totalPrice));
			
		}

	}
	
	public void extract_Each_OTO_Product_Price_using_regex(String extractPriceFromText) {
		
		Pattern pattern = Pattern.compile("@\\$(\\d+\\.\\d{2})");
		Matcher matcher = pattern.matcher(extractPriceFromText);

		if(matcher.find()) {
			String price_String = matcher.group(1);
			checkoutPrice = Double.parseDouble(price_String);
			totalPrice = totalPrice+individual_OTO_Price;
			formattedPrice = Double.parseDouble(String.format("%.2f", totalPrice));
			
		}
	}
	public static double getCheckoutPrice() {
		
		return checkoutPrice;
	}
}
