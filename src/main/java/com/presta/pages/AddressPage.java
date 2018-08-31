package com.presta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.common.Configuration;

public class AddressPage {
	public WebDriver driver= Configuration.browser();
	
	@FindBy(xpath="//p//label[contains(text(),'Use the delivery address as the billing address')]//preceding-sibling::input[@id='addressesAreEquals']")
    public WebElement checkbox;
	
	@FindBy(xpath="//input[@name='processAddress']")
	public WebElement nextbtn1;
	
	@FindBy(xpath="//a[text()='Add a new address']")
	public WebElement addnewaddress;
	
	public AddressPage(){
		PageFactory.initElements(driver, this);
				
	}
	
	public void cartddruncheck() throws InterruptedException {
		
		Thread.sleep(5000);
		boolean c=checkbox.isSelected();  //validating the checkbox is unchecked 
		if(c==true) {
			checkbox.click();
		}
		boolean ad=addnewaddress.isDisplayed();
		Assert.assertEquals(ad, true,"Add new address button doesn't exist");  //validating add new address button
		boolean nb=nextbtn1.isDisplayed();
		Assert.assertEquals(nb, true," next button doesn't exist in address page");  //validating next button
		
	}
	
public void cartddrcheckbox() throws InterruptedException {
		
		Thread.sleep(5000);
		boolean c=checkbox.isSelected();  //validating the checkbox is checked 
		if(c==false) {
			checkbox.click();
		}
		
		boolean nb=nextbtn1.isDisplayed();
		Assert.assertEquals(nb, true," next button doesn't exist in address page");  //validating next button
		if(nb==true) {
			nextbtn1.click();
			
		}
		
	}
	

}
