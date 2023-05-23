package ru.netology.helpers;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLHelper {
    private static final String url = System.getProperty("db.url");
    private static final String username = System.getProperty("db.username");
    private static final String password = System.getProperty("db.password");
    public static final QueryRunner runner = new QueryRunner();

    public SQLHelper() {
    }

    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection(url, username, password);
    }

    @SneakyThrows
    public static DataGenerator.CreditEntity getCreditCardData() {
        var cardDataSQL = "SELECT * FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
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








































