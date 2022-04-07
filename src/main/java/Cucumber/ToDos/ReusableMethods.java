package Cucumber.ToDos;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods {
	public void waitforPageToLoad(String Xpath, String pageName, WebDriver driver, String expectedTitle) {

		WebDriverWait wait = new WebDriverWait(driver, 50);
		Actions builder = new Actions(driver);
		builder.moveByOffset(174, 490).doubleClick().build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Xpath)));
		String curr_window_text = driver.findElement(By.xpath(Xpath)).getAttribute("placeholder");

		Assert.assertEquals("Loaded " + pageName + "successfully", curr_window_text, expectedTitle);
	}
}
