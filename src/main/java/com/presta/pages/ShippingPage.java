package com.presta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.common.Configuration;

public class ShippingPage {
	
	public WebDriver driver=Configuration.browser();
	@FindBy(xpath="//div[@id='carrier_area']//h1[text()='Shipping']")
	public WebElement ship;
	
	@FindBy(xpath="//label[contains(text(),'I agree to the Terms of Service')]//preceding-sibling::input[@type='checkbox']")
	public WebElement termscheckbox;
	
	@FindBy(xpath="//input[@name='processCarrier']")
	public WebElement nextbtn2;
	
	public ShippingPage() {
		PageFactory.initElements(driver, this);
	}

	public void validateshippingpage() {
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver, 30);  
	      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='carrier_area']//h1[text()='Shipping']"))); 
		boolean sh=ship.isDisplayed();
		Assert.assertEquals(sh, true,"not in shipping page");  //validating shipping text
		boolean tc=termscheckbox.isSelected();
		Assert.assertEquals(tc, false,"Terms checkbox is selected "); //validating terms checkbox
	}
	
	public void shippingpage() {
		boolean tc=termscheckbox.isSelected();
		Assert.assertEquals(tc, false,"Terms checkbox is selected "); //validating terms checkbox
		if(tc==false) {
			termscheckbox.click();
		}
		nextbtn2.click();
		
	}
	
}
