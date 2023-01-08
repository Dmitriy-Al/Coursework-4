import data.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AlpinistDao implements Dao<Alpinist, Integer> {

    public void createTable() {
        String create = "CREATE TABLE IF NOT EXISTS table_alpinists(alpinist_id SERIAL PRIMARY KEY, name VARCHAR(20) NOT NULL, age INTEGER NOT NULL);";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Не установить соединение с сервером");
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(DbConnectionSettings.CONNECTION_STRING, DbConnectionSettings.LOGIN, DbConnectionSettings.PASSWORD)) {
            try (java.sql.Statement statement = connection.createStatement()) {
                statement.executeUpdate(create);
                System.out.println("Таблица создана");
            }
        } catch (SQLException e) {
            System.out.println("Таблица не была создана " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public void add(Alpinist alpinist) {
        String insert = "INSERT INTO table_alpinists (name, age) VALUES (?, ?) RETURNING alpinist_id";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("org.postgresql.Driver не был загружен");
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(DbConnectionSettings.CONNECTION_STRING, DbConnectionSettings.LOGIN, DbConnectionSettings.PASSWORD)) {
            try (PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setString(1, alpinist.getName());
                statement.setInt(2, alpinist.getAge());
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int id = resultSet.getInt("alpinist_id");
                    System.out.println("Данные добавлены, id = " + id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Alpinist> getInfo() {
        List<Alpinist> alpinists;
        String select = "SELECT * FROM table_alpinists";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(DbConnectionSettings.CONNECTION_STRING, DbConnectionSettings.LOGIN, DbConnectionSettings.PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(select);
                alpinists = new ArrayList<>();
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    int id = resultSet.getInt("alpinist_id");
                    Alpinist alpinist = new Alpinist();
                    alpinist.setName(name);
                    alpinist.setAge(age);
                    alpinist.setId(id);
                    alpinists.add(alpinist);
                }
            }
        } catch (SQLException e) {
            System.out.println("Таблица не была создана " + e.getMessage());
            throw new RuntimeException(e);
        }
        return alpinists;
    }


    public Alpinist getById(Integer integer) {
        Alpinist alpinist = null;
        String select = "SELECT name, age FROM table_alpinists WHERE alpinist_id = ?";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("org.postgresql.Driver не был загружен");
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(DbConnectionSettings.CONNECTION_STRING, DbConnectionSettings.LOGIN, DbConnectionSettings.PASSWORD)) {
            try (PreparedStatement statement = connection.prepareStatement(select)) {
                statement.setInt(1, integer);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int age = resultSet.getInt("age");
                    String name = resultSet.getString("name");
                    alpinist = new Alpinist();
                    alpinist.setAge(age);
                    alpinist.setName(name);
                    alpinist.setId(integer);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alpinist;
    }


    public void changeAlpinistName(Alpinist alpinist) {
        String update = "UPDATE table_alpinists SET name = ?";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("org.postgresql.Driver не был загружен");
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(DbConnectionSettings.CONNECTION_STRING, DbConnectionSettings.LOGIN, DbConnectionSettings.PASSWORD)) {
            try (PreparedStatement statement = connection.prepareStatement(update)) {
                statement.setString(1, alpinist.getName());
                statement.executeUpdate();
                System.out.println("Имя изменено");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Alpinist> getDeterminedAgeAlpinists() {
        List<Alpinist> alpinists;
        String select = "SELECT alpinist_id, name FROM table_alpinists WHERE age BETWEEN 30 AND 50";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(DbConnectionSettings.CONNECTION_STRING, DbConnectionSettings.LOGIN, DbConnectionSettings.PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(select);
                alpinists = new ArrayList<>();
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int id = resultSet.getInt("alpinist_id");
                    Alpinist alpinist = new Alpinist();
                    alpinist.setName(name);
                    alpinist.setId(id);
                    alpinists.add(alpinist);
                }
            }
        } catch (SQLException e) {
            System.out.println("Таблица не была создана " + e.getMessage());
            throw new RuntimeException(e);
        }
        return alpinists;
    }


}
