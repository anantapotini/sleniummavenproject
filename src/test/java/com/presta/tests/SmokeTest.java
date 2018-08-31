package com.presta.tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.presta.pages.AddressPage;
import com.presta.pages.HomePage;
import com.presta.pages.LoginPage;
import com.presta.pages.PaymentPage;
import com.presta.pages.ShippingPage;
import com.presta.pages.SubmitPage;
import com.presta.pages.SummaryPage;
import com.selenium.common.Configuration;
import com.selenium.common.ReadExcel;

public class SmokeTest {

	public WebDriver driver = Configuration.browser();
	public ReadExcel read;
	public HomePage home;
	public LoginPage login;
	public SummaryPage Summary;
	public AddressPage address;
	public ShippingPage shipping;
	public PaymentPage payment;
	public SubmitPage submit;

	public SmokeTest() {
		read = new ReadExcel();
		home = new HomePage();
		login = new LoginPage();
		Summary = new SummaryPage();
		address = new AddressPage();
		shipping = new ShippingPage();
		payment=new PaymentPage();
		submit=new SubmitPage();

	}

	@BeforeSuite(alwaysRun = true)
	public void loginToAPP() {

		driver.get(Configuration.URL);
		driver.manage().window().maximize();
		home.loginLink();
		Assert.assertEquals(driver.getTitle(), read.readData("Login_Title"));
		login.loginData(Configuration.username, Configuration.password);
		Assert.assertEquals(driver.getTitle(), read.readData("MyAccount_Title"));

	}

	@AfterSuite(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}

	@AfterMethod(alwaysRun = true)
	public void navigateToHome() throws InterruptedException {
		home.navigateToHome();
	}

	@Test(priority = 1, testName = "login_Positive", description = "login_Positive", timeOut = 190000, enabled = true, groups = {
			"sanity", "1" })
	public void alogin_Positive() {
		Assert.assertTrue(true);

	}

	@Test(priority = 2, testName = "login_negative", description = "login_negative", timeOut = 190000, enabled = true, groups = {
			"sanity", "2" })
	public void loginNegative() {
		home.logout();
		login.loginData("aa", "aa");
		login.validateErrorMessage();
		login.loginData(Configuration.username, Configuration.password);
		Assert.assertEquals(driver.getTitle(), read.readData("MyAccount_Title"));

	}

	@Test(priority = 3, testName = "searchProduct", description = "searchProduct", timeOut = 190000, enabled = true, groups = {
			"sanity", "3" })
	public void searchProduct() {
		home.searchProduct(read.readData("Prodname"), read.readData("prodresult"), read.readData("price"),
				read.readData("total results"));

	}

	@Test(priority = 4, testName = "Addtocart", description = "Addtocart", timeOut = 190000, enabled = true, groups = {
			"sanity", "4" })
	public void Addtocart() throws InterruptedException {
		home.searchProduct(read.readData("Prodname"), read.readData("prodresult"), read.readData("price"),
				read.readData("total results"));
		Thread.sleep(3000);
		home.addtocart(read.readData("prodresult"), read.readData("price"));

	}

	@Test(priority = 5, testName = "Sumpage", description = "Sumpage", timeOut = 190000, enabled = true, groups = {
			"sanity" })
	public void validatesummarypage() throws InterruptedException {
		// home.searchProduct(read.readData("Prodname"),
		// read.readData("prodresult"),read.readData("price"), read.readData("total
		// results"));
		Thread.sleep(3000);
		// home.addtocart(read.readData("prodresult"), read.readData("price"));
		Summary.sumpage(read.readData("prodresult"), read.readData("price"));

	}

	@Test(priority = 6, testName = "Deleteitem", description = "Deleteitem", timeOut = 190000, enabled = true, groups = {
			"sanity" })
	public void Deleteitem() throws InterruptedException {

		Summary.deleteitemsincart(read.readData("prodresult"));

	}

	@Test(priority=7,testName = "cartaddress", description = "cartaddress", timeOut = 190000, enabled = true, groups = {
	"sanity"})
     public void cartaddress() throws InterruptedException {
		 home.searchProduct(read.readData("Prodname"), read.readData("prodresult"),read.readData("price"), read.readData("total results"));
		 //Thread.sleep(3000);
		 home.addtocart(read.readData("prodresult"), read.readData("price"));
		  Summary.sumpage(read.readData("prodresult"), read.readData("price"));
		  Summary.nextinsummarypage();
		  address.cartddruncheck();
		  
	}
		
    @Test(priority=8,testName = "cart_shipping", description = "cart_shipping", timeOut = 190000, enabled = true, groups = {
			"sanity"})
	public void shipping() throws InterruptedException {
		 
			  Summary.sumpage(read.readData("prodresult"), read.readData("price"));
			  Summary.nextinsummarypage();
			  address.cartddrcheckbox();
			  
			  shipping.validateshippingpage();
	}
    
	
    @Test(priority=9,testName = "cart_payment", description = "cart_payment", timeOut = 190000, enabled = true, groups = {
			"sanity"})
	public void payment() throws InterruptedException {
		 
			  Summary.sumpage(read.readData("prodresult"), read.readData("price"));
			  Summary.nextinsummarypage();
			  address.cartddrcheckbox();
			  shipping.shippingpage();
			  payment.paymentpage(read.readData("prodresult"),read.readData("price"));
			  payment.chequelink();
	}
	
    

    @Test(priority=10,testName = "ordersummary and confirmation", description = "ordersummary and confirmation", timeOut = 190000, enabled = true, groups = {
			"sanity"})
	public void cartsubmit() throws InterruptedException {
		 
			  Summary.sumpage(read.readData("prodresult"), read.readData("price"));
			  Summary.nextinsummarypage();
			  address.cartddrcheckbox();
			  shipping.shippingpage();
			  payment.paymentpage(read.readData("prodresult"),read.readData("price"));
			  payment.chequelink();
			  submit.ordersummary();
			  submit.confirmorder();
	}
 
}


