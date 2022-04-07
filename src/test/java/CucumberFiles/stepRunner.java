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
	public void Launch_chromeDriver(String driverPath, String url) throws Throwable {

		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);
		ReusableMethods rm = new ReusableMethods();
		rm.waitforPageToLoad(textBox, "ToDoPage", driver, "What needs to be done?");

	}

	@Given("^User enters To do List \"([^\"]*)\"$")
	public void User_enters_To_do_List(String todoLists) throws Throwable {

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

	}

	@Then("^verify if the item listed in added \"([^\"]*)\"$")
	public void if_the_item_listed_in_added(String todoLists) throws Throwable {

		String[] toDoList = todoLists.split(",");
		for (int i = 0; i < toDoList.length; i++) {
			String curr_window_text = driver.findElement(By.xpath("//label[contains(text(),'" + toDoList[i] + "')]"))
					.getText();
			Assert.assertEquals("To do list Added", curr_window_text, toDoList[i]);
		}
	}

	@And("^verify if \"([^\"]*)\" item crossed out and counter added \"([^\"]*)\"$")
	public void verify_if_item_crossed_out_and_counter_added(String completeList, String todoLists) throws Throwable {

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
	}

	@Then("^delete the item$")
	public void delete_the_item() throws Throwable {
		driver.findElement(By.xpath(textBox2)).click();
		driver.findElement(By.xpath(delete)).click();

	}

	@And("^verify if item is removed from list \"([^\"]*)\"$")
	public void verify_if_item_is_removed_from_list(String completItem) throws Throwable {
		String curr_window_text = driver.findElement(By.xpath(label)).getText();
		Assert.assertNotEquals("To do list Added", curr_window_text, completItem);
	}

	@Then("^click on active button to check active items \"([^\"]*)\"$")
	public void click_on_active(String activeItem) throws Throwable {
		driver.findElement(By.xpath(active)).click();
		String curr_window_text = driver.findElement(By.xpath(label)).getText();
		Assert.assertEquals("Active filter working as expected", curr_window_text, activeItem);

	}

	@And("^click on completed button to check completed items \"([^\"]*)\"$")
	public void click_on_completed(String completedItem) throws Throwable {
		driver.findElement(By.xpath(completed)).click();
		String curr_window_text = driver.findElement(By.xpath(label)).getText();
		Assert.assertEquals("Completed filter working as expected", curr_window_text, completedItem);
	}

	@After
	public void quitDriver() {
		driver.close();
		driver.quit();
	}

}
