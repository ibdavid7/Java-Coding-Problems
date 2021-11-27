package JetBrainsAcademy;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class CoolJDBC {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:C:/Users/pc/SQLite/edu.db";


        SQLiteDataSource sqLiteDataSource = new SQLiteDataSource();
        sqLiteDataSource.setUrl(url);

        try (Connection connection = sqLiteDataSource.getConnection()) {
            if (connection.isValid(5)) {
                System.out.println("Connection is valid");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
