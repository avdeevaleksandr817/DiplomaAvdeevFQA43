package ru.netology.test.ui;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.helpers.DataGenerator;
import ru.netology.page.MainPage;
import ru.netology.page.PayPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;

public class TestUIAllFields {
    static MainPage mainPage = new MainPage();
    PayPage payPage = new PayPage();

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");

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
    public void cardPaymentHappyPath() throws InterruptedException {
        mainPage.choosePaymentCard();//выбрать оплату по карте
        TimeUnit.SECONDS.sleep(10);//ожидание
        payPage.fillCardData(DataGenerator.generateDataWithApprovedCard());//форму заполнить валидно картой с согласием

        payPage.shouldNotNotification();//отсутствие сообщения при валидном заполнении
        payPage.shouldSuccessNotification();//сообщение одобрения банком
    }

    //    Отказ в оплате тура "Путешествие дня" при валидном заполнение полей формы "Оплата по карте" по declined карте
    //    (номер карты заполнен с пробелами после каждых 4 символов) Ожидаемый результат: появление сообщения об отказе в оплате тура
    @Test//Баг карта с отказом выдает согласие
    @DisplayName("Test№ 4 CardPaymentSadPath")
    public void cardPaymentSadPath() throws InterruptedException {
        mainPage.choosePaymentCard();//выбрать оплату по карте
        TimeUnit.SECONDS.sleep(10);//ожидание
        payPage.fillCardData(DataGenerator.generateDataWithDeclineCard());//форму заполнить валидно картой с отказом

        payPage.shouldNotNotification();//отсутствие сообщения при валидном заполнении
        payPage.shouldFailureNotification();//отказ банком
    }

    //    Успешная оплата в кредит тура "Путешествие дня" при валидном заполнение полей формы "Кредит по данным карты" по действующей карте
    //    (номер карты заполнен с пробелами после каждых 4 символов) Ожидаемый результат: появление сообщения об успешной взятие кредита
    @Test
    @DisplayName("Test№ 5 Payment On Credit Happy Path")
    public void paymentOnCreditHappyPath() throws InterruptedException {
        mainPage.chooseCreditOnCard();//выбрать кредит по карте
        TimeUnit.SECONDS.sleep(10);//ожидание
        payPage.fillCardData(DataGenerator.generateDataWithApprovedCard());//форму заполнить валидно картой с согласием

        payPage.shouldSuccessNotification();//сообщение одобрения банком
    }

    //    Отказ в кредите на покупку тура "Путешествие дня" при валидном* заполнение полей формы "Кредит по данным карты" по declined карте
    //    (номер карты заполнен с пробелами после каждых 4 символов) Ожидаемый результат: появление сообщения об отказе во взятие кредита
    @Test//Баг по карте с отказом банк выдает согласие
    @DisplayName("Test№ 6 paymentOnCreditSadPath")
    public void paymentOnCreditSadPath() throws InterruptedException {
        mainPage.chooseCreditOnCard();//выбрать кредит по карте
        TimeUnit.SECONDS.sleep(10);//ожидание
        payPage.fillCardData(DataGenerator.generateDataWithDeclineCard());//форму заполнить валидно картой с отказом

        payPage.shouldFailureNotification();//отказ банком
    }

    //    Заполнение всех полей валидными* данными формы "Оплата по карте" тура "Путешествие дня" с последующим переключением на форму
    //    "Кредит по данным карты" Ожидаемый результат: форма переключиться, поля останутся заполненными теми же данными
    @Test//Баг форма очищается
    @DisplayName("Test№ 7 Switching from a completed card payment form to a credit, card data is not reset")
    public void switchingCardPaymentToCreditDataNotResetData() throws InterruptedException {
        mainPage.choosePaymentCard();//выбрать оплату по карте
        TimeUnit.SECONDS.sleep(10);//ожидание
        payPage.fillCardData(DataGenerator.generateDataWithApprovedCard());//форму заполнить валидно картой с согласием
        mainPage.chooseCreditOnCard();//выбрать кредит по карте
        payPage.continueButton.click();

        payPage.shouldSuccessNotification();//сообщение одобрения банком
    }

    //    Заполнение всех полей валидными* данными формы "Кредит по данным карты" тура "Путешествие дня" с последующим переключением
    //    на форму "Оплата по карте" Ожидаемый результат: форма переключиться, поля останутся заполненными теми же данными
    @Test//Баг форма очищается
    @DisplayName("Test№ 8 Switching from credit according to card data to Payment by card without data reset")
    public void switchingCreditToCardPaymentNotResetData() throws InterruptedException {
        mainPage.chooseCreditOnCard();//выбрать оплату по карте
        TimeUnit.SECONDS.sleep(10);//ожидание
        payPage.fillCardData(DataGenerator.generateDataWithApprovedCard());//форму заполнить валидно картой с согласием
        mainPage.choosePaymentCard();//выбрать кредит по карте
        payPage.continueButton.click();

        payPage.shouldSuccessNotification();//сообщение одобрения банком
    }

    //    Оставление всех полей пустыми Ожидаемый результат: под всеми полями появиться предупреждение об пустом поле, невозможно отправить форму
    @Test//Баг надписи не соответствуют
    @DisplayName("Test№ 9 All field empty should Empty Field Notification")
    public void shouldNotificationEmptyField() throws InterruptedException {
        mainPage.choosePaymentCard();//выбрать оплату по карте
        TimeUnit.SECONDS.sleep(10);//ожидание
        payPage.fillCardData(DataGenerator.CardEmptyField());


        payPage.shouldEmptyFieldNotification();
        payPage.shouldImproperFormatNotificationHidden();
    }
}