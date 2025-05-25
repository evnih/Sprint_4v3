package ru.practicum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class HomePage {
        private static final String URL = "https://qa-scooter.praktikum-services.ru/";
        private final WebDriver driver;
        private final WebDriverWait wait;

        // Локатор кнопки сообщения у кукисах
        private final By cookieButton = By.id("rcc-confirm-button");
        // Локатор кнопки Заказа вверху
        private final By upOrderButton = By.className("Button_Button__ra12g");
        // Локатор кнопки Заказа в середине
        private final By midOrderButton = By.className("Button_Middle__1CSJM");
        // Локаторы кнопок с вопросами
        private final By[] dropDownQuestion = new By[8];
        // Локаторы кнопок с ответами
        private final By[] dropDownAnswers = new By[8];

        {
            for (int i = 0; i < 8; i++) {
                dropDownQuestion[i] = By.id("accordion__heading-" + i);
                dropDownAnswers[i] = By.id("accordion__panel-" + i);
            }
        }

        public HomePage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }

        // Открыть главную
        public HomePage open() {
            driver.get(URL);
            return this;
        }

        //Локатор кнопка для закрытия кукис
        public HomePage acceptCookies() {
            wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
            return this;
        }

        //Клик кнопки заказа вверху
        public PageOrder clickHeaderOrderButton() {
            wait.until(ExpectedConditions.elementToBeClickable(upOrderButton)).click();
            return new PageOrder(driver);
        }


        //Клик кнопки заказа в середине
        public PageOrder clickMiddleOrderButton() {
            wait.until(ExpectedConditions.elementToBeClickable(midOrderButton)).click();
            return new PageOrder(driver);
        }

        // Скролл страницы до блока с вопросами
        public HomePage scrollPage() {
            String lastQuestionLocator = String.format("accordion__heading-%d", 7);

            // Находим элемент с явным ожиданием
            WebElement lastQuestionArrow = wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.id(lastQuestionLocator))
            );

            // Скролл с плавной анимацией и центрированием
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                    lastQuestionArrow
            );

            // Дополнительная проверка видимости
            wait.until(ExpectedConditions.visibilityOf(lastQuestionArrow));
            return this;
        }


        //Локатор для клика по стрелке выпадающего списка
        public HomePage clickQuestion(int index) {
            String questionLocator = String.format("accordion__heading-%d", index);
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id(questionLocator)))
                    .click();
            return this;
        }

        //Проверка соответсвия тектса ответов ОР и ФР
        public String checkText(int index) {
            String answerLocator = String.format("accordion__panel-%d", index);
            return new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id(answerLocator)))
                    .getText();
        }
    }


