package com.presta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.common.Configuration;
import com.selenium.common.ReadExcel;

public class HomePage {
     public ReadExcel read;
	
	public WebDriver driver = Configuration.browser();

	@FindBy(className = "login")
	public WebElement loginLink;

	@FindBy(className = "logo")
	public WebElement logoImage;

	@FindBy(className = "logout")
	public WebElement logoutLink;

	@FindBy(id = "search_query_top")
	public WebElement searchTextBox;

	@FindBy(name = "submit_search")
	public WebElement searchBtn;
	
	@FindBy(xpath = "//h3[@class='nbresult']//span")
	public WebElement totalResuts;
	
	
	
	@FindBy(xpath="//tbody//td[@class='cart_description']//a[text()='iPod shuffle']/../../..//td[@class='cart_total']//span")
    public WebElement pintable;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
		read=new ReadExcel();
	}

	public void loginLink() {
		loginLink.click();

	}

	public void navigateToHome() throws InterruptedException {
		Thread.sleep(3000);
		logoImage.click();
	}

	public void logout() {
		logoutLink.click();
		loginLink.click();
	}

	public void searchProduct(String ProdName, String prodResult, String price,String totalResults) {
		searchTextBox.clear();
		searchTextBox.sendKeys(ProdName);
		searchBtn.click();
		Assert.assertEquals(totalResuts.getText(), totalResults);
		boolean b1=driver.findElement(By.xpath("//a[text()='"+prodResult+"']")).isDisplayed();
		Assert.assertEquals(b1, true,"product is not found");
		
		boolean b2=driver.findElement(By.xpath("//a[text()='"+prodResult+"']/../../..//span[text()='"+price+"']")).isDisplayed();
		Assert.assertEquals(b2, true,"price doesn't match");
		
		

	}

	public void addtocart(String prodresult,String price) throws InterruptedException {
		
		driver.findElement(By.xpath("//a[text()='"+prodresult+"']/../../..//span[text()='"+price+"']/..//following-sibling::a[@title='Add to cart']")).click();
		//Assert.assertEquals(b3, true,"add to cart button doesn't exist");
		
	
		
	}
	
	
}
