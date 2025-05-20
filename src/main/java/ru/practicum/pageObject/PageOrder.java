package ru.practicum.pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;


public class PageOrder {
    private final WebDriver driver;

    //Локатор Имя клиента
    private final By clientFirstName = By.cssSelector(".Input_InputContainer__3NykH input[placeholder*='Имя']");
    //Локатор Фамилия клиента
    private final By clientLastName = By.cssSelector(".Input_InputContainer__3NykH input[placeholder*='Фамилия']");
   //Локатор Адрес доставки
    private final By deliveryAddress = By.cssSelector(".Input_InputContainer__3NykH input[placeholder*='Адрес: куда привезти заказ']");
    //Локатор станция метро
    private final By deliveryMetroStation = By.xpath(".//input[@placeholder='* Станция метро']");
    //Локатор телефона клиента
    private final By deliveryClientPhoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Локатор кнопки далее
    private final By nextButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    public PageOrder(WebDriver driver) {
        this.driver = driver;
    }
//Вводим Имя
    public PageOrder sendClientFirstName(String firstName) {
        driver.findElement(clientFirstName).sendKeys(firstName);
        return this;
    }
//ВВодим Фамилию
    public PageOrder sendClientLastName(String lastName) {
        driver.findElement(clientLastName).sendKeys(lastName);
        return this;
    }
//Вводим адрес доставки
    public PageOrder sendDeliveryAddress(String address) {
        driver.findElement(deliveryAddress).sendKeys(address);
        return this;
    }


    public PageOrder selectMetroStation(String metroStationFromOrder) {
        driver.findElement(deliveryMetroStation).click();
        driver.findElement(deliveryMetroStation).sendKeys(metroStationFromOrder);
        driver.findElement(deliveryMetroStation).sendKeys(Keys.DOWN,Keys.ENTER);
        return this;

    }

    public PageOrder sendClientPhoneNumber(String phoneNumber) {
        driver.findElement(deliveryClientPhoneNumber).sendKeys(phoneNumber);
        return this;
    }

    public void clickNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement nextButtonElement = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
            nextButtonElement.click();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось нажать кнопку 'Далее'", e);
        }
    }
}
