package ru.netology.test.ui;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.helpers.SQLHelper;
import ru.netology.helpers.helpers.MonthHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PayPage;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.open;

public class TestUiMonthField {

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

    //    Заполнение поля "Месяц" 3 цифрами, остальные поля заполнены валидно* в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: обрезание последней цифры в поле "Месяц", появление сообщения об успешной оплате тура
    @Test//Баг можно ввести больше 12 месяцев
    @DisplayName("Month Test№ 1 approved Card With Month Of 3numbers")
    public void approvedCardWithMonthOf3numbers() {
        payPage.fillCardData(MonthHelper.approvedCardWithMonthOf3numbers());

        payPage.shouldInvalidExpiredDateNotification();//Неверно указан срок действия карты
    }

    //    Заполнение поля "Месяц" значением 00, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Месяц" появиться предупреждение об невалидном значение
    @Test
    @DisplayName("Month Test№ 2 approved Card With Month Of 00 numbers")
    public void approvedCardWithMonthOf00numbers() {
        payPage.fillCardData(MonthHelper.approvedCardWithMonthOf00numbers());

        payPage.shouldEmptyFieldNotification();//видимое Сообщение Поле обязательно для заполнения
    }

    //    Заполнение поля "Месяц" значением из 1 цифры, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: автодополнение нулем вначале в поле "Месяц", появление сообщения об успешной оплате тура
    @Test//Баг нет автодополнения
    @DisplayName("Month Test№ 3 approved Card With Month Of 1 number")
    public void approvedCardWithMonthOf1numbers() {
        payPage.fillCardData(MonthHelper.approvedCardWithMonthOf1numbers());

        payPage.shouldSuccessNotification();//Операция одобрена Банком
    }

    //    Заполнение поля "Месяц" значением 12, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: появление сообщения об успешной оплате тура
    @Test//OK
    @DisplayName("Month Test№ 4 approved Card With Month Of 12 number")
    public void approvedCardWithMonthOf12numbers() {
        payPage.fillCardData(MonthHelper.approvedCardWithMonthOf12numbers());

        payPage.shouldSuccessNotification();//Операция одобрена Банком
    }

    //    Заполнение поля "Месяц" значением 13, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Месяц" появиться предупреждение об невалидном значение
    @Test//OK
    @DisplayName("Month Test№ 5 approved Card With Month Of 13 number")
    public void approvedCardWithMonthOf13numbers() {
        payPage.fillCardData(MonthHelper.approvedCardWithMonthOf13numbers());

        payPage.shouldInvalidExpiredDateNotification();//Неверно указан срок действия карты
        payPage.shouldEmptyFieldNotification();//видимое Сообщение Поле обязательно для заполнения
    }

    //    Заполнение поля "Месяц" двумя рандомными спецсимволами, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Месяц" появиться предупреждение об невалидном значение
    @Test//OK
    @DisplayName("Month Test№ 6 approved Card With Month Of 2 symbols")
    public void approvedCardWithMonthOf2symbols() {
        payPage.fillCardData(MonthHelper.approvedCardWithMonthOf2symbols());

        payPage.shouldImproperFormatNotification();//отображение сообщения Неверный формат
    }

    //    Заполнение поля "Месяц" буквами латиницы, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Месяц" появиться предупреждение об недопустимых символах
    @Test//OK
    @DisplayName("Month Test№ 7 approved Card With Month Of 2 Latin")
    public void approvedCardWithMonthOf2Latin() {
        payPage.fillCardData(MonthHelper.approvedCardWithMonthOf2Latin());

        payPage.shouldImproperFormatNotification();//отображение сообщения Неверный формат
    }

    //    Заполнение поля "Месяц" буквами кириллицы, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Месяц" появиться предупреждение об недопустимых символа
    @Test//OK
    @DisplayName("Month Test№ 8 approved Card With Month Of 2 Cyrillic")
    public void approvedCardWithMonthOf2Cyrillic() {
        payPage.fillCardData(MonthHelper.approvedCardWithMonthOf2Cyrillic());

        payPage.shouldImproperFormatNotification();//отображение сообщения Неверный формат
    }
}
