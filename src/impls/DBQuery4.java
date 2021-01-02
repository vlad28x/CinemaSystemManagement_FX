package impls;

import db.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Query4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBQuery4 {

    ObservableList<Query4> query4List = FXCollections.observableArrayList();

    public ObservableList<Query4> findAll(int release) {
        query4List.clear();
        try(Connection con = SQLiteConnection.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT MOVIE_NAME\n" +
                    "    FROM MOVIE M\n" +
                    "    WHERE M.RELEASE = ?;")) {
            statement.setInt(1, release);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Query4 query4 = new Query4();
                query4.setName(rs.getString("MOVIE_NAME"));
                query4List.add(query4);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

        return query4List;
    }
}
