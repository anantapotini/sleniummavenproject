package com.presta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.common.Configuration;
import com.selenium.common.ReadExcel;

public class PaymentPage {
	
	@FindBy(xpath="//a[@title='Pay by cheque']")
	public WebElement paybychecklink;
	
	@FindBy(xpath="//button[text()='Submit Payment']")
	public WebElement submitbtn;
	
	public WebDriver driver=Configuration.browser();
	public ReadExcel read;
	
	@FindBy(xpath="//a[@title='Pay with cash on delivery (COD)']")
	public WebElement deliverylink;
	
	public PaymentPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void paymentpage(String prodresult,String price) {
		
		boolean ds=driver.findElement(By.xpath("//table//tbody//td[@class='cart_description']//a[text()='"+prodresult+"']")).isDisplayed(); //description validation
		Assert.assertEquals(ds, true,"Decription of the product is not matching");
		String description=driver.findElement(By.xpath("//table//tbody//td[@class='cart_description']//a")).getText();
		System.out.println("Description of the selected product :"+description);
		String unitprice=driver.findElement(By.xpath("//table//tbody//td[@class='cart_description']//a[text()='"+prodresult+"']/../..//following-sibling::td[@class='cart_unit']//span")).getText();
		System.out.println("price of the selected product :"+unitprice);
		Assert.assertEquals(unitprice, price,"price is not matching");   //price validation
		String checklink=paybychecklink.getText();
		String actual="Pay by cheque (order process will be longer)";
		
		Assert.assertEquals(checklink, actual.toUpperCase(),"pay by cheque link doesn't exist");//pay by cheque link validation
		boolean dl=deliverylink.isDisplayed();
		Assert.assertEquals(dl, true,"Pay with cash delivery link doesn't exist");//validating pay with acsh on delivery link
		
		boolean sb=submitbtn.isEnabled();
		
		Assert.assertEquals(sb, true,"payment submit button doesn't exist");//payment submit button
		
		
			}
	
	public void chequelink() {
		
		paybychecklink.click();
		
	}
	

}
