package impls;

import db.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Query1;
import objects.Query3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBQuery3 {

    ObservableList<Query3> query3List = FXCollections.observableArrayList();

    public ObservableList<Query3> findAll(int genre_id) {
        query3List.clear();
        try(Connection con = SQLiteConnection.getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT MOVIE_NAME\n" +
                    "    FROM MOVIE M INNER JOIN MOVIE_GENRE MG\n" +
                    "    ON M.MOVIE_ID = MG.MOVIE_ID\n" +
                    "    WHERE GENRE_ID=?;")) {
            statement.setInt(1, genre_id);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Query3 query3 = new Query3();
                query3.setName(rs.getString("MOVIE_NAME"));
                query3List.add(query3);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

        return query3List;
    }

}
