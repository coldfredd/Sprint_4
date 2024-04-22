package sprint4.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.time.Duration.ofSeconds;

public class MainPage {

    private final WebDriver webDriver;
    private final By orderStatusLocator = By.xpath("//button[text()='Статус заказа']"); // кнопка Статус заказа
    private final By cookiesButtonLocator = By.id("rcc-confirm-button"); // кнопка закрытия куки
    private final By orderNumberInputLocator = By.xpath("//input[@placeholder='Введите номер заказа']"); // поле ввода номера заказа
    private final By goButtonLocator = By.xpath("//button[text()='Go!']"); // кнопка Go!
    private final By notFoundImgLocator = By.xpath(".//img[@alt='Not found']"); // Нет заказа
    private final By createOrderButtonTopLocator = By.xpath("//div[contains(@class,'Header')]//button[text()='Заказать']"); // кнопка Заказать Верхняя
    private final By createOrderButtonLowerLocator = By.xpath("//div[contains(@class,'ThirdPart')]//button[text()='Заказать']"); // кнопка Заказать Нижняя
    private final String questionLocator = "accordion__heading-%s"; // Выпадающий список
    private final String answerLocator = "//div[contains(@id, 'accordion__panel')][.='%s']"; // Текст внутри списка

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // Проверка: если ввести неправильный номер заказа, попадёшь на страницу статуса заказа. На ней должно быть написано, что такого заказа нет.
    public void clickOrderStatusButton() { // Клик на кнопка Статус заказа
        WebElement orderStatusButton = webDriver.findElement(orderStatusLocator);
        orderStatusButton.click();
    }

    public void enterOrderNumber(String orderNumber) { // Ввод номера заказа
        WebElement orderInput = webDriver.findElement(orderNumberInputLocator);
        new WebDriverWait(webDriver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(orderInput));
        orderInput.sendKeys(orderNumber);
    }

    public void clickGoButton() { // Клик на кнопку Go!
        WebElement goButton = webDriver.findElement(goButtonLocator);
        new WebDriverWait(webDriver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(goButton));
        goButton.click();
        new WebDriverWait(webDriver, ofSeconds(5)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//img[@alt='Not found']")));
    }

    public boolean notFoundImgIsDisplayed() { // Картинка такого заказа нет

        return webDriver.findElement(notFoundImgLocator).isDisplayed();
    }

    // Заказ самоката. Нужно проверить весь флоу позитивного сценария с двумя наборами данных. Проверить точки входа в сценарий, их две: кнопка «Заказать» вверху страницы и внизу.

    public void clickCreateOrderTop(){ // Клик на кнопку Заказать
        WebElement createOrderTopButton = webDriver.findElement(createOrderButtonTopLocator);
        createOrderTopButton.click();
    }

    public void clickCreateOrderLower(){ // Клик на кнопку Заказать
        WebElement createOrderLowerButton = webDriver.findElement(createOrderButtonLowerLocator);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",createOrderLowerButton);
        new WebDriverWait(webDriver,ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(createOrderLowerButton));
        createOrderLowerButton.click();
    }

    public void closeCookiesWindow() { // Закрытие окна куки
        webDriver.findElement(cookiesButtonLocator).click();
    }
    public void expandQuestion(int index) { // клик на список
        WebElement element = webDriver.findElement(By.id(String.format(questionLocator, index)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",element);
        new WebDriverWait(webDriver,ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public boolean answerIsDisplayed(String expectedAnswer) { // Проверка текста из списка
        WebElement element = webDriver.findElement(By.xpath(String.format(answerLocator,expectedAnswer)));
        return element.isDisplayed();
    }
}
