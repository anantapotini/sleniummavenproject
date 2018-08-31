package com.presta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.common.Configuration;

public class SubmitPage {
	
	public WebDriver driver=Configuration.browser();
	
	@FindBy(xpath="//div//h2[text()='Order summary']")
	public WebElement ordersummary;
	
	@FindBy(xpath="//h3[text()='Cheque payment']")
	public WebElement chequepayment;
	
	@FindBy(xpath="//a[text()='Other payment methods']")
	public WebElement otherpaymentmethods;
	
	@FindBy(xpath="//input[@name='submit']")
	public WebElement confirmorderbtn;
	

	@FindBy(xpath="//div//h1[text()='Order confirmation']")
	public WebElement orderconfirm;
	
	@FindBy(xpath="//a//span[text()='(empty)']")
	public WebElement cartempty;
	
	public SubmitPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void ordersummary() throws InterruptedException {
		boolean os=ordersummary.isDisplayed();
		Assert.assertEquals(os, true,"order summary text doesn't exist in the page");//validating order summary
		boolean cp=chequepayment.isDisplayed();
		Assert.assertEquals(cp, true,"Cheque payment text doesn't exist in the page");//validating cheque payment
		boolean pm=otherpaymentmethods.isDisplayed();
		if(pm==true) {
			boolean ot=otherpaymentmethods.isEnabled();
			Assert.assertEquals(ot, true,"other payment methods button is not enabled");//validating otherpyment methods button
			
		}
			
		boolean cm=confirmorderbtn.isDisplayed();
		if(cm==true) {
			boolean ob=confirmorderbtn.isEnabled();
			Assert.assertEquals(ob, true,"confirm order button is not enabled");//validating otherpyment methods button
			confirmorderbtn.click(); //clicking on confirm order button
			Thread.sleep(5000);
		}
	}
	   
	public void confirmorder() {
		boolean co=orderconfirm.isDisplayed();
		Assert.assertEquals(co, true,"order confirmation text doesn't exist"); //validating order confirmation text
		
		
		boolean ce=cartempty.isDisplayed();
		Assert.assertEquals(ce, true,"cart is not empty on top right corner of the page");
	}
	
}
