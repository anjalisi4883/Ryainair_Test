package functions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class bookingFunctions{
	
	
	WebDriver driver= null;
	
	public void initializeBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "C:/Users/GOLU/Downloads/driver/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ryanair.com/ie/en");
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[text()='Yes, I agree']")).click();
		
	}
	
	public void login() {
		
        driver.findElement(By.xpath("//*[contains(text(),'Log in')]")).click();
		WebDriverWait wait = new WebDriverWait(driver,30);
		driver.switchTo().activeElement();
		//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='email']"))));
		WebElement emailId = driver.findElement(By.xpath("//input[@name='email']"));
		emailId.click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("anjalisi4883@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Ryanair@123");
		driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
		//String username = driver.findElement(By.xpath("//button//span[@class='ry-header__user-name']")).getText();
		//Assert.assertEquals("anjalisi4883@gmail.com",username);
	}
	
	
	public void bookingHomepage(String strFrom, String strTo, int adultscount , int childrencount) throws InterruptedException {
		driver.switchTo().activeElement();
        WebDriverWait wait = new WebDriverWait(driver,30);
		WebElement btnOneWay=driver.findElement(By.xpath("//button[@aria-label='One way']"));
		//wait.until(ExpectedConditions.elementToBeClickable(btnOneWay));
		btnOneWay.click();
		driver.findElement(By.xpath("//input[@placeholder='Departure']")).clear();
		driver.findElement(By.xpath("//input[@placeholder='Departure']")).sendKeys(strFrom);
		driver.findElement(By.xpath("//input[@placeholder='Destination']")).sendKeys(strTo);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@data-ref='airport-item__name']")).click();
		driver.findElement(By.xpath("//div[text()=' Choose date ']")).click();
		driver.switchTo().activeElement();
		WebElement Date = driver.findElement(By.xpath("//div[@data-id='2020-12-18']"));
		Date.click();
		
		driver.findElement(By.xpath("//*//div[text()=' 1 Adult ']")).click();
		
		driver.switchTo().activeElement();
		for (int i=1 ;i<adultscount ; i++) 
		{	
		driver.findElement(By.xpath("(//*//div[contains(@data-ref,\"counter.counter__increment\")])[1]")).click();
		}
		for (int j=0 ;j<childrencount ; j++)
		{
		driver.findElement(By.xpath("(//*//div[contains(@data-ref,\"counter.counter__increment\")])[3]")).click();
		}
		driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
		
	}
	
	
	public void booking2ndpage() throws InterruptedException {
		
		String selecteddate=driver.findElement(By.xpath("//span[@class='date-item__day-of-month h4 date-item__day-of-month--selected']")).getText();
		String selectedmonth=driver.findElement(By.xpath("//span[@class='date-item__month h4 date-item__month--selected']")).getText();
		String ActualDate = selecteddate.concat(selectedmonth);
		Assert.assertEquals("18 Dec", ActualDate); //validate date as '2020-12-18'
		Thread.sleep(1000);
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js. executeScript("window. scrollBy(0,250)", "");
		driver.findElement(By.xpath("//div[@class='app-scroll-container']"));
		driver.switchTo().activeElement();
		driver.findElement(By.cssSelector("div.card-info__cols-container")).click();
		WebElement Value= driver.findElement(By.xpath("//button//span[contains(text(),'Continue with Value fare')]"));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("argument[0].ScrollIntoView();",Value);
		Value.click();
	}
	
	public void enterPassengerDetails() {
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div//*[contains(text(),'Passengers')]"))));
		Select Title = new Select(driver.findElement(By.xpath("//div[@class='dropdown__menu-items']")));
		//1st passenger
		Title.selectByVisibleText("Mr");
		driver.findElement(By.xpath("//input[@id='formState.passengers.ADT-0.name']")).sendKeys("John");
		driver.findElement(By.xpath("//input[@id='formState.passengers.ADT-0.surname']")).sendKeys("Jordan");
		//2nd passenger
		Title.selectByVisibleText("Mrs");
		driver.findElement(By.xpath("//input[@id='formState.passengers.ADT-1.name']")).sendKeys("Anne");
		driver.findElement(By.xpath("//input[@id='formState.passengers.ADT-1.surname']")).sendKeys("Jordan");
		//child details
		driver.findElement(By.xpath("//input[@id='formState.passengers.CHD-0.name']")).sendKeys("Anne");
		driver.findElement(By.xpath("//input[@id='formState.passengers.CHD-0.surname']")).sendKeys("Jordan");
		driver.findElement(By.xpath("//button[@class='continue-flow__button ry-button--gradient-yellow']"));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()=' Okay, got it. ']"))));
		driver.findElement(By.xpath("//button[text()=' Okay, got it. ']"));
	}
	
	public void selectSeats() {
		List<WebElement> seats=driver.findElements(By.xpath("//button[@class='ng-star-inserted seatmap__seat seatmap__seat--standard']"));
		for (int i=0; i <= 2; i++)
		{
			WebElement clck =seats.get(i);
			clck.click();
		}
		driver.findElement(By.xpath("//button[text()=' Continue ']")).click();//popup - No Thanks select
		driver.findElement(By.xpath("//button[text()=' No, thanks ']")).click();
	}
	
	public void cabinbagselection() {
		
		
		WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p/span[text()=' *Mandatory selection ']"))));
		driver.findElement(By.xpath("//input[@value='cabin-bag']")).click(); //Select Add cabin bag charged
		driver.findElement(By.xpath("//section//button[contains(text(),'Continue')]")).click();
		driver.findElement(By.xpath("//div//button[contains(text(),'Continue')]")).click();
		
	}
	
	
	public void checkoutPaymentpage()  {
		
		WebDriverWait wait = new WebDriverWait(driver,20);
		//basket click and checkout to payment page
		driver.findElement(By.xpath("//icon[@class='basket-total-icon__tick']/span")).click();
		driver.findElement(By.xpath("//button[@data-ref='basket-continue-flow__check-out']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h3[text()=' Contact details ']"))));
		driver.findElement(By.xpath("//ry-input-d[@formcontrolname='phoneNumber']//input")).sendKeys("899809628");
		driver.findElement(By.xpath("//div[@class='insurance__opt-out section-content ng-star-inserted']//div[@class='_background']")).click();
		
	}
	
	
		public void paymentsdetailsenter(int cardno, int expirydetails, int cvvdetails) {
		
		driver.findElement(By.xpath("//div//input[@pattern='[0-9 ]*']")).sendKeys("cardno");
		driver.findElement(By.xpath("//button[@class='dropdown__toggle b2']/span[text()='Month']")).click();
		driver.findElement(By.xpath("//button//div[text()='October']")).click();
		driver.findElement(By.xpath("//button//div[text()='2022']")).click();
		driver.findElement(By.xpath("//div//input[@placeholder='CVV']")).click();
		driver.findElement(By.xpath("//div//input[@placeholder='CVV']")).sendKeys("cvvdetails");
		driver.findElement(By.xpath("//*[@formcontrolname='accountName']/div//input[@class='b2 date-placeholder']")).click();
		driver.findElement(By.xpath("//*[@formcontrolname='accountName']/div//input[@class='b2 date-placeholder']")).sendKeys("Testname holder");
		driver.findElement(By.xpath("//*[@class='address-form__input ng-pristine ng-invalid ng-touched']/div/input")).click();
		driver.findElement(By.xpath("//*[@class='address-form__input ng-pristine ng-invalid ng-touched']/div/input")).sendKeys("Test Address");
		driver.findElement(By.xpath("//*[@formcontrolname='city']//input")).click();
		driver.findElement(By.xpath("//*[@formcontrolname='city']//input")).sendKeys("Dublin");
		driver.findElement(By.xpath("//*[@formcontrolname='country']//input")).click();
		driver.findElement(By.xpath("//*[@formcontrolname='country']//input")).sendKeys("Ireland");
		driver.findElement(By.xpath("//*[@formcontrolname='country']//input")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@data-ref='add-card-modal__postcode']//input")).sendKeys("12455");
		driver.findElement(By.xpath("(//*[@class='_background _background--checked'])[3]")).click();
		driver.findElement(By.xpath("//div/button[text()=' Pay now ']")).click();
		
		}
		
		
		public void validateDeclinepayment() {
		
		String Payment_errorMsgActual= driver.findElement(By.xpath("//div[@class='b2 ng-star-inserted']")).getText();
		String Payment_errorMsgExpected= "Oops, something went wrong. Please check your payment details and try again";
		
		Assert.assertEquals(Payment_errorMsgExpected, Payment_errorMsgActual);
		System.out.println("Payment Decline message ++"+Payment_errorMsgActual);
		
	
		
		}
		
		
	}
	
	
	
	
	
	
	
	
	
