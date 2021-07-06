package Novae.Resources;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {
	
	public WebDriver driver;

	private final String path=System.getProperty("user.dir");
	private final String pathChrome = path + "\\drivers\\chromedriver.exe";
	private final String pathMozilla = path + "\\drivers\\geckodriver.exe";
	private final String pathProperties = path + "\\src\\main\\java\\Novae\\resources\\data.properties";

	public WebDriver initializeDriver() throws IOException {
		
		System.out.println(path);
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream(pathProperties);
		props.load(fis);
		String browserName = System.getProperty("browser");
		System.out.println(browserName);
		//opens makemytrip page

		if (browserName==null) {
			System.out.println("Default configuration");
			System.setProperty("webdriver.chrome.driver", pathChrome);
			driver = new ChromeDriver();
		} else {

		if(browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver",pathChrome );
			ChromeOptions options=new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("--headless");
			}
			driver=new ChromeDriver(options);
			
		}
		else if(browserName.contains("firefox")){

			System.setProperty("webdriver.gecko.driver", pathMozilla);
			FirefoxOptions options=new FirefoxOptions();
			options.addArguments("headless");
			driver=new FirefoxDriver(options);
		}
		}

		String url = props.getProperty("URL");
		driver.get(url);
		System.out.println(url);
		driver.manage().window().maximize();
		
		fis.close();
		return driver;

	}
	//explicit wait
	
	public WebDriverWait initializeWait()
	{
		return new WebDriverWait(driver,10);
	}

}
