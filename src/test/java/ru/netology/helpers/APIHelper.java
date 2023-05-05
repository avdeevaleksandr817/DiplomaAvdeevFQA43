package ru.netology.helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class APIHelper {
    //Спецификация запроса requestSpec = новый построитель спецификаций запросов
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")//установить базовый Uri
            .setPort(8080)//установить порт
            .setAccept(ContentType.JSON)//установить Принять
            .setContentType(ContentType.JSON)//установить тип контента
            .log(LogDetail.ALL)//журнал(Детали журнала.ВСЕ)
            .build();//собрать

    public static void createPayment(DataGenerator.CardData cardInfo) {
        given()//ДАНО
                .spec(requestSpec)///спецификация(запрос спецификации)
                .body(cardInfo)//тело запроса (информация о карте)
                .when()//КОГДА
                .post("/api/v1/pay")//тип запроса пост идет на ("/оплата")
                .then()//ТОГДА
                .statusCode(200);//код статуса(200)
//                .extract()//извлечь
//                .response()//ответ
//                .asString();//как строку
//        return createPayment(cardInfo);
    }


    public static void createCredit(DataGenerator.CardData cardInfo) {
        given()//ДАНО
                .spec(requestSpec)///спецификация(запрос спецификации)
                .body(cardInfo)//тело запроса (информация о карте)
                .when()//КОГДА
                .post("/api/v1/credit")//тип запроса пост идет на ("/кредит")
                .then()//ТОГДА
                .statusCode(200);//код статуса(200)
//                .extract()//извлечь
//                .response()//ответ
//                .asString();//как строку

//        return createCredit(cardInfo);
    }


}
