package ru.netology.test.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.helpers.APIHelper;
import ru.netology.helpers.DataGenerator;
import ru.netology.helpers.SQLHelper;
import ru.netology.helpers.helpers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAPIPaymentCard {

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

    //1. Отправка POST запроса платежа с валидно заполненным body и Карта APPROVED на http://localhost:9999/payment
    //Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test
    @DisplayName("TestAPIPay№ 1 Payment for services with an APPROVED card")
    void paymentForServicesWithApprovedCard() {
        int statusCode = APIHelper.getRequestStatusCodePayment(DataGenerator.generateDataWithApprovedCard());
        assertEquals(200, statusCode);
    }

    //2. Отправка POST запроса платежа с валидно заполненным body и Карта DECLINED на http://localhost:9999/payment
    //   Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test
    @DisplayName("TestAPIPay№ 2 Payment For Services With Declined Card")
    void paymentForServicesWithDeclinedCard() {
        int statusCode = APIHelper.getRequestStatusCodePayment(DataGenerator.generateDataWithDeclineCard());
        assertEquals(200, statusCode);
    }

    //3. Отправка POST запроса с пустым body на http://localhost:9999/payment
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test
    @DisplayName("TestAPIPay№ 3 Payment For Services With Card Empty Fields")
    void paymentForServicesWithCardEmptyFields() {
        int statusCode = APIHelper.getRequestStatusCodePayment(DataGenerator.CardEmptyFields());
        assertEquals(400, statusCode);
    }

    //4. Отправка POST запроса платежа с пустым значением у атрибута number в body (остальные данные заполнены валидно) на http://localhost:9999/payment
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test
    @DisplayName("TestAPIPay№ 4 Payment For Services With Card Empty Field Number")
    void paymentForServicesWithCardEmptyFieldNumber() {
        int statusCode = APIHelper.getRequestStatusCodePayment(NumberHelper.dataWithCardNumberEmpty());
        assertEquals(400, statusCode);
    }

    //5. Отправка POST запроса платежа с пустым значением у атрибута month в body (остальные данные заполнены валидно) на http://localhost:9999/payment
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем месяц попадает в бд, со статусом одобрено
    @DisplayName("TestAPIPay№ 5 Payment For Services With Month Empty Field")
    void paymentForServicesWithCardEmptyFieldMonth() {
        int statusCode = APIHelper.getRequestStatusCodePayment(MonthHelper.approvedCardWithMonthEmptyField());
        assertEquals(400, statusCode);
    }

    //6. Отправка POST запроса платежа с пустым значением у атрибута year в body (остальные данные заполнены валидно) на http://localhost:9999/payment
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем год попадает в бд, со статусом одобрено
    @DisplayName("TestAPIPay№ 6 Payment For Services With Year Empty Field")
    void paymentForServicesWithCardYearEmptyField() {
        int statusCode = APIHelper.getRequestStatusCodePayment(YearHelper.approvedCardWithYearEmptyField());
        assertEquals(400, statusCode);
    }

    //7. Отправка POST запроса платежа с пустым значением у атрибута holder в body (остальные данные заполнены валидно) на http://localhost:9999/payment
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем владелец попадает в бд, со статусом одобрено
    @DisplayName("TestAPIPay№ 7 Payment For Services With Holder Empty Field")
    void paymentForServicesWithCardHolderEmpty() {
        int statusCode = APIHelper.getRequestStatusCodePayment(HolderHelper.approvedCardWithHolderEmpty());
        assertEquals(400, statusCode);
    }

    //8. Отправка POST запроса платежа с пустым значением у атрибута cvc в body (остальные данные заполнены валидно) на http://localhost:9999/payment
    //      Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем владелец попадает в бд, со статусом одобрено
    @DisplayName("TestAPIPay№ 8 Payment For Services With CVC Empty Field")
    void paymentForServicesWithCardCVCEmpty() {
        int statusCode = APIHelper.getRequestStatusCodePayment(CVCHelper.approvedCardWithCVCEmpty());
        assertEquals(400, statusCode);
    }
}

