package ru.netology.test.sql;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.helpers.APIHelper;
import ru.netology.helpers.DataGenerator;
import ru.netology.helpers.SQLHelper;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TestAPISQL {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void cleanTable() {
        SQLHelper.cleanDatabase();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    //Отправка POST запроса на кредит с валидно заполненным body и Карта APPROVED на http://localhost:9999/credit
    //Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test//Баг нет записей в БД
    @DisplayName("SQLCredit № 1 add Credit data to the database with a valid APPROVED card via API")
    void creditAPIAndSQLHappyPath() {
        var cardInfo = DataGenerator.generateDataWithApprovedCard();
        var statusCode = APIHelper.getRequestStatusCodeCredit(cardInfo);
        var creditCardData = SQLHelper.getCreditCardData();

        assertEquals(200, statusCode);//проверка статуса API карта валидная статус APPROVED, код должен быть 200
        assert creditCardData != null;
        assertEquals("APPROVED", creditCardData.getStatus());//"УТВЕРЖДЕНО", Данные платежной карты. Получить Статус из БД
        assertEquals(200, statusCode, String.valueOf(!Objects.equals(creditCardData.getStatus(), "APPROVED")));
    }

    //Отправка POST запроса на кредит с валидно заполненным body и Карта Declined на http://localhost:9999/credit
    //Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test//Баг нет записей в БД
    @DisplayName("SQLCredit № 2 Credit For Services With DECLINED Card")
    void creditAPIAndSQLSadPath() {
        var cardInfo = DataGenerator.generateDataWithDeclineCard();
        var statusCode = APIHelper.getRequestStatusCodeCredit(cardInfo);
        var creditCardData = SQLHelper.getCreditCardData();

        assertEquals(200, statusCode);//проверка статуса API карта валидная статус DECLINED, код должен быть 200
        assert creditCardData != null;
        assertEquals("DECLINED", creditCardData.getStatus());//"DECLINED", Данные платежной карты. Получить Статус из БД
        assertEquals(200, statusCode, String.valueOf(!Objects.equals(creditCardData.getStatus(), "DECLINED")));
    }

    //Отправка POST запроса на кредит с пустым body на http://localhost:9999/credit
    //Ожидаемый результат: статус 400, отсутствие записей в БД
    @Test//OK
    @DisplayName("SQLCredit № 3 Credit For Services With empty Card")
    void creditForServicesWithEmptyCard() {
        var cardInfo = DataGenerator.CardEmptyFields();
        var statusCode = APIHelper.getRequestStatusCodeCredit(cardInfo);
        var creditCardData = SQLHelper.getCreditCardData();

        assertEquals(400, statusCode);//проверка статуса API карта пустое тело, код должен быть 400
        assertNull(creditCardData);// Получить Статус из БД, нет статуса в БД не должно быть записей null
        assertEquals(400, statusCode, String.valueOf((Object) null));
    }

    //Отправка POST запроса платежа с валидно заполненным body и Карта APPROVED на http://localhost:9999/payment
    //Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test//БАг нет записей в БД
    @DisplayName("SQLPay № 4 Payment data to the database with a valid APPROVED card via API")
    void paymentAPIAndSQLHappyPath() {
        var cardInfo = DataGenerator.generateDataWithApprovedCard();
        var statusCode = APIHelper.getRequestStatusCodePayment(cardInfo);
        var paymentCardData = SQLHelper.getPaymentCardData();

        assertEquals(200, statusCode);//проверка статуса API карта валидная статус APPROVED, код должен быть 200
        assert paymentCardData != null;
        assertEquals("APPROVED", paymentCardData.getStatus());//"УТВЕРЖДЕНО", Данные платежной карты. Получить Статус из БД
        assertEquals(200, statusCode, String.valueOf(!Objects.equals(paymentCardData.getStatus(), "APPROVED")));

    }

    //Отправка POST запроса платежа с валидно заполненным body и Карта DECLINED на http://localhost:9999/payment
    //Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test//Баг нет записей в БД
    @DisplayName("SQLPay № 5 Payment data to the database with a valid DECLINED Card via API ")
    void paymentAPIAndSQLSadPath() {
        var cardInfo = DataGenerator.generateDataWithDeclineCard();
        var statusCode = APIHelper.getRequestStatusCodePayment(cardInfo);
        var paymentCardData = SQLHelper.getPaymentCardData();

        assertEquals(200, statusCode);//проверка статуса API карта валидная статус APPROVED, код должен быть 200
        assert paymentCardData != null;
        assertEquals("DECLINED", paymentCardData.getStatus());//"УТВЕРЖДЕНО", Данные платежной карты. Получить Статус из БД
        assertEquals(200, statusCode, String.valueOf(!Objects.equals(paymentCardData.getStatus(), "DECLINED")));
    }

    //Отправка POST запроса на кредит с пустым body на http://localhost:9999/credit
    //Ожидаемый результат: статус 400, отсутствие записей в БД
    @Test//OK
    @DisplayName("SQLCredit № 6 Credit For Services With empty Card")
    void paymentForServicesWithEmptyCard() {
        var cardInfo = DataGenerator.CardEmptyFields();
        var statusCode = APIHelper.getRequestStatusCodePayment(cardInfo);
        var creditCardData = SQLHelper.getPaymentCardData();

        assertEquals(400, statusCode);//проверка статуса API карта пустое тело, код должен быть 400
        assertNull(creditCardData);// Получить Статус из БД, нет статуса в БД не должно быть записей null
        assertEquals(400, statusCode, String.valueOf((Object) null));
    }
}
