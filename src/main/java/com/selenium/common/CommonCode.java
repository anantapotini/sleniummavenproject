package com.selenium.common;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


import com.google.common.base.Function;

//this class is were we will use all the reuseable functions e.g getwindow handle.

public class CommonCode {
	public WebDriver driver = Configuration.browser(); // This will read the
														// configuration.java >
														// Gets the webdriver
														// instance

	public void getWindowHandle() {
		Set<String> handles = driver.getWindowHandles();// We will use this if
														// windows is greater
														// than 1 - otherwise we
														// just getwindowhandle
														// ();
		if (handles.size() >= 1) {
			System.out.println("Number of broiwsers opened are"
					+ handles.size());
			for (String handle : handles) {
				driver.switchTo().window(handle);

			}
		}

	}

	
	

}
