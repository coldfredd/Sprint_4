package sprint4.plain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import sprint4.WebDriverFactory;
import sprint4.page.MainPage;
import sprint4.page.OrderPage;
import static org.junit.Assert.assertTrue;

public class OrderTest {

    private static final String BROWSER = "chrome";
    private WebDriver webDriver;

    @Before
    public void setup(){
        webDriver = WebDriverFactory.getWebDriver(BROWSER);
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void orderNotFound() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOrderStatusButton();

        mainPage.enterOrderNumber("asd");

        mainPage.clickGoButton();

        assertTrue(mainPage.notFoundImgIsDisplayed());
    }

    @Test
    public void createOrderFirstVersion() {

        MainPage mainPage = new MainPage(webDriver);

        mainPage.clickCreateOrderTop();

        OrderPage orderPage = new OrderPage(webDriver);

        orderPage.fillCustomerInfo("Имя", "Фамилия", "Адрес", "Арбатская", "89844444444");
        orderPage.clickNextButton();

        orderPage.fillAboutRent("01.01.2025","трое суток");
        orderPage.clickCreateOrderButton();

        orderPage.clickOrderAcceptButton();

        assertTrue(orderPage.orderProcessedIsDisplayed());
    }

    @Test
    public void createOrderSecondVersion() {

        MainPage mainPage = new MainPage(webDriver);

        mainPage.clickCreateOrderLower();

        OrderPage orderPage = new OrderPage(webDriver);

        orderPage.fillCustomerInfo("ИмяИмя", "ФамилияФамилия", "АдресАдрес", "Измайлово", "12345678901");
        orderPage.clickNextButton();

        orderPage.fillAboutRent("05.06.2025","сутки");
        orderPage.clickCreateOrderButton();

        orderPage.clickOrderAcceptButton();

        assertTrue(orderPage.orderProcessedIsDisplayed());
    }



    @After
    public void tearDown(){
        webDriver.quit();
    }
}
