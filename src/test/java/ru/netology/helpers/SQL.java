package ru.netology.helpers;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQL {

    static String url = System.getProperty("db.url");//Система. Получить Свойство
    static String user = System.getProperty("db.username");//Система. Получить Свойство
    static String password = System.getProperty("db.password");//Система. Получить Свойство
    private static QueryRunner runner = new QueryRunner();

    public SQL() {
    }


    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection(url, user, password);
    }


    public static DataGenerator.CreditRequestEntity getCreditCardData() {
        var cardDataSQL = "SELECT * FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            var result = runner.query(conn, cardDataSQL,
                    new BeanHandler<>(DataGenerator.CreditRequestEntity.class));
            return result;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static DataGenerator.PaymentEntity getPaymentCardData() {
        var cardDataSQL = "SELECT * FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            var result = runner.query(conn, cardDataSQL,
                    new BeanHandler<>(DataGenerator.PaymentEntity.class));
            return result;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static DataGenerator.OrderEntity getTableOrderEntity() {
        var orderEntityDataSQL = "SELECT * FROM order_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            var result = runner.query(conn, orderEntityDataSQL,
                    new BeanHandler<>(DataGenerator.OrderEntity.class));
            return result;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var conn = getConn();
        runner.execute(conn, "DELETE FROM order_entity");
        runner.execute(conn, "DELETE FROM payment_entity");
        runner.execute(conn, "DELETE FROM credit_request_entity");
    }
}






































