import org.junit.Test;
import ru.practicum.pageObject.AboutRentingAScooterPage;
import ru.practicum.pageObject.HomePage;
import ru.practicum.pageObject.PageOrder;

import static org.junit.Assert.assertTrue;

public class ScooterOrderTest extends BaseTest {
    // Тест заказа через кнопку сверху
    @Test
    public void rentAScooterUsingTheButtonAtTheTop() {
        AboutRentingAScooterPage aboutPage = new AboutRentingAScooterPage(driver);

        new HomePage(driver)
                .openSite()
                .clickCookieButton()
                .clickHeaderOrderButton();

        new PageOrder(driver)
                .sendClientFirstName("Женя")
                .sendClientLastName("Лукашин")
                .sendDeliveryAddress("Москва, Третья улица Строителей, дом 25")
                .selectMetroStation("Юго-западная")
                .sendClientPhoneNumber("81234567890")
                .clickNextButton();

        boolean isConfirmed = aboutPage
                .sendRentalDate("25.05.2025")
                .setRentalTime()
                .selectColor("grey")
                .sendComment("Пить надо меньше, меньше надо пить")
                .clickOrderButton()
                .clickOrderConfirmationButton()
                .isOrderConfirmationDisplayed();
        assertTrue("Подтверждение заказа не отобразилось", isConfirmed);
    }
    @Test
    public void rentAScooterUsingTheMiddleButton() {
        AboutRentingAScooterPage aboutPage = new AboutRentingAScooterPage(driver);

        new HomePage(driver)
                .openSite()
                .clickCookieButton()
                .clickMiddleOrderButton();

        new PageOrder(driver)
                .sendClientFirstName("Алиса")
                .sendClientLastName("Селезнева")
                .sendDeliveryAddress("Москва, переулок Островского, дом 16")
                .selectMetroStation("Черкизовская")
                .sendClientPhoneNumber("89991455567")
                .clickNextButton();

        boolean isConfirmed = aboutPage
                .sendRentalDate("26.05.2025")
                .setRentalTime()
                .selectColor("black")
                .sendComment("Я за кефиром пошел, а тут такие приключения!")
                .clickOrderButton()
                .clickOrderConfirmationButton()
                .isOrderConfirmationDisplayed();

        assertTrue("Подтверждение заказа не отобразилось при использовании средней кнопки", isConfirmed);
    }
}



