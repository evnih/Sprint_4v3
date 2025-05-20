package ru.practicum.pageObject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class HomePage {
        private final WebDriver driver;

        // Локатор кнопки сообщения у кукисах
        private final By cookieButton = By.id("rcc-confirm-button");
        // Локатор кнопки Заказа вверху
        private final By upOrderButton = By.className("Button_Button__ra12g");
        // Локатор кнопки Заказа в середине
        private final By midOrderButton = By.className("Button_Middle__1CSJM");

        // Локаторы кнопок с вопросами
        private static final String[] dropDownQuestionsArray = {
                "accordion__heading-0",
                "accordion__heading-1",
                "accordion__heading-2",
                "accordion__heading-3",
                "accordion__heading-4",
                "accordion__heading-5",
                "accordion__heading-6",
                "accordion__heading-7"
        };

        // Локаторы кнопок с ответами
        private static final String[] dropDownAnswersArray = {
                "accordion__panel-0",
                "accordion__panel-1",
                "accordion__panel-2",
                "accordion__panel-3",
                "accordion__panel-4",
                "accordion__panel-5",
                "accordion__panel-6",
                "accordion__panel-7"
        };

        public HomePage(WebDriver driver) {

            this.driver = driver;
        }

        // Открыть главную
        public HomePage openSite() {
            driver.get("https://qa-scooter.praktikum-services.ru/");
            return this;
        }

        //Локатор кнопка для закрытия кукис
        public HomePage clickCookieButton() {
            driver.findElement(cookieButton).click();
            return this;
        }


        //Клик кнопки заказа вверху
        public HomePage clickHeaderOrderButton() {
            driver.findElement(upOrderButton).click();
            return this;
        }

        //Клик кнопки заказа в середине
        public HomePage clickMiddleOrderButton() {
            driver.findElement(midOrderButton).click();
            return this;
        }

        // Скролл страницы до блока с вопросами
        public HomePage scrollPage() {
            WebElement lastQuestionArrow = driver.findElement(By.id(dropDownQuestionsArray[7]));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", lastQuestionArrow);
            return this;
        }


        //Локатор для клика по стрелке выпадающего списка
        public HomePage clickQuestionArrow(int questionNumber) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id(dropDownQuestionsArray[questionNumber])));
            element.click();
            return this;
        }

        //Проверка соответсвия тектса ответов ОР и ФР
        public void checkText(String expectedText, int answerNumber) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id(dropDownAnswersArray[answerNumber])));

            Assert.assertEquals("Текст не совпадает для ответа номер: " + answerNumber,
                    expectedText, element.getText());
        }

        //Клик по кнопке вопроса
        public HomePage clickQuestionButton(String questionButtonLocator) {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.id(questionButtonLocator)));
            element.click();
            return this;
        }
}
