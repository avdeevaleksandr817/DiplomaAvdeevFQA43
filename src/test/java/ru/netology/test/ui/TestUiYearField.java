package ru.netology.test.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.helpers.helpers.YearHelper;

import java.util.concurrent.TimeUnit;

public class TestUiYearField extends TestUIAllFields {

    @BeforeEach
    void setUpChoosePaymentCard() throws InterruptedException {
        mainPage.choosePaymentCard();//выбрать оплату по карте
        TimeUnit.SECONDS.sleep(6);//ожидание
    }

    //    Оставление поля "Год" пустым, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Год" появиться предупреждение об пустом поле
    @Test//Баг подписи поля уже было в общих
    @DisplayName("Year Test№ 1 approved Card With Year Empty Field")
    public void approvedCardWithYearOEmptyField() {
        payPage.fillCardData(YearHelper.approvedCardWithYearEmptyField());

        payPage.shouldEmptyFieldNotification();//Сообщение Поле обязательно для заполнения
    }


    //    Заполнение поля "Год" предыдущим годом, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Год" появиться предупреждение об невалидном значение
    @Test//OK
    @DisplayName("Year Test№ 2 approved Card With Last Year")
    public void approvedCardWithLastYear() {
        payPage.fillCardData(YearHelper.approvedCardWithLastYear());

        payPage.shouldExpiredDatePassNotification();//Истёк срок действия карты
    }


    //    Заполнение поля "Год" текущим годом, а поля "Месяц" предыдущим месяцем, остальные поля заполнены валидно* в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Месяц" появиться предупреждение об невалидном значение
    @Test//OK
    @DisplayName("Year Test№ 3 approved Card With Valid Year Las tMonth")
    public void approvedCardWithValidYearLastMonth() {
        payPage.fillCardData(YearHelper.approvedCardWithValidYearLastMonth());

        payPage.shouldInvalidExpiredDateNotification();//Неверно указан срок действия карты
    }


    //    Заполнение поля "Год" 1 цифрой, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Год" появиться предупреждение об невалидном значение
    @Test//Баг сообщения уже было в общих
    @DisplayName("Year Test№ 4 approved Card With Year Of 1 number")
    public void approvedCardWithYearOf1numbers() {
        payPage.fillCardData(YearHelper.approvedCardWithYearOf1numbers());

        payPage.shouldEmptyFieldNotification();//Сообщение Поле обязательно для заполнения
    }


    //    Заполнение поля "Год" 2015, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: истёк срок действия карты
    @Test//Баг можно ввести значение меньше валидного
    @DisplayName("Year Test№ 5 approved Card With Year 2015")
    public void approvedCardWithYear2015() {
        payPage.fillCardData(YearHelper.approvedCardWithYear2015());

        payPage.shouldExpiredDatePassNotification();//истёк срок действия карты
    }


    //    Заполнение поля "Год" плюс 6 лет к текущему году, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Год" появиться предупреждение об невалидном значение, срок действия карты истек
    @Test//OK
    @DisplayName("Year Test№ 6 approved Card Year Plus 6")
    public void approvedCardYearPlus6() {
        payPage.fillCardData(YearHelper.approvedCardYearPlus6());

        payPage.shouldInvalidExpiredDateNotification();//Неверно указан срок действия карты
    }


    //    Заполнение поля "Год" рандомными спецсимволами, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Год" появиться предупреждение об невалидном значение
    @Test//OK
    @DisplayName("Year Test№ 7 approved Card With Year Of 2 symbols")
    public void approvedCardWithYearOf2symbols() {
        payPage.fillCardData(YearHelper.approvedCardWithYearOf2symbols());

        payPage.shouldImproperFormatNotification();//отображение сообщения Неверный формат
    }


    //    Заполнение поля "Год" буквами латиницы, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Год" появиться предупреждение об недопустимых символах
    @Test//OK
    @DisplayName("Year Test№ 8 approved Card With Year Of 2 Latin")
    public void approvedCardWithYearOf2Latin() {
        payPage.fillCardData(YearHelper.approvedCardWithYearOf2Latin());

        payPage.shouldImproperFormatNotification();//отображение сообщения Неверный формат
    }


    //    Заполнение поля "Год" буквами кириллицы, остальные поля заполнены валидно в форме "Оплата по карте" тура "Путешествие дня"
    //    Ожидаемый результат: под полем "Год" появиться предупреждение об недопустимых символах
    @Test//ОК
    @DisplayName("Year Test№ 9 approved Card With Year Of 2 Cyrillic")
    public void approvedCardWithYearOf2Cyrillic() {
        payPage.fillCardData(YearHelper.approvedCardWithYearOf2Cyrillic());

        payPage.shouldImproperFormatNotification();//отображение сообщения Неверный формат
    }
}
