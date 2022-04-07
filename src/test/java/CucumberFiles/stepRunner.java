package CucumberFiles;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Cucumber.ToDos.ReusableMethods;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class stepRunner extends Xpath {

	public static WebDriver driver;

	@Given("^Launch Chrome driver in path \"([^\"]*)\" and initialize webdriver with base url \"([^\"]*)\"$")
	public void Launch_chromeDriver(String driverPath, String url) {
		try {

			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(url);
			ReusableMethods rm = new ReusableMethods();
			rm.waitforPageToLoad(textBox, "ToDoPage", driver, "What needs to be done?");
		} catch (Exception e) {
			Assert.fail("Error in launching URL");
		}

	}

	@Given("^User enters To do List \"([^\"]*)\"$")
	public void User_enters_To_do_List(String todoLists) {
		try {

			driver.findElement(By.xpath(textBox)).clear();
			String[] toDoList = todoLists.split(",");
			for (int i = 0; i < toDoList.length; i++) {
				if (i > 0) {
					driver.findElement(By.xpath(textBox1)).sendKeys(toDoList[i]);
				} else {
					driver.findElement(By.xpath(textBox)).sendKeys(toDoList[i]);
				}
				driver.findElement(By.xpath(enter)).sendKeys(Keys.ENTER);
			}
		} catch (Exception e) {
			Assert.fail("Error in entering value in text box");
		}

	}

	@Then("^verify if the item listed in added \"([^\"]*)\"$")
	public void if_the_item_listed_in_added(String todoLists) {
		try {

			String[] toDoList = todoLists.split(",");
			for (int i = 0; i < toDoList.length; i++) {
				String curr_window_text = driver
						.findElement(By.xpath("//label[contains(text(),'" + toDoList[i] + "')]")).getText();
				Assert.assertEquals("To do list Added", curr_window_text, toDoList[i]);
			}
		} catch (Exception e) {
			Assert.fail("Error in reading to do list");
		}
	}

	@And("^verify if \"([^\"]*)\" item crossed out and counter added \"([^\"]*)\"$")
	public void verify_if_item_crossed_out_and_counter_added(String completeList, String todoLists) throws Throwable {

		try {
			String[] toDoList = todoLists.split(",");
			driver.findElement(By.xpath("//label[contains(text(),'" + completeList + "')]")).click();
			driver.findElement(By.xpath(textBox2)).click();
			String curr_window_text = driver
					.findElement(By.xpath("//label[contains(text(),'" + completeList + "')]/ancestor::li"))
					.getAttribute("class");
			Assert.assertEquals("To do list completed", curr_window_text, "ng-scope completed");
			curr_window_text = driver.findElement(By.xpath(bindingText)).getText();
			int toDocount = toDoList.length - 1;
			Assert.assertEquals("Count got subtrscted", curr_window_text, String.valueOf(toDocount));
		} catch (Exception e) {
			Assert.fail("Error in reading crossed and counter");
		}
	}

	@Then("^delete the item$")
	public void delete_the_item() {
		try {
			driver.findElement(By.xpath(textBox2)).click();
			driver.findElement(By.xpath(delete)).click();
		} catch (Exception e) {
			Assert.fail("Error in deleting item");
		}

	}

	@And("^verify if item is removed from list \"([^\"]*)\"$")
	public void verify_if_item_is_removed_from_list(String completItem) {
		try {
			String curr_window_text = driver.findElement(By.xpath(label)).getText();
			Assert.assertNotEquals("To do list Added", curr_window_text, completItem);
		} catch (Exception e) {
			Assert.fail("Error in verifying removed item");
		}
	}

	@Then("^click on active button to check active items \"([^\"]*)\"$")
	public void click_on_active(String activeItem) {
		try {
			driver.findElement(By.xpath(active)).click();
			String curr_window_text = driver.findElement(By.xpath(label)).getText();
			Assert.assertEquals("Active filter working as expected", curr_window_text, activeItem);
		} catch (Exception e) {
			Assert.fail("Error in reading active item");
		}

	}

	@And("^click on completed button to check completed items \"([^\"]*)\"$")
	public void click_on_completed(String completedItem) {
		try {
			driver.findElement(By.xpath(completed)).click();
			String curr_window_text = driver.findElement(By.xpath(label)).getText();
			Assert.assertEquals("Completed filter working as expected", curr_window_text, completedItem);

		} catch (Exception e) {
			Assert.fail("Error in reading completed item");
		}
	}

	@After
	public void quitDriver() {
		try {
			driver.close();
			driver.quit();
		} catch (Exception e) {
			Assert.fail("Error in quiting driver");
		}
	}

}
