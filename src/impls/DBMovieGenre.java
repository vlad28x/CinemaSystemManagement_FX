package impls;

import DAO.MovieGenreDAO;
import db.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.MovieGenre;
import utils.DialogManager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBMovieGenre implements MovieGenreDAO {

    private ObservableList<MovieGenre> movieGenreList = FXCollections.observableArrayList();

    @Override
    public boolean add(MovieGenre object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("INSERT INTO MOVIE_GENRE(MOVIE_ID, GENRE_ID) VALUES (?, ?)")) {
            statement.setInt(1, object.getMovie_id());
            statement.setInt(2, object.getGenre_id());

            int result = statement.executeUpdate();
            if (result > 0) {
                movieGenreList.add(object);
                return true;
            }
        } catch (SQLException ex){
            Logger.getLogger(DBDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean update(MovieGenre object, int movie_id, int genre_id) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("UPDATE MOVIE_GENRE SET MOVIE_ID=?, GENRE_ID=? WHERE MOVIE_ID =? AND GENRE_ID=?")) {
            statement.setInt(1, object.getMovie_id());
            statement.setInt(2, object.getGenre_id());
            statement.setInt(3, movie_id);
            statement.setInt(4, genre_id);
            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException ex){
            Logger.getLogger(DBDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean delete(MovieGenre object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("DELETE FROM MOVIE_GENRE WHERE MOVIE_ID=? AND GENRE_ID=?")) {
            statement.setInt(1, object.getMovie_id());
            statement.setInt(2, object.getGenre_id());
            int result = statement.executeUpdate();
            if (result > 0) {
                movieGenreList.remove(object);
                return true;
            }
        } catch (SQLException ex){
            DialogManager.showErrorDialog("Ошибка", "Нарушена ссылочная целостность!");
        }

        return false;
    }

    public boolean check(int movie_id, int genre_id) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM MOVIE_GENRE WHERE MOVIE_ID=? AND GENRE_ID=?")) {
            statement.setInt(1, movie_id);
            statement.setInt(2, genre_id);

            ResultSet rs = statement.executeQuery();

            if(rs.next()) return true;

        } catch (SQLException ex) {
            Logger.getLogger(DBHall.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public ObservableList<MovieGenre> findAll() {
        return find("");
    }

    @Override
    public ObservableList<MovieGenre> find(String text) {
        movieGenreList.clear();

        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM MOVIE_GENRE WHERE MOVIE_ID LIKE ? OR GENRE_ID LIKE ?")) {

            String searchStr = "%"+text+"%";

            statement.setString(1, searchStr);
            statement.setString(2, searchStr);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                MovieGenre movieGenre = new MovieGenre();
                movieGenre.setMovie_id(rs.getInt("MOVIE_ID"));
                movieGenre.setGenre_id(rs.getInt("GENRE_ID"));
                movieGenreList.add(movieGenre);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBHall.class.getName()).log(Level.SEVERE, null, ex);
        }

        return movieGenreList;
    }

    public ObservableList<MovieGenre> getMovieGenreList() {
        return movieGenreList;
    }
}
