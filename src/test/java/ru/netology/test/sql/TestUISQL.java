package ru.netology.test.sql;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.helpers.DataGenerator;
import ru.netology.helpers.SQLHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PayPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestUISQL {
    MainPage mainPage = new MainPage();
    PayPage payPage = new PayPage();

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");

    }

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

    //ОПЛАТА КАРТОЙ

    //    Успешная оплата тура "Путешествие дня" при валидном заполнение полей формы "Оплата по карте" по действующей карте
    //    (номер карты заполнен с пробелами после каждых 4 символов) Ожидаемый результат: появление сообщения об успешной оплате тура
    @Test
    @DisplayName("TestUISQL № 1 CardPaymentHappyPath")
    public void cardPaymentHappyPath() throws InterruptedException {
        mainPage.choosePaymentCard();//выбрать оплату по карте
        TimeUnit.SECONDS.sleep(5);//ожидание
        payPage.fillCardData(DataGenerator.generateDataWithApprovedCard());//форму заполнить валидно картой с согласием

        TimeUnit.SECONDS.sleep(10);//ожидание
        var paymentCardData = SQLHelper.getPaymentCardData();

        assert paymentCardData != null;
        assertEquals("APPROVED", paymentCardData.getStatus());//"УТВЕРЖДЕНО", Данные платежной карты. Получить Статус из БД
    }

    //     при валидном заполнение полей формы "Оплата по карте" по declined карте
    //    Ожидаемый результат: появление сообщения в БД с "DECLINED", Данные платежной карты
    @Test//OK
    @DisplayName("TestUISQL № 2 CardPaymentSadPath")
    public void cardPaymentSadPath() throws InterruptedException {
        mainPage.choosePaymentCard();//выбрать оплату по карте
        TimeUnit.SECONDS.sleep(5);//ожидание
        payPage.fillCardData(DataGenerator.generateDataWithDeclineCard());//форму заполнить валидно картой с отказом

        TimeUnit.SECONDS.sleep(10);//ожидание
        var paymentCardData = SQLHelper.getPaymentCardData();

        assert paymentCardData != null;
        assertEquals("DECLINED", paymentCardData.getStatus());//"DECLINED", Данные платежной карты. Получить Статус из БД
    }

    //     при пустых полях формы "Оплата по карте"
    //    Ожидаемый результат: нет записей в БД
    @Test
    @DisplayName("TestUISQL № 3 CardPaymentEmptyPath")
    public void cardPaymentEmptyPath() throws InterruptedException {
        mainPage.choosePaymentCard();//выбрать оплату по карте
        TimeUnit.SECONDS.sleep(5);//ожидание

        payPage.fillCardData(DataGenerator.CardEmptyFields());//пустая форма
        TimeUnit.SECONDS.sleep(10);//ожидание

        var paymentCardData = SQLHelper.getPaymentCardData();
        assertNull(paymentCardData);// Получить Статус из БД нет записей
    }

    //КРЕДИ ПО ДАННЫМ КАРТЫ

    //    Успешная оплата тура "Путешествие дня" при валидном заполнение полей формы "Оплата по карте" по действующей карте
    //    (номер карты заполнен с пробелами после каждых 4 символов) Ожидаемый результат: появление сообщения об успешной оплате тура
    @Test
    @DisplayName("TestUISQL № 4 credit On Card Happy Path")
    public void creditOnCardHappyPath() throws InterruptedException {
        mainPage.chooseCreditOnCard();//выбрать кредит по карте
        TimeUnit.SECONDS.sleep(5);//ожидание
        payPage.fillCardData(DataGenerator.generateDataWithApprovedCard());//форму заполнить валидно картой с согласием

        TimeUnit.SECONDS.sleep(10);//ожидание
        var creditOnCardData = SQLHelper.getCreditCardData();

        assert creditOnCardData != null;
        assertEquals("APPROVED", creditOnCardData.getStatus());//"УТВЕРЖДЕНО", Данные платежной карты. Получить Статус из БД
    }

    //     при валидном заполнение полей формы "Оплата по карте" по declined карте
    //    Ожидаемый результат: появление сообщения в БД с "DECLINED", Данные платежной карты
    @Test//OK
    @DisplayName("TestUISQL № 5 credit On Card Sad Path")
    public void creditOnCardSadPath() throws InterruptedException {
        mainPage.chooseCreditOnCard();//выбрать кредит по карте
        TimeUnit.SECONDS.sleep(5);//ожидание
        payPage.fillCardData(DataGenerator.generateDataWithDeclineCard());//форму заполнить валидно картой с отказом

        TimeUnit.SECONDS.sleep(10);//ожидание
        var creditOnCardData = SQLHelper.getCreditCardData();

        assert creditOnCardData != null;
        assertEquals("DECLINED", creditOnCardData.getStatus());//"DECLINED", Данные платежной карты. Получить Статус из БД
    }

    //     при пустых полях формы "Оплата по карте"
    //    Ожидаемый результат: нет записей в БД
    @Test
    @DisplayName("TestUISQL № 6 credit On Card Empty Path")
    public void creditOnCardEmptyPath() throws InterruptedException {
        mainPage.chooseCreditOnCard();//выбрать кредит по карте
        TimeUnit.SECONDS.sleep(5);//ожидание

        payPage.fillCardData(DataGenerator.CardEmptyFields());//пустая форма
        TimeUnit.SECONDS.sleep(10);//ожидание

        var creditOnCardData = SQLHelper.getCreditCardData();
        assertNull(creditOnCardData);// Получить Статус из БД нет записей
    }
}
