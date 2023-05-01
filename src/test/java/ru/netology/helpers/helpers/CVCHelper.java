package ru.netology.helpers.helpers;

import com.github.javafaker.Faker;
import ru.netology.helpers.DataGenerator;

import java.util.Locale;

import static ru.netology.helpers.DataGenerator.getNumberByStatus;
import static ru.netology.helpers.DataGenerator.symbols;

public class CVCHelper {

    private static final Faker fakerEN = new Faker(Locale.ENGLISH);
    private static final Faker fakerRU = new Faker(new Locale("ru", "RU"));

    //создать валидный CVC
    public static String generateValidCVC() {
        return fakerEN.numerify("###");
    }

    //пустое поле
    public static String generateEmptyCVC() {
        return ("");
    }

    //сгенерировать недействительный CVC с 1 цифрами
    public static String generateInvalidCVCWith1Digit() {
        return fakerEN.numerify("#");
    }

    //сгенерировать недействительный CVC с 4 цифрами
    public static String generateInvalidCVCWith4Digit() {
        return fakerEN.numerify("####");
    }

    //генерировать недопустимый CVC с 3 случайными символами латиницы
    public static String generateInvalidCVCWith3Latin() {
        return fakerEN.letterify("???");
    }

    //генерировать недопустимый CVC с 3 случайными символами кириллицы
    public static String generateInvalidCVCWith3Cyrillic() {
        return fakerRU.letterify("???");
    }

    //генерировать недопустимый CVC с 3 случайными спецсимволами
    public static String generateInvalidCVCWith3Symbols() {
        return (symbols("#") + symbols("#") + symbols("#"));
    }

    //ApprovedCard создать CVC из 1 символа
    public static DataGenerator.CardData approvedCardWithCVCWith1Digit() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateInvalidCVCWith1Digit());
    }

    //ApprovedCard создать CVC из 4 символа
    public static DataGenerator.CardData approvedCardWithCVCWith4Digit() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateInvalidCVCWith4Digit());
    }

    //ApprovedCard создать CVC из 4 символа
    public static DataGenerator.CardData approvedCardWithCVCWith3Latin() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateInvalidCVCWith3Latin());
    }

    //ApprovedCard создать CVC из 3 Cyrillic
    public static DataGenerator.CardData approvedCardWithCVCWith3Cyrillic() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateInvalidCVCWith3Cyrillic());
    }

    //ApprovedCard создать CVC из 3 Symbols
    public static DataGenerator.CardData approvedCardWithCVCWith3Symbols() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateInvalidCVCWith3Symbols());
    }
}
