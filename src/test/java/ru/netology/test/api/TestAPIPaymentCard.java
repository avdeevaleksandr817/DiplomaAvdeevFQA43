package ru.netology.test.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.helpers.APIHelper;
import ru.netology.helpers.DataGenerator;
import ru.netology.helpers.SQL;
import ru.netology.helpers.helpers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAPIPaymentCard {

//    BeforeAll
//    static void setUpAll() {
//        SelenideLogger.addListener("allure", new AllureSelenide());
//    }

    @BeforeEach
    public void cleanTable() {
        SQL.cleanDatabase();
    }

//    @AfterAll
//    static void tearDownAll() {
//        SelenideLogger.removeListener("allure");
//    }

    @Test
    @DisplayName("TestAPIPay№ 1 add payment data to the database with a valid card APPROVED via API")
    void shouldSuccessTransactionWithApprovedPaymentCardViaAPI() {
        //var cardInfo = Генератор данных. генерировать данные с утвержденной картой();
        var cardInfo = DataGenerator.generateDataWithApprovedCard();
        //APIПомощник. создать платеж (информация о карте);
        APIHelper.createPayment(cardInfo);

        //var Данные платежной карты = SQL. получить данные платежной карты();
        var paymentCardData = SQL.getPaymentCardData();
        assert paymentCardData != null;
        //assertEquals("УТВЕРЖДЕНО", Данные платежной карты. Получить Статус());
        assertEquals("APPROVED", paymentCardData.getStatus());
    }

    //2. Отправка POST запроса платежа с валидно заполненным body и Карта DECLINED на http://localhost:9999/payment
    //   Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test
    @DisplayName("TestAPIPay№ 2 add payment data to the database with a valid card DECLINED via API")
    void transactionWithDeclinedPaymentCardMustNotBeMadeThroughTheAPI() {
        //var cardInfo = Генератор данных. генерировать данные с отклоненной картой();
        var cardInfo = DataGenerator.generateDataWithDeclineCard();
        //APIПомощник. создать платеж (информация о карте);
        APIHelper.createPayment(cardInfo);

        //var Данные платежной карты = SQL. получить данные платежной карты();
        var paymentCardData = SQL.getPaymentCardData();
        assert paymentCardData != null;//Данные платежной карты не могут быть равны нулю
        //assertEquals("ОТКЛОНЕНО", Данные платежной карты. Получить Статус());
        assertEquals("DECLINED", paymentCardData.getStatus());
    }

    //1. Отправка POST запроса платежа с валидно заполненным body и Карта APPROVED на http://localhost:9999/payment
    //      Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test
    @DisplayName("TestAPIPay№ 3 Payment for services with an APPROVED card")
    void paymentForServicesWithApprovedCard() {
        int statusCode = APIHelper.getRequestStatusCodePayment(DataGenerator.generateDataWithApprovedCard());
        assertEquals(200, statusCode);
    }

    //2. Отправка POST запроса платежа с валидно заполненным body и Карта DECLINED на http://localhost:9999/payment
    //      Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test
    @DisplayName("TestAPIPay№ 4 Payment For Services With Declined Card")
    void paymentForServicesWithCard() {
        int statusCode = APIHelper.getRequestStatusCodePayment(DataGenerator.generateDataWithDeclineCard());
        assertEquals(200, statusCode);
    }

    //3. Отправка POST запроса с пустым body на http://localhost:9999/payment
    //      Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test
    @DisplayName("TestAPIPay№ 5 Payment For Services With Card Empty Fields")
    void paymentForServicesWithCardEmptyFields() {
        int statusCode = APIHelper.getRequestStatusCodePayment(DataGenerator.CardEmptyField());
        assertEquals(500, statusCode);
    }

    //4. Отправка POST запроса платежа с пустым значением у атрибута number в body (остальные данные заполнены валидно) на http://localhost:9999/payment
    //      Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test
    @DisplayName("TestAPIPay№ 6 Payment For Services With Card Empty Fields")
    void paymentForServicesWithCardEmptyFieldNumber() {
        int statusCode = APIHelper.getRequestStatusCodePayment(NumberHelper.dataWithCardNumberEmpty());
        assertEquals(500, statusCode);
    }

    //5. Отправка POST запроса платежа с пустым значением у атрибута month в body (остальные данные заполнены валидно) на http://localhost:9999/payment
    //      Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем месяц попадает в бд, со статусом одобрено
    @DisplayName("TestAPIPay№ 7 Payment For Services With Month Empty Field")
    void paymentForServicesWithCardEmptyFieldMonth() {
        int statusCode = APIHelper.getRequestStatusCodePayment(MonthHelper.approvedCardWithMonthEmptyField());
        assertEquals(500, statusCode);
    }

    //6. Отправка POST запроса платежа с пустым значением у атрибута year в body (остальные данные заполнены валидно) на http://localhost:9999/payment
    //      Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем год попадает в бд, со статусом одобрено
    @DisplayName("TestAPIPay№ 8 Payment For Services With Year Empty Field")
    void paymentForServicesWithCardYearEmptyField() {
        int statusCode = APIHelper.getRequestStatusCodePayment(YearHelper.approvedCardWithYearEmptyField());
        assertEquals(500, statusCode);
    }

    //7. Отправка POST запроса платежа с пустым значением у атрибута holder в body (остальные данные заполнены валидно) на http://localhost:9999/payment
    //      Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем владелец попадает в бд, со статусом одобрено
    @DisplayName("TestAPIPay№ 9 Payment For Services With Holder Empty Field")
    void paymentForServicesWithCardHolderEmpty() {
        int statusCode = APIHelper.getRequestStatusCodePayment(HolderHelper.approvedCardWithHolderEmpty());
        assertEquals(500, statusCode);
    }

    //8. Отправка POST запроса платежа с пустым значением у атрибута cvc в body (остальные данные заполнены валидно) на http://localhost:9999/payment
    //      Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем владелец попадает в бд, со статусом одобрено
    @DisplayName("TestAPIPay№ 10 Payment For Services With CVC Empty Field")
    void paymentForServicesWithCardCVCEmpty() {
        int statusCode = APIHelper.getRequestStatusCodePayment(CVCHelper.approvedCardWithCVCEmpty());
        assertEquals(500, statusCode);
    }
}

