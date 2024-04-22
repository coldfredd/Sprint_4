package sprint4.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class OrderPage {
    private final WebDriver webDriver;
    private final By nameInputLocator = By.xpath("//input[@placeholder='* Имя']"); //поле Имя
    private final By lastnameInputLocator = By.xpath("//input[@placeholder='* Фамилия']"); //поле Фамилия
    private final By addressInputLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']"); //поле адреса
    private final By subwayInputLocator = By.xpath("//input[@placeholder='* Станция метро']"); //поле выбора метро
    private final By phoneInputLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']"); //поле номера
    private final By nextButtonLocator = By.xpath("//button[text()='Далее']"); //кнопка далее
    private final By dateInputLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");//дата когда привезти самокат
    private final By rentalPeriodLocator = By.xpath("//div[text()='* Срок аренды']");//срок аренды
    private final By createOrderButtonLocator = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");//кнопка заказать
    private final By orderAcceptButtonLocator = By.xpath("//button[text()='Да']");//кнопка да, для подтверждения заказа
    private final By orderProcessedLocator = By.xpath("//div[text()='Заказ оформлен']");//текст Заказ оформлен


    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void fillCustomerInfo(String name, String lastname, String address, String stationName, String phone) {//Заполнение информации клиента
        WebElement nameInput = webDriver.findElement(nameInputLocator);
        nameInput.sendKeys(name);

        WebElement lastNameInput = webDriver.findElement(lastnameInputLocator);
        lastNameInput.sendKeys(lastname);

        WebElement addressInput = webDriver.findElement(addressInputLocator);
        addressInput.sendKeys(address);

        WebElement subwayInput = webDriver.findElement(subwayInputLocator);
        subwayInput.click();

        WebElement stationMenu = webDriver.findElement(By.xpath(".//*[text()='" + stationName + "']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",stationMenu);
        stationMenu.click();

        WebElement phoneInput = webDriver.findElement(phoneInputLocator);
        phoneInput.sendKeys(phone);
    }

    public void clickNextButton() {//кнопка Далее

        WebElement nextButton = webDriver.findElement(nextButtonLocator);
        nextButton.click();
    }

    public void fillAboutRent(String date,String rentTitle){ //Заполнение данных про аренду
        
        WebElement dateInput = webDriver.findElement(dateInputLocator);
        dateInput.sendKeys(date, Keys.ENTER);
        
        WebElement rentPeriodInput = webDriver.findElement(rentalPeriodLocator);
        rentPeriodInput.click();

        WebElement rentPeriodMenuItem = webDriver.findElement(By.xpath("//div[text()='" + rentTitle +"']"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",rentPeriodMenuItem);
        rentPeriodMenuItem.click();
    }

    public void clickCreateOrderButton () { //кнопка Заказать
        WebElement createOrderButton = webDriver.findElement(createOrderButtonLocator);
        createOrderButton.click();
    }

    public void clickOrderAcceptButton () {//кнопка Да
        WebElement orderAcceptButton = webDriver.findElement(orderAcceptButtonLocator);
        orderAcceptButton.click();
        new WebDriverWait(webDriver, ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(orderProcessedLocator));
    }

    public boolean orderProcessedIsDisplayed () {//Проверка на успешный заказ
        return webDriver.findElement(orderProcessedLocator).isDisplayed();
    }

}

