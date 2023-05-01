package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    //заголовок найти строку, значение точного текста
    private static final SelenideElement headingDayTrip = $x("//div[@id='root']/div/h2");
    //описание путешествия #root > div > div
    private static final SelenideElement dailyTripCard = $x("//div[@id='root']/div/div[contains(@class, 'card')]");
    //поиск по коллекции элементов кнопка "button" плюс найти точный текст
    private static final SelenideElement cardButton = $$("button").find(exactText("Купить"));
    //поиск по коллекции элементов кнопка "button" плюс найти точный текст
    private static final SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));
    //показать форма для заполнения
    private static final SelenideElement formToFill = $("#root > div > form");
    private static final SelenideElement messPay = $("#root > div > h3");
    public void choosePaymentCard() {
        headingDayTrip.find(String.valueOf(exactText("Путешествие дня")));
        dailyTripCard.shouldBe(visible);
        cardButton.shouldBe(visible).click();
        messPay.shouldHave(text("Оплата по карте"));
        formToFill.shouldBe(visible);

    }
    public void chooseCreditOnCard() {
        headingDayTrip.find(String.valueOf(exactText("Путешествие дня")));
        dailyTripCard.shouldBe(visible);
        creditButton.shouldBe(visible).click();
        messPay.shouldHave(text("Кредит по данным карты"));
        formToFill.shouldBe(visible);

    }
}


