import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amazon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		WebElement Search = driver.findElement(By.xpath("//input[@name='field-keywords']"));
		Search.sendKeys("samsung");
		WebElement submit = driver.findElement(By.id("nav-search-submit-button"));
		submit.click();
		List<WebElement> Model = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		List<WebElement> Price = driver
				.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));

		for (int i = 0; i < Model.size(); i++) {

			System.out.println("The Model " + Model.get(i).getText() + "The Price is " + Price.get(i).getText());
		}
		String ParentWin = driver.getWindowHandle();
		String ExpectedValue = Model.get(0).getText();
		// Clicking on first Item
		Model.get(0).click();
		Set<String> allWins = driver.getWindowHandles();

		for (String win : allWins) {

			System.out.println(win);

			if (!win.equals(ParentWin)) {
				driver.switchTo().window(win);
			}
		}
		WebElement Title = driver.findElement(By.id("productTitle"));
		if (ExpectedValue.equals(Title.getText())) {
			System.out.println("Title mached");

		} else {

			System.out.println("Not Matched");
		}

	}
	// div[@data-component-type='s-search-result']//span[@class='a-price']
	// span[@class='a-price-whole']
	// div[@data-component-type='s-search-result']//span[@class='a-price']//span[@class='a-price-whole']

}
