package ru.netology.test.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.helpers.helpers.HolderHelper;

import java.util.concurrent.TimeUnit;

public class TestUiHolderField extends TestUIAllFields {

    @BeforeEach
    void setUpChoosePaymentCard() throws InterruptedException {
        mainPage.choosePaymentCard();//выбрать оплату по карте
        TimeUnit.SECONDS.sleep(10);//ожидание
    }


    //    Заполнение поля "Владелец" валидными данными с дефисом, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: появление сообщения об успешной оплате тура
    @Test//ОК
    @DisplayName("Holder Test№ 1 Approved Card With Double Last Name")
    public void approvedCardWithDoubleLastName() {
        payPage.fillCardData(HolderHelper.approvedCardWithDoubleLastName());

        payPage.shouldSuccessNotification();
    }

    //    Заполнение поля "Владелец" в нижнем регистре, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: автозаполнение поля "Владелец" в верхнем регистре
    @Test//БАГ возможен нижний регистр
    @DisplayName("Holder Test№ 2 Approved Card With Holder Lower Case")
    public void approvedCardWithHolderLowerCase() {
        payPage.fillCardData(HolderHelper.approvedCardWithHolderLowerCase());//пользователь в нижнем регистре

        payPage.shouldImproperFormatNotification();//неверный формат
    }


    //    Заполнение поля "Владелец" с пробелами в начале и в конце, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: авто удаление лишних символов в поле "Владелец"
    @Test//ОК
    @DisplayName("Holder Test№ 3 Approved Card With Holder Start And End Spaces")
    public void approvedCardWithHolderStartAndEndSpaces() {
        payPage.fillCardData(HolderHelper.approvedCardWithHolderStartAndEndSpaces());//пользователь пробелы в начале и конце

        payPage.shouldSuccessNotification();
    }


    //    Заполнение поля "Владелец" с дефисами в начале и в конце, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: авто удаление лишних символов в поле "Владелец"
    @Test//Баг возможны дефисы в начале и конце
    @DisplayName("Holder Test№ 4 Approved Card With Holder Start And End Hyphens")
    public void approvedCardWithHolderStartAndEndHyphens() {
        payPage.fillCardData(HolderHelper.approvedCardWithHolderStartAndEndHyphens());//пользователь дефисы в начале и конце

        payPage.shouldImproperFormatNotification();
    }

    //    Заполнение поля "Владелец" цифрами, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Владелец" появиться предупреждение об невалидном значение
    @Test//Баг возможны возможно ввести цифры
    @DisplayName("Holder Test№ 5 Approved Card With Holder Numbers")
    public void approvedCardWithHolderNumbers() {
        payPage.fillCardData(HolderHelper.approvedCardWithHolderNumbers());//пользователь цифры

        payPage.shouldImproperFormatNotification();//неверный формат
    }


    //    Заполнение поля "Владелец" максимальным количеством букв на латинице в одно слово, остальные поля заполнены валидно* в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: появление сообщения об успешной оплате тура
    @Test//Баг возможны возможно ввести 1001Letter нет ограничения по вводу
    @DisplayName("Holder Test№ 6 Approved Card With Holder 1001Letter")
    public void approvedCardWithHolder1001Letter() {
        payPage.fillCardData(HolderHelper.approvedCardWithHolder1001Letter());//пользователь 1001Letter

        payPage.shouldImproperFormatNotification();//неверный формат
    }


    //    Заполнение поля "Владелец" одной буквой на латинице, остальные поля заполнены валидно* в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: появиться предупреждение об невалидном значение, должно быть минимум 2 буквы
    @Test//Баг возможно ввести 1 букву
    @DisplayName("Holder Test№ 7 Approved Card With Holder One Letter")
    public void approvedCardWithHolderOneLetter() {
        payPage.fillCardData(HolderHelper.approvedCardWithHolderOneLetter());//пользователь одна буква

        payPage.shouldEmptyFieldNotification();//Поле обязательно для заполнения
    }


    //    Заполнение поля "Владелец" кириллицей, остальные поля заполнены валидно* в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Владелец" появиться предупреждение об невалидном значение
    @Test//Баг кириллица в поле
    @DisplayName("Holder Test№ 8 approved Card With Holder With Cyrillic Symbols")
    public void approvedCardWithHolderWithCyrillicSymbols() {
        payPage.fillCardData(HolderHelper.approvedCardWithHolderWithCyrillicSymbols());//поля заполнены валидно, владелец кириллицей

        payPage.shouldImproperFormatNotification();//видимое Сообщение Неверный формат
    }


    //    Заполнение поля "Владелец" рандомными спецсимволами, остальные поля заполнены валидно* в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Владелец" появиться предупреждение об невалидном значение
    @Test//Баг возможны спецсимволы
    @DisplayName("Holder Test№ 9 approved Card With Holder Symbols")
    public void approvedCardWithHolderSymbols() {
        payPage.fillCardData(HolderHelper.approvedCardWithHolderSymbols());//поля заполнены валидно, владелец спецсимволами

        payPage.shouldImproperFormatNotification();//видимое Сообщение Неверный формат
    }
}
