package impls;

import db.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Query2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBQuery2 {

    ObservableList<Query2> query2list = FXCollections.observableArrayList();

    public ObservableList<Query2> findAll(int cinema_id) {
        query2list.clear();
        try(Connection con = SQLiteConnection.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT DATE_TIME, NUMBER_FREE_PLACES \n" +
                    "    FROM SESSION \n" +
                    "    WHERE HALL_ID IN \n" +
                    "    (SELECT HALL_ID FROM HALL WHERE CINEMA_ID = ?);")) {
            statement.setInt(1, cinema_id);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Query2 query2 = new Query2();
                query2.setDate_time(rs.getString("DATE_TIME"));
                query2.setPlaces(rs.getInt("NUMBER_FREE_PLACES"));
                query2list.add(query2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

        return query2list;
    }
}
