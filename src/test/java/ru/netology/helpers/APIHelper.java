package ru.netology.helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class APIHelper {
    //Спецификация запроса requestSpec = новый построитель спецификаций запросов
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")//установить базовый Uri
            .setPort(9999)//установить порт
            .setAccept(ContentType.JSON)//установить Принять
            .setContentType(ContentType.JSON)//установить тип контента
            .log(LogDetail.ALL)//журнал(Детали журнала.ВСЕ)
            .build();//собрать

    public static int getRequestStatusCodePayment(DataGenerator.CardData cardInfo) {

        int statusCode = given()
                .spec(requestSpec)
                .body(cardInfo)
                .when()
                .post("/payment")
                .getStatusCode();
        return statusCode;
    }

    public static int getRequestStatusCodeCredit(DataGenerator.CardData cardInfo) {

        int statusCode = given()
                .spec(requestSpec)
                .body(cardInfo)
                .when()
                .post("/credit")
                .getStatusCode();
        return statusCode;
    }
}
