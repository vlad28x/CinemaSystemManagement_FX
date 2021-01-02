package impls;

import db.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Query1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBQuery1 {

    ObservableList<Query1> query1List = FXCollections.observableArrayList();

    public ObservableList<Query1> findAll(int district_id) {
        query1List.clear();
        try(Connection con = SQLiteConnection.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT CINEMA_NAME \n" +
                    "    FROM CINEMA C INNER JOIN DISTRICT D\n" +
                    "    ON C.DISTRICT_ID = D.DISTRICT_ID \n" +
                    "    WHERE D.DISTRICT_ID = ?;")) {
            statement.setInt(1, district_id);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Query1 query1 = new Query1();
                query1.setName(rs.getString("CINEMA_NAME"));
                query1List.add(query1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

        return query1List;
    }
}
