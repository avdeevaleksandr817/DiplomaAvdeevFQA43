package ru.netology.helpers.helpers;

import com.github.javafaker.Faker;
import ru.netology.helpers.DataGenerator;

import java.util.Locale;

import static ru.netology.helpers.DataGenerator.getNumberByStatus;
import static ru.netology.helpers.DataGenerator.symbols;

public class HolderHelper {

    private static final Faker fakerEN = new Faker(Locale.ENGLISH);
    private static final Faker fakerRU = new Faker(new Locale("ru", "RU"));

    //создать валидного владельца карты
    public static String generateValidHolder() {
        return fakerEN.name().fullName().toUpperCase();
    }

    //пустое поле владельца карты
    public static String generateHolderEmpty() {
        return ("");
    }

    //создать валидного владельца карты с двойной фамилией через дефис
    public static String generateValidHolderWithDoubleLastName() {
        return fakerEN.name().lastName().toUpperCase() + "-" + fakerEN.name().lastName().toUpperCase() + " "
                + fakerEN.name().firstName().toUpperCase();
    }

    //создать не валидного пользователя заполнив данные кириллицей
    public static String generateInvalidHolderWithCyrillicSymbols() {
        return fakerRU.name().firstName().toUpperCase() + " "
                + fakerRU.name().lastName().toUpperCase();
    }

    //Заполнения поля владелец в нижнем регистре
    public static String generateInvalidHolderLowerCase() {
        return ("Aaron" + "Presley");
    }

    //Заполнения поля владелец в начале и конце пробелы
    public static String generateHolderStartAndEndSpaces() {
        return (" AARON " + " PRESLEY ");
    }

    //Заполнения поля владелец в начале и конце дефисы
    public static String generateHolderStartAndEndHyphens() {
        return ("-AARON-" + "-PRESLEY-");
    }

    //Заполнения поля владелец цифрами
    public static String generateHolderNumbers() {
        return (fakerEN.numerify("######## ########"));
    }

    //Заполнения поля владелец 1001
    public static String generateHolder1001Letter() {
        return ("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

    //Заполнения поля владелец одной буквой верхний регистр
    public static String generateHolderOneLetter() {
        return (fakerEN.letterify("?").toUpperCase());
    }

    public static String generateHolderSymbols() {
        return (symbols("#") + symbols("#") + symbols("#") + symbols("#") + symbols("#") + symbols("#") + symbols("#") + symbols("#") + "-" + symbols("#") + symbols("#") + symbols("#") + symbols("#") + symbols("#") + symbols("#") + symbols("#") + symbols("#"));
    }

    //ApprovedCard создать валидного владельца карты с двойной фамилией через дефис
    public static DataGenerator.CardData approvedCardWithDoubleLastName() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateValidHolderWithDoubleLastName(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать владельца карты нижний регистр
    public static DataGenerator.CardData approvedCardWithHolderLowerCase() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateInvalidHolderLowerCase(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать владельца карты пробелы в начале и конце
    public static DataGenerator.CardData approvedCardWithHolderStartAndEndSpaces() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateHolderStartAndEndSpaces(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать владельца карты дефисы в начале и конце
    public static DataGenerator.CardData approvedCardWithHolderStartAndEndHyphens() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateHolderStartAndEndHyphens(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать владельца карты из цифр
    public static DataGenerator.CardData approvedCardWithHolderNumbers() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateHolderNumbers(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать владельца из 1001 буквы
    public static DataGenerator.CardData approvedCardWithHolder1001Letter() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateHolder1001Letter(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать владельца из 1 буквы
    public static DataGenerator.CardData approvedCardWithHolderOneLetter() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateHolderOneLetter(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать владельца из кириллицы
    public static DataGenerator.CardData approvedCardWithHolderWithCyrillicSymbols() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateInvalidHolderWithCyrillicSymbols(),
                CVCHelper.generateValidCVC());
    }

    //ApprovedCard создать владельца из символов
    public static DataGenerator.CardData approvedCardWithHolderSymbols() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateHolderSymbols(),
                CVCHelper.generateValidCVC());
    }
    //ApprovedCard создать владельца пустое поле
    public static DataGenerator.CardData approvedCardWithHolderEmpty() {
        return new DataGenerator.CardData(getNumberByStatus("APPROVED"),
                MonthHelper.generateMonth(0),
                YearHelper.generateYear(0),
                HolderHelper.generateHolderEmpty(),
                CVCHelper.generateValidCVC());
    }

}
