package ru.netology.helpers.helpers;

import com.github.javafaker.Faker;
import ru.netology.helpers.DataGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static ru.netology.helpers.DataGenerator.getNumberByStatus;
import static ru.netology.helpers.DataGenerator.symbols;

public class YearHelper {
    private static final Faker fakerEN = new Faker(Locale.ENGLISH);
    private static final Faker fakerRU = new Faker(new Locale("ru", "RU"));
    //сгенерировать год
    public static String generateYear(int shiftYear) {
        return LocalDate.now().plusYears(shiftYear).format(DateTimeFormatter.ofPattern("yy"));
    }
    //поле год пустое
    public static String generateYearEmptyField() {
        return("");
    }
    //заполнить поле год 1 цифры
    public static String generateYearWith1number() {return fakerEN.numerify("#");}
    //заполнить поле год 3 цифры
    public static String generateYearWith3numbers() {
        return fakerEN.numerify("###");
    }
    //заполнить поле год 2 нуля
    public static String generateYearWith00numbers() {return("00");}
    //заполнить поле год 2 спецсимвола
    public static String generateYearWith2symbols() {return(symbols("#") + symbols("#"));}
    //заполнить поле год 2 латинских буквы
    public static String generateYearWith2Latin() {
        return fakerEN.letterify("??");
    }
    //заполнить поле год 2 буквы кириллица
    public static String generateYearWith2Cyrillic() {
        return fakerRU.letterify("??");
    }

    //ApprovedCard создать Year пустое поле
    public static DataGenerator.CardData approvedCardWithYearEmptyField() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYearEmptyField(),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }
    //ApprovedCard создать Year предыдущий год
    public static DataGenerator.CardData approvedCardWithLastYear() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(-1),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }
    //ApprovedCard создать Year предыдущий год
    public static DataGenerator.CardData approvedCardWithValidYearLastMonth() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(-1),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }
    //ApprovedCard создать Year предыдущий год
    public static DataGenerator.CardData approvedCardYearPlus6() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(+6),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }
    //ApprovedCard создать Year из 1 цифры
    public static DataGenerator.CardData approvedCardWithYearOf1numbers() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYearWith1number(),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }
    //ApprovedCard создать Year из 3 цифр
    public static DataGenerator.CardData approvedCardWithYear2015() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(-8),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }
    //ApprovedCard создать Year из 00 цифр
    public static DataGenerator.CardData approvedCardWithYearOf00numbers() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYearWith00numbers(),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }
    //ApprovedCard создать Year из 2symbols
    public static DataGenerator.CardData approvedCardWithYearOf2symbols() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYearWith2symbols(),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }
    //ApprovedCard создать Year из 2 Latin
    public static DataGenerator.CardData approvedCardWithYearOf2Latin() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYearWith2Latin(),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }
    //ApprovedCard создать Year из 2 Cyrillic
    public static DataGenerator.CardData approvedCardWithYearOf2Cyrillic() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYearWith2Cyrillic(),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

}
