package ru.netology.helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.val;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class APIHelper {
    //Спецификация запроса requestSpec = новый построитель спецификаций запросов
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")//установить базовый Uri
            .setPort(8080)//установить порт
            .setAccept(ContentType.JSON)//установить Принять
            .setContentType(ContentType.JSON)//установить тип контента
            .log(LogDetail.ALL)//журнал(Детали журнала.ВСЕ)
            .build();//собрать

    public static String createPayment(DataGenerator.CardData cardInfo) {
        return given()//ДАНО
                .spec(requestSpec)///спецификация(запрос спецификации)
                .body(cardInfo)//тело запроса (информация о карте)
                .when()//КОГДА
                .post("/api/v1/pay")//тип запроса пост идет на ("/оплата")
                .then()//ТОГДА
                .statusCode(200)//код статуса(200)
                .extract()//извлечь
                .response()//ответ
                .asString();//как строку
    }


    public static String createCredit(DataGenerator.CardData cardInfo) {
        return given()//ДАНО
                .spec(requestSpec)///спецификация(запрос спецификации)
                .body(cardInfo)//тело запроса (информация о карте)
                .when()//КОГДА
                .post("/api/v1/credit")//тип запроса пост идет на ("/кредит")
                .then()//ТОГДА
                .statusCode(200)//код статуса(200)
                .extract()//извлечь
                .response()//ответ
                .asString();//как строку

    }


//    BeforeAll
//    static void setUpAll() {
//        SelenideLogger.addListener("allure", new AllureSelenide());
//    }
//
//    @BeforeEach
//    public void cleanTable() {
//        SQL.cleanTable();
//    }
//
//    @AfterAll
//    static void tearDownAll() {
//        SelenideLogger.removeListener("allure");
//    }

    @Test
    void shouldValidStatusCardPaymentApproved() {
        val validApprovedCard = DataGenerator.generateDataWithApprovedCard();
        val status = createPayment(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
    }

    @Test
    void shouldValidStatusCardPaymentDeclined() {
        val validDeclinedCard = DataGenerator.generateDataWithDeclineCard();
        val status = createPayment(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
    }

    @Test
    void shouldValidStatusCardCreditRequestApproved() {
        val validApprovedCard = DataGenerator.generateDataWithApprovedCard();
        val status = createCredit(validApprovedCard);
        assertTrue(status.contains("APPROVED"));
        assertEquals("APPROVED", SQL.getCreditCardData());
    }
    //Р±Р°Рі DECLINED РЅРѕ APPROVED
    @Test
    void shouldValidStatusCardCreditRequestDeclined() {
        val validDeclinedCard = DataGenerator.generateDataWithDeclineCard();
        val status = createCredit(validDeclinedCard);
        assertTrue(status.contains("DECLINED"));
        assertEquals("DECLINED", SQL.getCreditCardData());
    }

}
