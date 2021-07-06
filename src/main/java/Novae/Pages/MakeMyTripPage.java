package Novae.Pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MakeMyTripPage {

	public WebDriver driver;
	public WebDriverWait w;


	private final By fromCity = By.id("fromCity");
	private final By to = By.xpath("//input[@placeholder='To']");

	private final By adults = By.xpath("//li[@data-cy='adults-1']");
	private final By children = By.xpath("//li[@data-cy='children-1']");
	private final By travelerClass = By.xpath("//li[@data-cy='travelClass-1']");
	private final By returnDate = By.xpath("//p[@data-cy='returnDefaultText']");
	private final By autoPop = By.xpath("//div[contains(@class,'autopop__wrap makeFlex')]");

	private final By flexible = By.xpath("//li[@data-cy='account']");
	private final By from = By.xpath("//input[@placeholder='From']");
	private final By flightTraveller = By.xpath("//div[@data-cy='flightTraveller']");
	private final By apply = By.xpath("//button[@data-cy='travellerApplyBtn']");
	private final By listSearches = By.cssSelector("ul[class='react-autosuggest__suggestions-list']");
	private final By airportName = By.cssSelector("p[class*='appendBottom3']");
	private final By iata = By.cssSelector("div[class*='pushRight']");
	private final By currentMonth = By.cssSelector("div.DayPicker-Body");
	private final By daysOfMonths = By.cssSelector("div.DayPicker-Day");

	private final By multyCity = By.xpath("(//span[@class='tabsCircle appendRight5'])[3]");
	private final By addAnotherCity = By.xpath("(//button[@data-cy='addAnotherCity'])[2]");
	private final By getFromCities=By.xpath("//span[text()='From']");
	private final By getToCities=By.xpath("//span[text()='To']");
	private final By countrySelect=By.cssSelector("span.latoBold.ctrySelectText");
	private final By cabsButton=By.xpath("//span[contains(@class,'chCabs')]");

	private final By more=By.xpath("//span[@data-cy='More']");
	private final By holidays=By.xpath("//p[@data-cy='HOLIDAYS']");
	private final By slickList=By.cssSelector("div.landingCardSlider.superOffers");
	private final By buttonNext=By.xpath("(//button[@class='slick-arrow slick-next'])[2]");
	private final By linkElement=By.tagName("a");

	private final By hotelsButton=By.xpath("//span[contains(@class,'chHotels')]");
	private final By searchButton=By.xpath("//a[contains(@class,'widgetSearchBtn')]");

	public WebElement getHotelsButton() {
		return driver.findElement(hotelsButton);
	}

	public WebElement getButtonNext() {
		return driver.findElement(buttonNext);
	}

	public WebElement getSlickList() {
		return driver.findElement(slickList);
	}

	public WebElement getHolidays() {
		return driver.findElement(holidays);
	}

	public WebElement getMore() {
		return driver.findElement(more);
	}

	public WebElement getCabsButton() {
		return driver.findElement(cabsButton);
	}

	public MakeMyTripPage(WebDriver driver, WebDriverWait w) {

		this.driver = driver;
		this.w = w;

	}

	public MakeMyTripPage(WebDriver driver) {

		this.driver = driver;
	}

	public WebElement getAutoPop() {
		return driver.findElement(autoPop);
	}

	public WebElement getFrom() {
		return driver.findElement(from);
	}

	public WebElement getFlightTraveler() {
		return driver.findElement(flightTraveller);
	}

	public WebElement getFlexible() {
		return driver.findElement(flexible);
	}


	public WebDriver getDriver() {
		return driver;
	}


	public WebElement getFromCity() {
		return driver.findElement(fromCity);
	}

	public WebElement getTo() {
		return driver.findElement(to);
	}

	public WebElement getAdults() {
		return driver.findElement(adults);
	}

	public WebElement getChildren() {
		return driver.findElement(children);
	}

	public WebElement getTravelerClass() {
		return driver.findElement(travelerClass);
	}

	public WebElement getReturnDate() {
		return driver.findElement(returnDate);
	}

	public WebElement getApply() {
		return driver.findElement(apply);
	}

	public void evadePopup() {
		try {
			if (getAutoPop().isDisplayed()) {
				getFlexible().click();
				System.out.println("Aparecio el popup");
			}
		} catch (Exception e) {
			System.out.println("No aparecio el Popup");
		}
	}

	public List<WebElement> getSuggestions() {
		WebElement lista = driver.findElement(listSearches);

		try {
			w.until(ExpectedConditions.numberOfElementsToBeLessThan(airportName, 20));
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("search terminated");
		}

		return lista.findElements(iata);

	}

	public WebElement getToday() {

		WebElement Calendar = driver.findElement(currentMonth);
		List<WebElement> days = Calendar.findElements(daysOfMonths);
		WebElement today = days.get(0);
		for (WebElement day : days) {
			if (day.getAttribute("class").contains("today")) {
				System.out.println(day.getAttribute("class"));
				today = day;
			}
		}
		return today;
	}

	public WebElement getMultiCity() {
		return driver.findElement(multyCity);
	}

	public WebElement getAddAnotherCity() {
		return driver.findElement(addAnotherCity);
	}

	public void validateOptions(List<WebElement> suggestions, String city) {
		if (suggestions.size() > 1) {
			System.out.println("There are " + suggestions.size() + " options for " + city);
		} else if (suggestions.size() > 0) {
			System.out.println("There is " + suggestions.size() + " option for " + city);
		} else {
			System.out.println("There are " + "0" + " options for " + city);
		}
	}

	public void printIataSuggestions(List<WebElement> suggestions) {
		for (WebElement suggestion : suggestions) {
			System.out.println(suggestion.getText());
		}
	}

	public void selectAirport(List<WebElement> suggestions, String airport) {
		for (WebElement suggestion : suggestions) {
			if (suggestion.getText().equals(airport)) {
				suggestion.click();
				break;
			}
		}
	}

	public List<WebElement> getFromCities()
	{
		return driver.findElements(getFromCities);
	}

	public List<WebElement> getToCities()
	{
		return driver.findElements(getToCities);
	}

	public WebElement getCountrySelect() {
		return driver.findElement(countrySelect);
	}

	public void scrollToSuperOffers() {

		JavascriptExecutor js=(JavascriptExecutor) driver; //Scroll start
		js.executeScript("window.scrollBy(0,700)");
		js.executeScript("document.querySelector('.landingCardSlider.superOffers').scrollTop=200");
	}

	public void switchToNewTab()
	{
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
	}

	public void selectOffer(WebElement slickList)
	{
		int index;
		for (int i = 0; i < 2; i++) {
			index=3*i+1;
			w.until(ExpectedConditions.visibilityOf(slickList.findElement(getSlideLastIndex(index))));
			List<WebElement> items=slickList.findElements(linkElement);
			System.out.println(items.size());
			for (WebElement item : items) {
				if (item.getText().contains("Book")) {
					item.click();
					break;
				}
			}
			getButtonNext().click();
		}
	}

	public void waitMore()
	{
		w.until(ExpectedConditions.presenceOfElementLocated(more));
	}

	public By getSlideLastIndex(int index)
	{
		return By.cssSelector("div[data-index='"+index+"']");
	}


	public WebElement getSearchButton() {
		return driver.findElement(searchButton);
	}
}
