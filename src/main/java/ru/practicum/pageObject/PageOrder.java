package ru.practicum.pageobject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class PageOrder {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //Локатор Имя клиента
    private final By firstNameField = By.cssSelector("[placeholder*='Имя']");
    //Локатор Фамилия клиента
    private final By lastNameField = By.cssSelector("[placeholder*='Фамилия']");
   //Локатор Адрес доставки
    private final By addressField = By.cssSelector("[placeholder*='Адрес: куда привезти заказ']");
    //Локатор станция метро
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    //Локатор телефона клиента
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Локатор кнопки далее
    private final By nextButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    public PageOrder(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Заполняем форму
    public PageOrder fillOrderForms(String firstName, String lastName, String address,
                                String metro, String phone){
              return setFirstName(firstName)
                .setLastName(lastName)
                .setAddress(address)
                .selectMetro(metro)
                .setPhone(phone);

    }
//Вводим Имя
    private PageOrder setFirstName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField))
                .clear();
        driver.findElement(firstNameField).sendKeys(name);
        return this;
    }
//Вводим Фамилию
    private PageOrder setLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField))
                .clear();
        driver.findElement(lastNameField).sendKeys(lastName);
        return this;
    }
//Вводим адрес доставки
    private PageOrder setAddress(String address) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressField))
                .clear();
        driver.findElement(addressField).sendKeys(address);
        return this;
    }


    public PageOrder selectMetro(String metroStation) {
        WebElement metroElement = wait.until(ExpectedConditions.elementToBeClickable(metroField));
        metroElement.click();
        metroElement.sendKeys(metroStation);
        metroElement.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        return this;

    }

    public PageOrder setPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField))
                .clear();
        driver.findElement(phoneField).sendKeys(phone);
        return this;
    }
    public AboutRentingAScooterPage clickNext() {
            driver.findElement(nextButton).click();
            return new AboutRentingAScooterPage(driver);
        }


    }

