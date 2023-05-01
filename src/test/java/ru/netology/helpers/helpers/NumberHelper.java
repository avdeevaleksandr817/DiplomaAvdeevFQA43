package ru.netology.helpers.helpers;

import com.github.javafaker.Faker;
import ru.netology.helpers.DataGenerator;

import java.util.Locale;

import static ru.netology.helpers.DataGenerator.symbols;

public class NumberHelper {
    private static final Faker fakerEN = new Faker(Locale.ENGLISH);
    private static final Faker fakerRU = new Faker(new Locale("ru", "RU"));

    //генерировать случайную одну цифру
    public static String generateRandomOneDigit() {
        return fakerEN.numerify("#");
    }

    //генерировать пустое поле
    public static String generateEmptyField() {
        return ("");
    }

    //сгенерировать не валидный номер карты с 15 цифрами
    public static String generateInvalidCardNumberWith15Digits() {
        return fakerEN.numerify("1111 22## #### ###");
    }

    //сгенерировать не валидный номер карты с 17 цифрами
    public static String generateValidCardNumberWith17Digits() {
        return fakerEN.numerify("1111 22## #### #### #");
    }

    //сгенерировать не валидный номер карты с 16 нулей
    public static String generateValidCardNumberWith16Zero() {
        return fakerEN.numerify("0000 0000 0000 0000");
    }

    //номер из 16 спецсимволов
    public static String generateInvalidCardNumberWith16Symbols() {
        return (symbols("#") + symbols("#") + symbols("#") + symbols("#")
                + symbols("#") + symbols("#") + symbols("#") + symbols("#")
                + symbols("#") + symbols("#") + symbols("#") + symbols("#")
                + symbols("#") + symbols("#") + symbols("#") + symbols("#"));
    }

    //сгенерировать неверный номер карты с 16 случайными латиница
    public static String generateInvalidCardNumberWithLatin() {
        return fakerEN.letterify("???? ???? ???? ????");
    }

    //сгенерировать неверный номер карты с номером из 16 кириллица
    public static String generateInvalidCardNumberWithCyrillic() {
        return fakerRU.letterify("???? ???? ???? ????");
    }

    //сгенерировать неверный номер карты из 16 цифр без пробелов
    public static String generateValidCardNumberWithoutSpaces() {
        return ("4444444444444441");
    }

    //сгенерировать неверный номер карты из 16 цифр с пробелами в начале и конце
    public static String generateValidCardNumberWithoutStartAndEndSpaces() {
        return (" 4444 4444 4444 4441 ");
    }

    //сгенерировать неверный номер карты из 16 цифр и запятыми
    public static String generateInvalidCardNumberWithCommas() {
        return ("1111,2222,3333,4444");
    }

    //поля заполнены валидно, номер действующей карты без пробелов
    public static DataGenerator.CardData dataWithApprovedCardNumberWithoutSpaces() {
        return new DataGenerator.CardData(
                NumberHelper.generateValidCardNumberWithoutSpaces(),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //поля заполнены валидно, номер действующей карты 1 цифра
    public static DataGenerator.CardData dataWithCardNumberOneRandomDigit() {
        return new DataGenerator.CardData(
                NumberHelper.generateRandomOneDigit(),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //поля заполнены валидно, номер действующей карты 15 цифр
    public static DataGenerator.CardData dataWithCardNumber15RandomDigit() {
        return new DataGenerator.CardData(
                NumberHelper.generateInvalidCardNumberWith15Digits(),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //поля заполнены валидно, номер действующей карты 17 цифр
    public static DataGenerator.CardData dataWithCardNumber17RandomDigit() {
        return new DataGenerator.CardData(
                NumberHelper.generateValidCardNumberWith17Digits(),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //поля заполнены валидно, номер действующей карты 16 zero
    public static DataGenerator.CardData dataWithCardNumber16zero() {
        return new DataGenerator.CardData(
                NumberHelper.generateValidCardNumberWith16Zero(),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //поля заполнены валидно, номер действующей карты 16 Symbols
    public static DataGenerator.CardData dataWithCardNumberWith16Symbols() {
        return new DataGenerator.CardData(
                NumberHelper.generateInvalidCardNumberWith16Symbols(),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //поля заполнены валидно, номер действующей карты 16 Latin
    public static DataGenerator.CardData dataWithCardNumberWithLatin() {
        return new DataGenerator.CardData(
                NumberHelper.generateInvalidCardNumberWithLatin(),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //поля заполнены валидно, номер действующей карты 16 Cyrillic
    public static DataGenerator.CardData dataWithCardNumberWithCyrillic() {
        return new DataGenerator.CardData(
                NumberHelper.generateInvalidCardNumberWithCyrillic(),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //поля заполнены валидно, номер действующей карты пробелы в начале и в конце
    public static DataGenerator.CardData dataWithCardNumberWithoutStartAndEndSpaces() {
        return new DataGenerator.CardData(
                NumberHelper.generateValidCardNumberWithoutStartAndEndSpaces(),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

    //поля заполнены валидно, номер действующей карты цифры разделены запятыми
    public static DataGenerator.CardData dataWithCardNumberWithCommas() {
        return new DataGenerator.CardData(
                NumberHelper.generateInvalidCardNumberWithCommas(),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolder(),
                CVCHelper.generateValidCVC());
    }

}
