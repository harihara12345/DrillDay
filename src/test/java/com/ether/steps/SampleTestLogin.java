package com.ether.steps;

import java.sql.ResultSet;

import org.openqa.selenium.WebElement;

import com.codoid.products.fillo.Select;
import com.ether.base.SeleniumDriver;
import com.ether.base.TestUtil;
import com.ether.pages.actions.LoginAction;
import com.ether.pages.locators.HomePageLocators;
import com.ether.pages.locators.LoginPageLocators;
import com.ether.pages.locators.SavingPageLocators;


//import com.ether.pages.locators.loginPageLocators;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SampleTestLogin {
	TestUtil util = new TestUtil();
	LoginPageLocators locator = new LoginPageLocators();
	HomePageLocators Homelocator = new HomePageLocators();
	SavingPageLocators Savinglocator = new SavingPageLocators();

	@Given("^Launch url$")
	public void loginToUrl() {
		SeleniumDriver.openPage("http://elastic.rapidtestpro.com:8086/index");
	}

	@And("^login with userName and password$")
	public void loginWithusername() throws Exception {
		String userName = null;
		String password = null;
		util.setUpDBConnection();
		String query = "Select * from User_Details";
		ResultSet result = util.getDataFromDB(query);

		while (result.next()) {
			userName = result.getString("Username");
			password = result.getString("Password");
		}
		SeleniumDriver.inputValuesInElement(locator.username, userName);
		SeleniumDriver.inputValuesInElement(locator.username, password);
		locator.signIn.click();
		util.closeDBConnection();
		
	}
	
	@When("^Click the Deposit option and Select the Saving option from the drop down$")
	public void ClickDepositSelectSaving() throws Exception
	{
		Homelocator.deposit.click();
		WebElement s = Savinglocator.Savingoption;
		Select selectaccount = new Select(s);
        selectaccount.selectByVisibleText("Savings");
        
	}
	
	@Then("^Input the amount to be deposited and Click Deposit button$")
	public void DepositInputAndClick() throws Exception
	{
		int Amount=5000;
        Savinglocator.SavingAmount.sendKeys(Amount);
        Savinglocator.DepositClick.click();
	}
	
	
		
		
		
	}
}
