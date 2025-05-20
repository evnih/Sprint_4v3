package ru.practicum.pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AboutRentingAScooterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    //Локатор поля даты начала аренды
    private final By rentalDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Локатор поля срока аренды
    private final By rentalTimeField = By.className("Dropdown-placeholder");
    //Локатор списка срока аренды
    private final By rentalTime = By.xpath(".//*[(@role ='option' and text()='трое суток')]");
    //Локатор цвета самоката "Черный жемчуг"
    private final By blackColorCheckbox = By.xpath(".//input[@id='black']");
    //Локатор цвета самоката "Серая безысходность"
    private final By grayColorCheckbox = By.xpath(".//input[@id='grey']");
    //Локатор поля комментария для курьера
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Локатор кнопки "Заказать"
    private final By orderButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    //Локатор кнопки "Да" подтверждение заказа
    private final By confirmButton = By.xpath("//button[text()='Да']");
    //Локатор модального окна подтверждения заказа
    private final By confirmationWindow = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    public AboutRentingAScooterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Ввод даты доставки
    public AboutRentingAScooterPage sendRentalDate(String date) {
        driver.findElement(rentalDateField).sendKeys(date);
        driver.findElement(rentalDateField).sendKeys(Keys.ENTER);
        return this;

    }

    //Ввод срока аренды
    public AboutRentingAScooterPage setRentalTime() {
        driver.findElement(rentalTimeField).click();
        driver.findElement(rentalTime).click();
        return this;
    }

    // Метод для выбора цвета
    public AboutRentingAScooterPage selectColor(String color) {
        if (color.equalsIgnoreCase("black")) {
            driver.findElement(blackColorCheckbox).click();
        } else {
            driver.findElement(grayColorCheckbox).click();
        }
        return this;
    }

    // Ввод комментария для курьера
    public AboutRentingAScooterPage sendComment(String userComment) {
        driver.findElement(commentField).sendKeys(userComment);
        return this;
    }

    // Клик по Кнопке "Заказать"
    public AboutRentingAScooterPage clickOrderButton() {
        driver.findElement(orderButton).click();
        return this;
    }


    //Клик по кнопке "Да" оформления заказа
    public AboutRentingAScooterPage clickOrderConfirmationButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationWindow));
        driver.findElement(confirmButton).click();
        return this;
    }

    //Заказ оформлен
    public boolean isOrderConfirmationDisplayed() {
        return driver.findElement(confirmationWindow).isDisplayed();
    }
}

