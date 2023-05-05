package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.helpers.APIHelper;
import ru.netology.helpers.DataGenerator;
import ru.netology.helpers.SQL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAPI {

//    BeforeAll
//    static void setUpAll() {
//        SelenideLogger.addListener("allure", new AllureSelenide());
//    }
//
//    @BeforeEach
//    public void cleanTable() {
//        SQL.cleanDatabase();
//    }
//
//    @AfterAll
//    static void tearDownAll() {
//        SelenideLogger.removeListener("allure");
//    }
@Test

@DisplayName("Should add the payment data to the database with APPROVAL via the API")
void shouldSuccessTransactionWithApprovedPaymentCardViaAPI() {
    var cardInfo = DataGenerator.generateDataWithApprovedCard();
    APIHelper.createPayment(cardInfo);

    var paymentCardData = SQL.getPaymentCardData();
    assert paymentCardData != null;
    assertEquals("APPROVED", paymentCardData.getStatus());

}


    }

