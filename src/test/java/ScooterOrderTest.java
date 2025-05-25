import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practicum.pageobject.AboutRentingAScooterPage;
import ru.practicum.pageobject.HomePage;
import ru.practicum.pageobject.PageOrder;


import java.util.Arrays;
import java.util.Collection;


import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ScooterOrderTest extends BaseTest {
    private static final String FIRST_NAME_1 = "Женя";
    private static final String LAST_NAME_1 = "Лукашин";
    private static final String ADDRESS_1 = "Москва, Третья улица Строителей, дом 25";
    private static final String METRO_1 = "Юго-западная";
    private static final String PHONE_1 = "81234567890";
    private static final String DATE_1 = "25.05.2025";
    private static final String COLOR_1 = "black";
    private static final String COMMENT_1 = "Пить надо меньше, меньше надо пить";

    // Тестовые данные для второго теста (средняя кнопка)
    private static final String FIRST_NAME_2 = "Алиса";
    private static final String LAST_NAME_2 = "Селезнева";
    private static final String ADDRESS_2 = "Москва, переулок Островского, дом 16";
    private static final String METRO_2 = "Черкизовская";
    private static final String PHONE_2 = "89991455567";
    private static final String DATE_2 = "26.05.2025";
    private static final String COLOR_2 = "black";
    private static final String COMMENT_2 = "Я за кефиром пошел, а тут такие приключения!";


    private final String buttonType;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String color;
    private final String comment;

    public ScooterOrderTest(String buttonType, String firstName, String lastName,
                            String address, String metro, String phone,
                            String date, String color, String comment) {
        this.buttonType = buttonType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Тест заказа через {0} кнопку для {1} {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"header", FIRST_NAME_1, LAST_NAME_1, ADDRESS_1, METRO_1, PHONE_1, DATE_1, COLOR_1, COMMENT_1},
                {"middle", FIRST_NAME_2, LAST_NAME_2, ADDRESS_2, METRO_2, PHONE_2, DATE_2, COLOR_2, COMMENT_2}
        });
    }

    @Test
    public void testScooterOrder() {
        // Открытие страницы и принятие cookies
        HomePage homePage = new HomePage(driver)
                .open()
                .acceptCookies();

        // Клик по соответствующей кнопке заказа
        if ("header".equals(buttonType)) {
            homePage.clickHeaderOrderButton();
        } else {
            homePage.clickMiddleOrderButton();
        }

        // Заполнение формы заказа
        new PageOrder(driver)
                .fillOrderForms(firstName, lastName, address, metro, phone)
                .clickNext();

        // Заполнение данных аренды и подтверждение
        boolean isOrderConfirmed = new AboutRentingAScooterPage(driver)
                .fillRentalDetails(date, color, comment)
                .confirmOrder()
                .isOrderConfirmed();

        // Проверка
        assertTrue("Заказ через " + buttonType + " кнопку не подтвердился", isOrderConfirmed);
    }
}
