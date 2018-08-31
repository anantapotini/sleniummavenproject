package com.presta.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.common.Configuration;
import com.selenium.common.ReadExcel;

public class SummaryPage {

	public ReadExcel read;
	public WebDriver driver= Configuration.browser();
	public HomePage home;
	
	public SummaryPage() {
		PageFactory.initElements(driver, this);
		read=new ReadExcel();
		home=new HomePage();
	}
	
	@FindBy(xpath="//a[@title='Next']")
	public WebElement nextbtn;
	
	@FindBy(xpath="//input[@name='processAddress']")
	public WebElement nextbtn1;
	
	@FindBy(xpath="//a[@title='Continue shopping']")
	public WebElement cntshoppingbtn;
	
	@FindBy(xpath="//div//h1[text()='Shopping cart summary']")
	public WebElement shopping_summary_text;
	
	@FindBy(xpath="//a[@title='View my shopping cart']")
	public WebElement cartlink;
	
	@FindBy(xpath="//div//p[text()='Your shopping cart is empty.']")
	public WebElement shoppingcart_emptytext;
	
	@FindBy(xpath="//a//span[@class='ajax_cart_no_product' and text()='(empty)']")
	public WebElement cart_empty;
	
	@FindBy(xpath="//p//label[contains(text(),'Use the delivery address as the billing address')]//preceding-sibling::input[@id='addressesAreEquals']")
     public WebElement checkbox;
	
	public void sumpage(String prodresult,String price) throws InterruptedException {
		cartlink.click();
		driver.navigate().refresh();
		boolean st=shopping_summary_text.isDisplayed();//shopping cart summary validation
		Assert.assertEquals(st, true,"landed in wrong page of cart summary");
		WebDriverWait wait = new WebDriverWait(driver,10);
		boolean nx=nextbtn.isDisplayed();
		Assert.assertEquals(nx, true,"Next button doesn't exist");//next button validation
		boolean cs=cntshoppingbtn.isDisplayed();
		Assert.assertEquals(cs, true,"Continue shopping button doesn't exist");//continue shopping button validation
		boolean ps=driver.findElement(By.xpath("//tbody//td[@class='cart_description']//a[text()='"+prodresult+"']")).isDisplayed(); //ipod shuffle validation
		Assert.assertEquals(ps, true, "Product"+prodresult+"is not available");
		String pr=driver.findElement(By.xpath("//tbody//td[@class='cart_description']//a[text()='"+prodresult+"']/../../..//td[@class='cart_total']//span")).getText();//price validation
		if(pr==read.readData("price")) {
			boolean b3= driver.findElement(By.xpath("//tbody//td[@class='cart_description']//a[text()='"+prodresult+"']/../../..//td[@class='cart_delete']//a[text()='Delete']")).isDisplayed();//delete button validation
			Assert.assertEquals(b3, true,"Delete button doesn't exist");
				
	}
	}
		public void deleteitemsincart(String prodresult) throws InterruptedException {
			cartlink.click();
			driver.navigate().refresh();
			driver.findElement(By.xpath("//tbody//td[@class='cart_description']//a[text()='"+prodresult+"']/../../..//td[@class='cart_delete']//a[text()='Delete']")).click();//clicked on delete button
			Thread.sleep(3000);
			boolean ce=shoppingcart_emptytext.isDisplayed(); //validating shopping cart empty text
			Assert.assertEquals(ce, true,"shopping cart is not empty");
			boolean ec=cart_empty.isDisplayed();
			Assert.assertEquals(ec, true,"cart is not empty");//validating cart empty on top right corner of the page
			home.navigateToHome();
			boolean h=home.logoutLink.isDisplayed();
			Assert.assertEquals(h, true,"logout doesn't exist");			
		}

	public void nextinsummarypage() {
		nextbtn.click();
	}
	
	
	
	
}



