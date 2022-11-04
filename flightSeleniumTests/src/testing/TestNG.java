package testing;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Function;


public class TestNG {
	WebDriver driver;
	
	@BeforeMethod
	void setUp() {
		driver = new ChromeDriver();
		driver.get("http://localhost:3000/");
	}
	
	@AfterMethod
	void close() {
		driver.quit();
	}
	
	public WebElement fluentWait(final By locator) {
	    Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	            .withTimeout(Duration.ofSeconds(30))
	            .pollingEvery(Duration.ofSeconds(5))
	            .ignoring(NoSuchElementException.class);

	    WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
	        public WebElement apply(WebDriver driver) {
	            return driver.findElement(locator);
	        }
	    });

	    return  foo;
	};
	
//	@Parameters({"calcs","result"})
//	@Test
//	public void runTestCalculator(@Optional("2+3") String text,@Optional("5") String result){
//
//		WebElement accept = driver.findElement(By.id("L2AGLb"));
//		WebElement searchBar = driver.findElement(By.xpath("//body/div[1]/div[3]/form[1]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]"));
//		accept.click();
//		searchBar.sendKeys(text);
//		searchBar.submit();
//		WebElement calculatorResult = driver.findElement(By.id("cwos"));
//		
//		Assert.assertEquals(calculatorResult.getText(),result);
//	}
	
	@Parameters({"login","pass","city","day","result"})
	@Test
	public void testSelectFlight(@Optional("pera") String login,@Optional("pass") String pass,@Optional("Sao Paulo") String city,@Optional("1") String day,@Optional("Available Flights") String result) throws InterruptedException {
		login(login,pass);
		Thread.sleep(1000);
		selectFlightDetails(city,Integer.parseInt(day)+1);
		Thread.sleep(1000);
		
		WebElement available = driver.findElement(By.xpath("//div[contains(text(),'Available Flights')]"));
		Assert.assertEquals(available.getText(),result);
	}
	
	public void login(String user,String pass) {
		WebElement username = fluentWait(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[2]/input[1]"));
		username.sendKeys(user);
		WebElement password =fluentWait(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[3]/input[1]"));
		password.sendKeys(pass);
		
		WebElement login = fluentWait(By.xpath("//button[contains(text(),'Login')]"));
		login.click();
	}
	
	public void selectFlightDetails(String originToSelect,int day) {
		WebElement origin = driver.findElement(By.id("origin"));
		origin.click();
		WebElement originOption = driver.findElement(By.id(originToSelect));
		originOption.click();
		WebElement destination = driver.findElement(By.id("destination"));
		destination.click();
		int var=1;
		WebElement destinationSelection = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/div[1]/div[1]/div[2]/div[2]/select[1]/option["+var+"]"));
		destinationSelection.click();
		WebElement selectDay = driver.findElement(By.xpath("//body/div[@id='__next']/div[@id='app_wrapper']/main[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/button["+day+"]"));
		selectDay.click();
		
		WebElement searchFlight = driver.findElement(By.xpath("//button[contains(text(),'Search Flights')]"));
		searchFlight.click();
	}
	
}
