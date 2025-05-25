package ru.practicum.pageobject;

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
    private final By rentalTimeOption = By.xpath(".//div[@class='Dropdown-option' and text()='трое суток']");
    //Локатор цвета самоката "Черный жемчуг"
    private final By blackColorCheckbox = By.xpath(".//input[@id='black']");
    //Локатор цвета самоката "Серая безысходность"
    private final By grayColorCheckbox = By.xpath(".//input[@id='grey']");
    //Локатор поля комментария для курьера
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Локатор кнопки "Заказать"
    private final By orderButton = By.xpath(".//button[(@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать')]");
    //Локатор кнопки "Да" подтверждение заказа
    private final By confirmButton = By.xpath(".//button[(@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да')]");
    //Локатор модального окна подтверждения заказа
    private final By confirmationModal = By.cssSelector("div.Order_Modal__YZ-d3");

    public AboutRentingAScooterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //Ввод даты доставки
    public AboutRentingAScooterPage sendRentalDate(String date) {
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(rentalDateField));
        dateInput.sendKeys(date, Keys.ENTER);
        return this;

    }

    //Ввод срока аренды
    public AboutRentingAScooterPage setRentalTime() {
        wait.until(ExpectedConditions.elementToBeClickable(rentalTimeField)).click();
        wait.until(ExpectedConditions.elementToBeClickable(rentalTimeOption)).click();
        return this;
    }

    // Метод для выбора цвета
    public AboutRentingAScooterPage selectColor(String color) {
        By colorLocator = color.equalsIgnoreCase("black") ? blackColorCheckbox : grayColorCheckbox;
        wait.until(ExpectedConditions.elementToBeClickable(colorLocator)).click();
        return this;
    }

    // Ввод комментария для курьера
    public AboutRentingAScooterPage sendComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
        return this;
    }
    // Клик по Кнопке "Заказать"
    public AboutRentingAScooterPage confirmOrder() {
        clickOrderButton();
        clickConfirmationButton();
        return this;
    }
    //Проверяет отображение подтверждения заказа

    public boolean isOrderConfirmed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationModal))
                    .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
    private void clickOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }

    private void clickConfirmationButton() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }
    public AboutRentingAScooterPage fillRentalDetails(String date, String color, String comment) {
        return this.sendRentalDate(date)
                .setRentalTime()
                .selectColor(color)
                .sendComment(comment);
    }
}