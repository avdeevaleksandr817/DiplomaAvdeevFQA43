package ru.netology.test.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.helpers.APIHelper;
import ru.netology.helpers.DataGenerator;
import ru.netology.helpers.SQL;
import ru.netology.helpers.helpers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAPICredit {

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

    @Test//OK
    @DisplayName("TestAPICredit№ 1 add Credit data to the database with a valid APPROVED card via API")
    void shouldSuccessTransactionWithApprovedCreditCardViaAPI() {
        //var cardInfo = Генератор данных. генерировать данные с утвержденной картой();
        var cardInfo = DataGenerator.generateDataWithApprovedCard();
        //APIПомощник. создать платеж (информация о карте);
        APIHelper.createCredit(cardInfo);

        //var Данные платежной карты = SQL. получить данные платежной карты();
        var creditCardData = SQL.getCreditCardData();
        assert creditCardData != null;
        //assertEquals("УТВЕРЖДЕНО", Данные платежной карты. Получить Статус());
        assertEquals("APPROVED", creditCardData.getStatus());
    }

    //2. Отправка POST запроса платежа с валидно заполненным body и Карта DECLINED на http://localhost:9999/payment
    //   Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test//OK
    @DisplayName("TestAPICredit№ 2 add Credit data to the database with a valid DECLINED card via API")
    void transactionWithDeclinedCreditCardMustNotBeMadeThroughTheAPI() {
        //var cardInfo = Генератор данных. генерировать данные с отклоненной картой();
        var cardInfo = DataGenerator.generateDataWithDeclineCard();
        //APIПомощник. создать платеж (информация о карте);
        APIHelper.createCredit(cardInfo);

        //var Данные платежной карты = SQL. получить данные платежной карты();
        var creditCardData = SQL.getCreditCardData();
        assert creditCardData != null;//Данные платежной карты не могут быть равны нулю
        //assertEquals("ОТКЛОНЕНО", Данные платежной карты. Получить Статус());
        assertEquals("DECLINED", creditCardData.getStatus());
    }

    //Отправка POST запроса на кредит с валидно* заполненным body и Карта APPROVED на http://localhost:9999/credit
    //Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test//OK
    @DisplayName("TestAPICredit№ 3 Credit for services with an APPROVED card")
    void creditForServicesWithApprovedCard() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(DataGenerator.generateDataWithApprovedCard());
        assertEquals(200, statusCode);
    }

    //2. Отправка POST запроса на кредит с валидно заполненным body и Карта Declined на http://localhost:9999/credit
    //   Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test//OK
    @DisplayName("TestAPICredit№ 4 Credit For Services With Declined Card")
    void creditForServicesWithCard() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(DataGenerator.generateDataWithDeclineCard());
        assertEquals(200, statusCode);
    }

    //3. Отправка POST запроса с пустым body на http://localhost:9999/credit
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//OK
    @DisplayName("TestAPICredit№ 5 Credit For Services With Card Empty Fields")
    void creditForServicesWithCardEmptyFields() {
        int statusCode = APIHelper.getRequestStatusCodePayment(DataGenerator.CardEmptyField());
        assertEquals(500, statusCode);
    }

    //4. Отправка POST запроса на кредит с пустым значением у атрибута number в body (остальные данные заполнены валидно) на http://localhost:9999/credit
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//OK
    @DisplayName("TestAPICredit№ 6 Credit For Services With Card Empty Fields")
    void creditForServicesWithCardEmptyFieldNumber() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(NumberHelper.dataWithCardNumberEmpty());
        assertEquals(500, statusCode);
    }

    //5. Отправка POST запроса на кредит с пустым значением у атрибута month в body (остальные данные заполнены валидно) на http://localhost:9999/credit
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем месяц попадает в бд, со статусом одобрено
    @DisplayName("TestAPICredit№ 7 Credit For Services With Month Empty Field")
    void creditForServicesWithCardEmptyFieldMonth() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(MonthHelper.approvedCardWithMonthEmptyField());
        assertEquals(500, statusCode);
    }

    //6. Отправка POST запроса на кредит с пустым значением у атрибута year в body (остальные данные заполнены валидно) на http://localhost:9999/credit
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем год попадает в бд, со статусом одобрено
    @DisplayName("TestAPICredit№ 8 Credit For Services With Year Empty Field")
    void creditForServicesWithCardYearEmptyField() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(YearHelper.approvedCardWithYearEmptyField());
        assertEquals(500, statusCode);
    }

    //7. Отправка POST запроса на кредит с пустым значением у атрибута holder в body (остальные данные заполнены валидно) на http://localhost:9999/credit
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем владелец попадает в бд, со статусом одобрено
    @DisplayName("TestAPICredit№ 9 Credit For Services With Holder Empty Field")
    void creditForServicesWithCardHolderEmpty() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(HolderHelper.approvedCardWithHolderEmpty());
        assertEquals(500, statusCode);
    }

    //8. Отправка POST запроса на кредит с пустым значением у атрибута cvc в body (остальные данные заполнены валидно) на http://localhost:9999/credit
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем владелец попадает в бд, со статусом одобрено
    @DisplayName("TestAPICredit№ 10 Credit For Services With CVC Empty Field")
    void creditForServicesWithCardCVCEmpty() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(CVCHelper.approvedCardWithCVCEmpty());
        assertEquals(500, statusCode);
    }
}
