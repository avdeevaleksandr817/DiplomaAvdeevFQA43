package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.helpers.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PayPage {
    //ПОЛЯ ДЛЯ ЗАПОЛНЕНИЯ
    private final SelenideElement fieldCardNumber = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement fieldNumberOfMonth = $("input[placeholder='08']");
    private final SelenideElement fieldYear = $("input[placeholder='22']");
    private final SelenideElement fieldCardholder = $(byText("Владелец")).parent().$("input");
    private final SelenideElement fieldCvv = $("input[placeholder='999']");
    //СООБЩЕНИЯ НА ПОЛЯХ
    private final SelenideElement improperFormat = $(byText("Неверный формат"));
    private final SelenideElement emptyField = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement invalidExpiredDate = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement expiredDatePass = $(byText("Истёк срок действия карты"));
    private final SelenideElement successNote = $(byText("Операция одобрена Банком."));
    private final SelenideElement failureNote = $(byText("Ошибка! Банк отказал в проведении операции."));
    //кнопка
    public final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    //заполнения полей без сообщений об ошибках
    public void fillCardData(DataGenerator.CardData cardData) {
        fieldCardNumber.shouldBe(visible).setValue(cardData.getNumber());
        fieldNumberOfMonth.shouldBe(visible).setValue(cardData.getMonth());
        fieldYear.shouldBe(visible).setValue(cardData.getYear());
        fieldCardholder.shouldBe(visible).setValue(cardData.getHolder());
        fieldCvv.shouldBe(visible).setValue(cardData.getCvc());
        continueButton.shouldBe(visible).click();
    }
    //отсутствие сообщения при валидном заполнении
    public void shouldNotNotification(){
        shouldEmptyFieldNotificationHidden();//не видимое Сообщение Поле обязательно для заполнения
        shouldImproperFormatNotificationHidden();//не видимое отображение сообщения Неверный формат
        shouldInvalidExpiredDateNotificationHidden();// Не Видимое сообщение Неверно указан срок действия карты
        shouldExpiredDatePassNotificationHidden();//Не Видимое сообщение Неверно указан срок действия карты
    }

    //видимое отображение сообщения Неверный формат
    public void shouldImproperFormatNotification() {
        improperFormat.shouldBe(Condition.visible);
    }
    //не видимое отображение сообщения Неверный формат
    public void shouldImproperFormatNotificationHidden() {
        improperFormat.shouldBe(Condition.hidden);
    }


    //видимое Сообщение Поле обязательно для заполнения
    public void shouldEmptyFieldNotification() {
        emptyField.shouldBe(Condition.visible);
    }
    //не видимое Сообщение Поле обязательно для заполнения
    public void shouldEmptyFieldNotificationHidden(){
        emptyField.shouldBe(Condition.hidden);
    }


    //Видимое сообщение Неверно указан срок действия карты
    public void shouldInvalidExpiredDateNotification() {
        invalidExpiredDate.shouldBe(Condition.visible);
    }
    //Не Видимое сообщение Неверно указан срок действия карты
    public void shouldInvalidExpiredDateNotificationHidden() {
        invalidExpiredDate.shouldBe(Condition.hidden);
    }


    // Видимое сообщение истёк срок действия карты
    public void shouldExpiredDatePassNotification() {
        expiredDatePass.shouldBe(Condition.visible);
    }
    // Не Видимое сообщение истёк срок действия карты
    public void shouldExpiredDatePassNotificationHidden() {
        expiredDatePass.shouldBe(Condition.hidden);
    }


    //Сообщение видимое Операция одобрена Банком + ожидание 5 секунд
    public void shouldSuccessNotification() {successNote.shouldBe(Condition.visible, Duration.ofSeconds(5));
    }
    public void shouldSuccessNotificationHidden() {successNote.shouldBe(Condition.hidden, Duration.ofSeconds(5));
    }
    //Сообщение видимое Операция отклонена Банком + ожидание 5 секунд
    public void shouldFailureNotification() {
        failureNote.shouldBe(Condition.visible, Duration.ofSeconds(5));
    }

}
