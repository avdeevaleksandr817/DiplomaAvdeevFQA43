package ru.netology.helpers.helpers;

import com.github.javafaker.Faker;
import ru.netology.helpers.DataGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static ru.netology.helpers.DataGenerator.getNumberByStatus;
import static ru.netology.helpers.DataGenerator.symbols;

public class MonthHelper {
    private static final Faker fakerEN = new Faker(Locale.ENGLISH);
    private static final Faker fakerRU = new Faker(new Locale("ru", "RU"));

    //сгенерировать месяц
    public static String generateMonth(int shiftMonth) {
        return LocalDate.now().plusMonths(shiftMonth).format(DateTimeFormatter.ofPattern("MM"));
    }

    //поле месяц пустое
    public static String generateMonthEmptyField() {
        return ("");
    }

    //заполнить поле месяц 3 цифры
    public static String generateMonthWith3numbers() {
        return fakerEN.numerify("###");
    }

    //заполнить поле месяц 2 нуля
    public static String generateMonthWith00numbers() {
        return ("00");
    }

    //заполнить поле месяц 1 цифры
    public static String generateMonthWith1number() {
        return fakerEN.numerify("#");
    }

    //заполнить поле месяц число 12
    public static String generateMonthWith12numbers() {
        return ("12");
    }

    //заполнить поле месяц число 13
    public static String generateMonthWith13numbers() {
        return ("13");
    }

    //заполнить поле месяц 2 спецсимвола
    public static String generateMonthWith2symbols() {
        return (symbols("#") + symbols("#"));
    }

    //заполнить поле месяц 2 латинских буквы
    public static String generateMonthWith2Latin() {
        return fakerEN.letterify("??");
    }

    //заполнить поле месяц 2 буквы кириллица
    public static String generateMonthWith2Cyrillic() {
        return fakerRU.letterify("??");
    }


    //ApprovedCard создать Month из 3 цифр
    public static DataGenerator.CardData approvedCardWithMonthOf3numbers() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonthWith3numbers(),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать Month из 00 цифр
    public static DataGenerator.CardData approvedCardWithMonthOf00numbers() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonthWith00numbers(),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать Month из 1 цифры
    public static DataGenerator.CardData approvedCardWithMonthOf1numbers() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonthWith1number(),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать Month из числа 12
    public static DataGenerator.CardData approvedCardWithMonthOf12numbers() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonthWith12numbers(),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать Month из числа 13
    public static DataGenerator.CardData approvedCardWithMonthOf13numbers() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonthWith13numbers(),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать Month из 2symbols
    public static DataGenerator.CardData approvedCardWithMonthOf2symbols() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonthWith2symbols(),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать Month из 2 Latin
    public static DataGenerator.CardData approvedCardWithMonthOf2Latin() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonthWith2Latin(),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать Month из 2 Cyrillic
    public static DataGenerator.CardData approvedCardWithMonthOf2Cyrillic() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonthWith2Cyrillic(),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }
    //ApprovedCard создать Month пустое поле
    public static DataGenerator.CardData approvedCardWithMonthEmptyField() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonthEmptyField(),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }
}
