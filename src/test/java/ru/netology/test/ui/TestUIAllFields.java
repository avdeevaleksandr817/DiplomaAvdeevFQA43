package ru.netology.test.ui;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.helpers.APIHelper;
import ru.netology.helpers.DataGenerator;
import ru.netology.helpers.SQLHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PayPage;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUIAllFields {
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
    void setUpChoosePaymentCard() throws InterruptedException {
        mainPage.choosePaymentCard();//выбрать оплату по карте
        TimeUnit.SECONDS.sleep(6);//ожидание
    }

    @BeforeEach
    public void cleanTable() {
        SQLHelper.cleanDatabase();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    //    Тестирование UI
    //успешный переход на оплату картой
    @Test
    @DisplayName("Test№ 1 successful transition to card payment")
    void shouldGetPaymentPage() {
        val mainPage = new MainPage();
        mainPage.choosePaymentCard();
    }

    //успешный переход на оформление кредита
    @Test
    @DisplayName("Test№ 2 successful transition to credit card ")
    void shouldGetCreditPage() {
        val mainPage = new MainPage();
        mainPage.chooseCreditOnCard();
    }


    //    Успешная оплата тура "Путешествие дня" при валидном заполнение полей формы "Оплата по карте" по действующей карте
    //    (номер карты заполнен с пробелами после каждых 4 символов) Ожидаемый результат: появление сообщения об успешной оплате тура
    @Test
    @DisplayName("Test№ 3 CardPaymentHappyPath")
    public void cardPaymentHappyPath() {

        payPage.fillCardData(DataGenerator.generateDataWithApprovedCard());//форму заполнить валидно картой с согласием

        payPage.shouldNotNotification();//отсутствие сообщения при валидном заполнении
        payPage.shouldSuccessNotification();//сообщение одобрения банком
    }

    //    Отказ в оплате тура "Путешествие дня" при валидном заполнение полей формы "Оплата по карте" по declined карте
    //    (номер карты заполнен с пробелами после каждых 4 символов) Ожидаемый результат: появление сообщения об отказе в оплате тура
    @Test//Баг карта с отказом выдает согласие
    @DisplayName("Test№ 4 CardPaymentSadPath")
    public void cardPaymentSadPath() {

        payPage.fillCardData(DataGenerator.generateDataWithDeclineCard());//форму заполнить валидно картой с отказом

        payPage.shouldNotNotification();//отсутствие сообщения при валидном заполнении
        payPage.shouldFailureNotification();//отказ банком
    }

    //    Успешная оплата в кредит тура "Путешествие дня" при валидном заполнение полей формы "Кредит по данным карты" по действующей карте
    //    (номер карты заполнен с пробелами после каждых 4 символов) Ожидаемый результат: появление сообщения об успешной взятие кредита
    @Test
    @DisplayName("Test№ 5 Payment On Credit Happy Path")
    public void paymentOnCreditHappyPath() {

        payPage.fillCardData(DataGenerator.generateDataWithApprovedCard());//форму заполнить валидно картой с согласием

        payPage.shouldSuccessNotification();//сообщение одобрения банком
    }

    //    Отказ в кредите на покупку тура "Путешествие дня" при валидном* заполнение полей формы "Кредит по данным карты" по declined карте
    //    (номер карты заполнен с пробелами после каждых 4 символов) Ожидаемый результат: появление сообщения об отказе во взятие кредита
    @Test//Баг по карте с отказом банк выдает согласие
    @DisplayName("Test№ 6 paymentOnCreditSadPath")
    public void paymentOnCreditSadPath() {

        payPage.fillCardData(DataGenerator.generateDataWithDeclineCard());//форму заполнить валидно картой с отказом

        payPage.shouldFailureNotification();//отказ банком
    }

    //    Заполнение всех полей валидными* данными формы "Оплата по карте" тура "Путешествие дня" с последующим переключением на форму
    //    "Кредит по данным карты" Ожидаемый результат: форма переключиться, поля останутся заполненными теми же данными
    @Test//Баг форма очищается
    @DisplayName("Test№ 7 Switching from a completed card payment form to a credit, card data is not reset")
    public void switchingCardPaymentToCreditDataNotResetData() {

        payPage.fillCardData(DataGenerator.generateDataWithApprovedCard());//форму заполнить валидно картой с согласием
        mainPage.chooseCreditOnCard();//выбрать кредит по карте
        payPage.continueButton.click();

        payPage.shouldSuccessNotification();//сообщение одобрения банком
    }

    //    Заполнение всех полей валидными* данными формы "Кредит по данным карты" тура "Путешествие дня" с последующим переключением
    //    на форму "Оплата по карте" Ожидаемый результат: форма переключиться, поля останутся заполненными теми же данными
    @Test//Баг форма очищается
    @DisplayName("Test№ 8 Switching from credit according to card data to Payment by card without data reset")
    public void switchingCreditToCardPaymentNotResetData() {

        payPage.fillCardData(DataGenerator.generateDataWithApprovedCard());//форму заполнить валидно картой с согласием
        mainPage.choosePaymentCard();//выбрать кредит по карте
        payPage.continueButton.click();

        payPage.shouldSuccessNotification();//сообщение одобрения банком
    }

    //    Оставление всех полей пустыми Ожидаемый результат: под всеми полями появиться предупреждение об пустом поле, невозможно отправить форму
    @Test//Баг надписи не соответствуют
    @DisplayName("Test№ 9 All field empty should Empty Field Notification")
    public void shouldNotificationEmptyField() {

        payPage.fillCardData(DataGenerator.CardEmptyFields());

        payPage.shouldEmptyFieldNotification();
        payPage.shouldImproperFormatNotificationHidden();
    }

    //    Заполнение всех полей валидными данными после попытки отправки пустой формы, предупреждения должны исчезать
    @Test//Баг надписи не исчезают
    @DisplayName("Test№ 10 No notification about empty field below fields after entering valid data")
    public void shouldNotNotificationValidDataField() {

        payPage.continueButton.click();
        payPage.fillCardData(DataGenerator.generateDataWithApprovedCard());

        payPage.shouldNotNotification();

    }
}
