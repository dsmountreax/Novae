package Novae.testcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Novae.Pages.MakeMyTripPage;
import Novae.Resources.Base;
import Novae.Resources.ExcelData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OneWay_TestCase extends Base {

    private WebDriver driver;
    private WebDriverWait w;

    @BeforeTest
    public void startDriver() throws IOException {
        driver = initializeDriver();
        w = initializeWait();
        driver.manage().window().maximize();
    }

    @Test(dataProvider="getData")
    public void baseNavigation(String cityFrom,String cityTo,String airportFrom,String airportTo) {

        driver.get("https://www.makemytrip.com"); //
        driver.getCurrentUrl();

        System.out.println(driver.getCurrentUrl());

        MakeMyTripPage mmt = new MakeMyTripPage(driver, w);
        mmt.evadePopup();
        mmt.getFromCity().click();
        mmt.getFrom().sendKeys(cityFrom);
        List<WebElement> suggestionsFrom = mmt.getSuggestions();
        mmt.validateOptions(suggestionsFrom,cityFrom);
        mmt.printIataSuggestions(suggestionsFrom);
        mmt.selectAirport(suggestionsFrom, airportFrom);
        mmt.getTo().sendKeys(cityTo);
        List<WebElement> suggestionsTo = mmt.getSuggestions();
        mmt.validateOptions(suggestionsTo,cityTo);
        mmt.printIataSuggestions(suggestionsTo);
        mmt.selectAirport(suggestionsTo, airportTo);
        mmt.getToday().click();
        mmt.getFlightTraveler().click();
        mmt.getAdults().click();
        mmt.getChildren().click();
        mmt.getTravelerClass().click();
        mmt.getApply().click();
        validateReturn(mmt);
        mmt.getSearchButton().click();

    }


    @AfterTest
    public void closedriver() {
        driver.close();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        Object[][] data=new Object[1][4];
        ExcelData d=new ExcelData();
        ArrayList<String> excelData=d.getExcelData("OneWay");

        for (int i = 0; i < 4; i++) {
            data[0][i]=excelData.get(i+1);
        }
        return data;
    }

    private void validateReturn(MakeMyTripPage trip) {
        if (returnIsDisabled(trip)) {
            System.out.println("Return option is disabled");
        } else {
            System.out.println("Return option is enabled");
        }

    }

    private boolean returnIsDisabled(MakeMyTripPage mmt) {
        return mmt.getReturnDate().getText().equals("Tap to add a return date for bigger discounts");
    }

}

//mvn test -Dbrowser=chrome
//mvn test -Dbrowser=firefox
