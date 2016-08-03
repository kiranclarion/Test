package tp;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class AutoSuggest {
	// test auto
		WebDriver driver;
		WebDriverWait wait;

		String URL = "http://jqueryui.com/autocomplete/";
		private By frameLocator = By.className("demo-frame");
		private By tagText = By.id("tags");

		@BeforeClass
		public void Setup() {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, 5);
		}

		@Test
		public void rightClickTest() {
			driver.navigate().to(URL);
			WebElement frameElement=driver.findElement(frameLocator);
			driver.switchTo().frame(frameElement);
			wait.until(ExpectedConditions.presenceOfElementLocated(tagText));
			WebElement textBoxElement = driver.findElement(tagText);
			textBoxElement.sendKeys("a");
			selectOptionWithText("Java");
			//selectOptionWithIndex(2);
			
		}

//to select the Option based on the string passed in the Test.
		@Test
     public void selectOptionWithText(String textToSelect) {
	try {
		WebElement autoOptions = driver.findElement(By.id("ui-id-1"));
		wait.until(ExpectedConditions.visibilityOf(autoOptions));

		List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));
		for(WebElement option : optionsToSelect){
	        if(option.getText().equals(textToSelect)) {
	        	System.out.println("Trying to select: "+textToSelect);
	            option.click();
	            break;
	        }
	    }
		
	} catch (NoSuchElementException e) {
		System.out.println(e.getStackTrace());
	}
	catch (Exception e) {
		System.out.println(e.getStackTrace());
	}
   }

	// Below is the method to select Option based on the index value
		public void selectOptionWithIndex(int indexToSelect) {
			
			try {
				WebElement autoOptions = driver.findElement(By.id("ui-id-1"));
				wait.until(ExpectedConditions.visibilityOf(autoOptions));

				List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));
			        if(indexToSelect<=optionsToSelect.size()) {
			        	System.out.println("Trying to select based on index: "+indexToSelect);
			           optionsToSelect.get(indexToSelect).click();
			        }
			} 		
			catch (NoSuchElementException e) {
				System.out.println(e.getStackTrace());
			}
			catch (Exception e) {
				System.out.println(e.getStackTrace());
			}
		}

		@AfterClass
		public void tearDown() {
			driver.quit();
		}

		
}

