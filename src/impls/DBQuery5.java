package impls;

import db.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Query5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBQuery5 {

    ObservableList<Query5> query5List = FXCollections.observableArrayList();

    public ObservableList<Query5> findAll(int rating) {
        query5List.clear();
        try(Connection con = SQLiteConnection.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT DISTINCT MOVIE_NAME\n" +
                    "    FROM MOVIE M INNER JOIN MOVIE_GENRE MG\n" +
                    "    ON M.MOVIE_ID = MG.MOVIE_ID\n" +
                    "    INNER JOIN GENRE G\n" +
                    "    ON MG.GENRE_ID = G.GENRE_ID\n" +
                    "    WHERE RATING >= ?;\n")) {
            statement.setInt(1, rating);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Query5 query5 = new Query5();
                query5.setName(rs.getString("MOVIE_NAME"));
                query5List.add(query5);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

        return query5List;
    }
}
