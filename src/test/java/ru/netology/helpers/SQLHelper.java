package ru.netology.helpers;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLHelper {
    //Система. Получить Свойство из билд гредл
    private static final String url = System.getProperty("db.url");
    //Система. Получить Свойство из билд гредл
    private static final String username = System.getProperty("db.username");
    //Система. Получить Свойство из билд гредл
    private static final String password = System.getProperty("db.password");
    //ЗапросБегунок бегунок = новый бегун запросов//необходим для выполнения запросов
    public static final QueryRunner runner = new QueryRunner();

    public SQLHelper() {
    }
    //@SneakyThrow позволит избежать требований javac в том, что вы либо перехватываете, либо выбрасываете любые проверенные исключения,
    // которые операторы в вашем теле метода объявляют, что они генерируют.
    //@SneakyThrow не проглатывает, не заворачивает в RuntimeException или иным образом не модифицирует какие-либо исключения из перечисленных проверенных типов исключений.
    // JVM не проверяет непротиворечивость проверенной системы исключений; javac делает, и эта аннотация позволяет отказаться от его механизма.

    @SneakyThrows
    private static Connection getConn() {
        //Примечание реализации:
        //Инициализация DriverManager ищет поставщиков услуг с помощью загрузчика класса контекста потока.
        // Драйверы, загруженные и доступные для приложения, будут зависеть от загрузчика классов контекста потока,
        // который запускает инициализацию драйвера с помощью DriverManager.
        //При вызове метода getConnection DriverManager попытается найти подходящий драйвер среди тех,
        // которые загружаются при инициализации, и тех, которые загружаются явно с использованием того же загрузчика классов, что и текущее приложение
        return DriverManager.getConnection(url, username, password);

    }

    @SneakyThrows
    public static DataGenerator.CreditEntity getCreditCardData() {
        //var
        //Класс String представляет строки символов. Все строковые литералы в программах на Java, такие как "abc", реализованы как экземпляры этого класса.
        //Строки постоянны; их значения не могут быть изменены после их создания. Строковые буферы поддерживают изменяемые строки.
        // Поскольку объекты String неизменяемы, ими можно делиться.

        //запрос = ВЫБРАТЬ * ИЗ кредитного запроса СОРТИРОВАТЬ ПО созданный ОПИСАНИЕ ОГРАНИЧЕНИЕ 1
        var cardDataSQL = "SELECT * FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        //Попробовать создать (переменная подключение = с помощью метода getConn( Менеджер драйверов. получить соединение с БД(адрес, пользователь, пароль))
        try (var conn = getConn()) {
            //query
            //Выполнить запрос SQL SELECT без каких-либо параметров замены. Вызывающий отвечает за закрытие соединения.
            //Параметры://conn — соединение для выполнения запроса в.
            //результат = бегунок вызывает исполнитель запроса( с параметрами соединение, тело запроса, дальше мы говорим как обработать ответ
            //здесь одна строка из таблицы(BeanHandler) парсит строку по полям из CreditEntity.class
            return runner.query(conn, cardDataSQL, new BeanHandler<>(DataGenerator.CreditEntity.class));
        } catch (SQLException exception) {//catch (исключение SQL sql Exception)
            exception.printStackTrace();//Исключение sql. распечатать трассировку стека()
        }
        return null;
    }

    @SneakyThrows
    public static DataGenerator.PaymentEntity getPaymentCardData() {
        var cardDataSQL = "SELECT * FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            return runner.query(conn, cardDataSQL, new BeanHandler<>(DataGenerator.PaymentEntity.class));
        } catch (SQLException exception) {//catch (исключение SQL sql Exception)
            exception.printStackTrace();//Исключение sql. распечатать трассировку стека()
        }
        return null;
    }

    @SneakyThrows
    public static DataGenerator.OrderEntity getTableOrderEntity() {
        var orderEntityDataSQL = "SELECT * FROM order_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            var result = runner.query(conn, orderEntityDataSQL, new BeanHandler<>(DataGenerator.OrderEntity.class));
            return result;
        } catch (SQLException exception) {//catch (исключение SQL sql Exception)
            exception.printStackTrace();//Исключение sql. распечатать трассировку стека()
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var conn = getConn();
        runner.execute(conn, "DELETE FROM order_entity");//удалить из объект заказа = "УДАЛИТЬ ИЗ объекта заказа"
        runner.execute(conn, "DELETE FROM payment_entity");//удалить из Payment Entity = "УДАЛИТЬ ИЗ платежного объекта"
        runner.execute(conn, "DELETE FROM credit_request_entity");//удалить из объект кредита = "УДАЛИТЬ ИЗ объекта запроса кредита"

    }
}








































