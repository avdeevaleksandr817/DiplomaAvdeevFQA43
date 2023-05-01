package ru.netology.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.helpers.helpers.*;

import java.sql.Timestamp;
import java.util.Random;

public class DataGenerator {

    //один спецсимвол
    public static String symbols(String locale) {
        var symbol = new String[]{"!", "", "№", ";", "%", ":", "?", "*", "(", ")", "_", "-", "+", "=", "/", "~", "@", "#", "$", "&"};
        return symbol[new Random().nextInt(symbol.length)];
    }

    private static final String ApprovedCard = "4444 4444 4444 4441";
    private static final String DeclinedCard = "4444 4444 4444 4442";

    //вернуть номер карты по статусу
    public static String getNumberByStatus(String status) {
        if (status.equalsIgnoreCase("APPROVED")) {
            return ApprovedCard;
        } else if (status.equalsIgnoreCase("DECLINED")) {
            return DeclinedCard;
        }
        return null;
    }

    //получить карту со статусом ОДОБРЕНО
    public static CardData generateDataWithApprovedCard() {
        return new CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //получить карту со статусом ОТКЛОНЕНО
    public static CardData generateDataWithDeclineCard() {
        return new CardData(getNumberByStatus("DECLINED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //форма все поля пустые
    public static CardData CardEmptyField() {
        return new CardData(
                NumberHelper.generateEmptyField(),
                MonthHelper.generateMonthEmptyField(),
                YearHelper.generateYearEmptyField(),
                HolderHelper.generateHolderEmpty(),
                CVCHelper.generateEmptyCVC());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CardData {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderEntity {
        private String id;
        private Timestamp created;
        private String credit_id;
        private String payment_id;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreditRequestEntity {
        private String id;
        private String bank_id;
        private Timestamp created;
        private String status;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentEntity {
        private String id;
        private int amount;
        private Timestamp created;
        private String status;
        private String transaction_id;
    }


}
