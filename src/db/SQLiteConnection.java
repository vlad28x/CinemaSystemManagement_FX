package db;

import org.sqlite.SQLiteConfig;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteConnection {
    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
            String url = "jdbc:sqlite:db" + File.separator + "CINEMA.db";// относительный путь
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);//включение внешних ключей
            return DriverManager.getConnection(url, config.toProperties());
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
