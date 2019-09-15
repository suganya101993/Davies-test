package stepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
public class stepDefinition {
	WebDriver driver;
	
	
	
@Given("^User is on \"([^\"]*)\" page and \"([^\"]*)\"$")
	public void user_is_on_page_and(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete action
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\chromedriver_win32\\chromedriver.exe");
 	driver = new ChromeDriver();
 	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.get(arg1);
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).click();
	driver.findElement(By.cssSelector("input#email_create")).sendKeys(arg2);
	
	driver.findElement(By.cssSelector("button#SubmitCreate")).click();
    
}
 	
  @When("^I enter all Mandatory details$")
	public void i_enter_all_Mandatory_details(DataTable testData) throws Throwable {

		List<String> details = testData.asList(String.class);
		driver.findElement(By.cssSelector("input#id_gender2")).click();
		driver.findElement(By.cssSelector("input#customer_firstname")).sendKeys(details.get(0));
		driver.findElement(By.cssSelector("input#customer_lastname")).sendKeys(details.get(1));
		driver.findElement(By.cssSelector("input#email")).click();
		driver.findElement(By.cssSelector("input#passwd")).sendKeys(details.get(2));
		driver.findElement(By.cssSelector("input#address1")).sendKeys(details.get(3));
		driver.findElement(By.cssSelector("input#city")).sendKeys(details.get(4));
		Select s = new Select(driver.findElement(By.xpath("//*[@id=\"id_state\"]")));
		s.selectByValue("30");
		driver.findElement(By.cssSelector("input#postcode")).sendKeys(details.get(5));
		driver.findElement(By.cssSelector("input#phone_mobile")).sendKeys(details.get(6));
		driver.findElement(By.cssSelector("input#alias")).sendKeys((Keys.chord(Keys.CONTROL, "a")), details.get(7));
		driver.findElement(By.cssSelector("button#submitAccount")).click();

	}

	@Then("^Validating the user account with the \"([^\"]*)\"$")
	public void validating_the_user_account_with_the(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		  String expected_URL = driver.getCurrentUrl();
		if (arg1.equalsIgnoreCase(expected_URL)) {
			System.out.println("Valid user Login ");
			validating_both_products_are_same();
		} else {
			System.out.println("Invalid user Login ");
			System.out.println("Required Fields is missing");
			driver.quit();
		}
	}

	@And("^Validating both products are same$")
    public void validating_both_products_are_same() throws Throwable {
	
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/a")).click();
		
		//Element to be Interactable
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		String Add_cart = driver.findElement(By.linkText("Faded Short Sleeve T-shirts")).getText();
		driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[2]/div[2]/a[1]/span")).click();
		driver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/span")).click();
		driver.findElement(By.xpath("//*[@id=\"header\"]/div[3]/div/div/div[3]/div/a")).click();
		String product = driver.findElement(By.linkText("Faded Short Sleeve T-shirts")).getText();
		if (Add_cart.equals(product)) {
			System.out.println("Added item and the item added in the cart page both are same");
		} else {
			System.out.println("Added item and the item added in the cart page both are Different");
		}

	 
    }

}