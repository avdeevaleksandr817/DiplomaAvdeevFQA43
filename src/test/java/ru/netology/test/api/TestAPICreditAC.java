package ru.netology.test.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.helpers.APIHelper;
import ru.netology.helpers.DataGenerator;
import ru.netology.helpers.SQLHelper;
import ru.netology.helpers.helpers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAPICreditAC {
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

    //Отправка POST запроса на кредит с валидно* заполненным body и Карта APPROVED на http://localhost:9999/credit
    //Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test//OK
    @DisplayName("APICreditAC № 1 Credit for services with an APPROVED card")
    void creditForServicesWithApprovedCard() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(DataGenerator.generateDataWithApprovedCard());
        assertEquals(200, statusCode);
    }

    //2. Отправка POST запроса на кредит с валидно заполненным body и Карта Declined на http://localhost:9999/credit
    //   Ожидаемый результат: статус 200, появление соответствующей записей в БД
    @Test//OK
    @DisplayName("TestAPICredit№ 2 Credit For Services With DECLINED Card")
    void creditForServicesWithDeclinedCard() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(DataGenerator.generateDataWithDeclineCard());
        assertEquals(200, statusCode);
    }

    //3. Отправка POST запроса с пустым body на http://localhost:9999/credit
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//OK
    @DisplayName("APICreditAC № 3 Credit For Services With Empty Fields")
    void creditForServicesWithCardEmptyFields() {
        int statusCode = APIHelper.getRequestStatusCodePayment(DataGenerator.CardEmptyFields());
        assertEquals(400, statusCode);
    }

    //4. Отправка POST запроса на кредит с пустым значением у атрибута number в body (остальные данные заполнены валидно) на http://localhost:9999/credit
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//OK
    @DisplayName("APICreditAC № 4 Credit For Services With APPROVED Card Empty Field Number")
    void creditForServicesWithCardEmptyFieldNumber() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(NumberHelper.dataWithCardNumberEmpty());
        assertEquals(400, statusCode);
    }

    //5. Отправка POST запроса на кредит с пустым значением у атрибута month в body (остальные данные заполнены валидно) на http://localhost:9999/credit
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем месяц попадает в бд, со статусом одобрено
    @DisplayName("APICreditAC № 5 Credit For Services With APPROVED Card Month Empty Field")
    void creditForServicesWithCardEmptyFieldMonth() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(MonthHelper.approvedCardWithMonthEmptyField());
        assertEquals(400, statusCode);
    }

    //6. Отправка POST запроса на кредит с пустым значением у атрибута year в body (остальные данные заполнены валидно) на http://localhost:9999/credit
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем год попадает в бд, со статусом одобрено
    @DisplayName("APICreditAC № 6 Credit For Services With APPROVED Card Year Empty Field")
    void creditForServicesWithCardYearEmptyField() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(YearHelper.approvedCardWithYearEmptyField());
        assertEquals(400, statusCode);
    }

    //7. Отправка POST запроса на кредит с пустым значением у атрибута holder в body (остальные данные заполнены валидно) на http://localhost:9999/credit
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем владелец попадает в бд, со статусом одобрено
    @DisplayName("APICreditAC № 7 Credit For Services With APPROVED Card Holder Empty Field")
    void creditForServicesWithCardHolderEmpty() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(HolderHelper.approvedCardWithHolderEmpty());
        assertEquals(400, statusCode);
    }

    //8. Отправка POST запроса на кредит с пустым значением у атрибута cvc в body (остальные данные заполнены валидно) на http://localhost:9999/credit
    //   Ожидаемый результат: статус 400, в БД не появляются новые записи
    @Test//БАГ карта с пустым полем владелец попадает в бд, со статусом одобрено
    @DisplayName("APICreditAC № 8 Credit For Services With APPROVED Card CVC Empty Field")
    void creditForServicesWithCardCVCEmpty() {
        int statusCode = APIHelper.getRequestStatusCodeCredit(CVCHelper.approvedCardWithCVCEmpty());
        assertEquals(400, statusCode);
    }
}
