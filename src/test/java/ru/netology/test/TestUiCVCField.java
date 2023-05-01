package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.helpers.helpers.CVCHelper;

import java.util.concurrent.TimeUnit;

public class TestUiCVCField extends TestUIAllFields {

    @BeforeEach
    void setUpChoosePaymentCard() throws InterruptedException {
        mainPage.choosePaymentCard();//выбрать оплату по карте
        TimeUnit.SECONDS.sleep(10);//ожидание
    }


    //    Заполнение поля "CVC/CVV" 1 рандомной цифрой, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "CVC/CVV" появление сообщения об недопустимой длине
    @Test//Баг сообщения
    @DisplayName("CVC Test№ 1 approved Card With CVC With 1 Digit")
    public void approvedCardWithCVCWith1Digit() {
        payPage.fillCardData(CVCHelper.approvedCardWithCVCWith1Digit());//поля заполнены валидно, номером CVC 1 цифра

        payPage.shouldEmptyFieldNotification();//видимое Сообщение поле обязательно для заполнения
    }


    //    Заполнение поля "CVC/CVV" 4 рандомными цифрами, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: 4 цифра в поле "CVC/CVV" обрезается
    @Test//ОК
    @DisplayName("CVC Test№ 2 approved Card With CVC With 4 Digit")
    public void approvedCardWithCVCWith4Digit() {
        payPage.fillCardData(CVCHelper.approvedCardWithCVCWith4Digit());//поля заполнены валидно, номером CVC 4 цифра

        payPage.shouldSuccessNotification();//видимое Сообщение успешно CVC обрезан до 3
    }


    //    Заполнение поля "CVC/CVV" 3 рандомными буквами латиницы, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "CVC/CVV" появление сообщения об невалидном значение
    @Test//ОК
    @DisplayName("CVC Test№ 3 approved Card With CVC With 3 Latin")
    public void approvedCardWithCVCWith3Latin() {
        payPage.fillCardData(CVCHelper.approvedCardWithCVCWith3Latin());//поля заполнены валидно, номером CVC 3Latin

        payPage.shouldImproperFormatNotification();//видимое отображение сообщения Неверный формат
    }


    //    Заполнение поля "CVC/CVV" 3 рандомными буквами кириллицы, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "CVC/CVV" появление сообщения об невалидном значение
    @Test//ОК
    @DisplayName("CVC Test№ 4 approved Card With CVC With 3 Cyrillic")
    public void approvedCardWithCVCWith3Cyrillic() {
        payPage.fillCardData(CVCHelper.approvedCardWithCVCWith3Cyrillic());//поля заполнены валидно, номером CVC 3 Cyrillic

        payPage.shouldImproperFormatNotification();//видимое отображение сообщения Неверный формат
    }


    //    Заполнение поля "CVC/CVV" 3 рандомными спецсимволами, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "CVC/CVV" появление сообщения об невалидном значение
    @Test//ОК
    @DisplayName("CVC Test№ 5 approved Card With CVC With 3 Symbols")
    public void approvedCardWithCVCWith3Symbols() {
        payPage.fillCardData(CVCHelper.approvedCardWithCVCWith3Symbols());//поля заполнены валидно, номером CVC 3 Symbols

        payPage.shouldImproperFormatNotification();//видимое отображение сообщения Неверный формат
    }
}
