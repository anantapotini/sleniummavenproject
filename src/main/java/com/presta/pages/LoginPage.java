package com.presta.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.selenium.common.Configuration;
import com.selenium.common.ReadExcel;

public class LoginPage {

	public WebDriver driver = Configuration.browser();
	public ReadExcel read;

	@FindBy(id = "email")
	public WebElement emailTextBox;

	@FindBy(id = "passwd")
	public WebElement pwdTextBox;

	@FindBy(id = "SubmitLogin")
	public WebElement loginBtn;

	@FindBy(xpath = "//li[text()='Invalid e-mail address']")
	public WebElement errorMessage;
	
	

	public LoginPage() {
		PageFactory.initElements(driver, this);
		read = new ReadExcel();
	}

	public void loginData(String userName, String pwd) {

		emailTextBox.clear();
		emailTextBox.sendKeys(userName);
		pwdTextBox.clear();
		pwdTextBox.sendKeys(pwd);
		loginBtn.click();

	}
	
	public void validateErrorMessage() {
		Assert.assertTrue(errorMessage.isDisplayed(), "error message is not displayed ");
	}
}
