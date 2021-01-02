package impls;

import DAO.MovieDAO;
import db.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Movie;
import utils.DialogManager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBMovie implements MovieDAO {
    private ObservableList<Movie> movieList = FXCollections.observableArrayList();

    @Override
    public boolean add(Movie object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("INSERT INTO MOVIE(MOVIE_NAME, RELEASE, DURATION, RATING) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, object.getMovie_name());
            statement.setInt(2, object.getRelease());
            statement.setInt(3, object.getDuration());
            statement.setFloat(4, object.getRating());

            int result = statement.executeUpdate();
            if (result > 0) {
                int id = statement.getGeneratedKeys().getInt(1);// получить сгенерированный id вставленной записи
                object.setMovie_id(id);
                movieList.add(object);
                return true;
            }
        } catch (SQLException ex){
            DialogManager.showErrorDialog("Ошибка", "Нарушена целостность данных!");
        }

        return false;
    }

    @Override
    public boolean update(Movie object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("UPDATE MOVIE SET MOVIE_NAME=?, RELEASE=?, DURATION=?, RATING=? WHERE MOVIE_ID=?")) {
            statement.setString(1, object.getMovie_name());
            statement.setInt(2, object.getRelease());
            statement.setInt(3, object.getDuration());
            statement.setFloat(4, object.getRating());
            statement.setInt(5, object.getMovie_id());
            int result = statement.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (SQLException ex){
            DialogManager.showErrorDialog("Ошибка", "Нарушена целостность данных!");
        }

        return false;
    }

    @Override
    public boolean delete(Movie object) {
        try (Connection con = SQLiteConnection.getConnection();
             Statement statement = con.createStatement()) {
            int result = statement.executeUpdate("DELETE FROM MOVIE WHERE MOVIE_ID="+object.getMovie_id());
            if (result > 0) {
                movieList.remove(object);
                return true;
            }
        } catch (SQLException ex){
            DialogManager.showErrorDialog("Ошибка", "Нарушена ссылочная целостность!");
        }

        return false;
    }

    @Override
    public ObservableList<Movie> findAll() {
        return find("");
    }

    @Override
    public ObservableList<Movie> find(String text) {
        movieList.clear();

        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM MOVIE WHERE MOVIE_ID LIKE ? OR MOVIE_NAME LIKE ?")) {

            String searchStr = "%"+text+"%";

            statement.setString(1, searchStr);
            statement.setString(2, searchStr);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Movie movie  = new Movie();
                movie.setMovie_id(rs.getInt("MOVIE_ID"));
                movie.setMovie_name(rs.getString("MOVIE_NAME"));
                movie.setRelease(rs.getInt("RELEASE"));
                movie.setDuration(rs.getInt("DURATION"));
                movie.setRating(rs.getFloat("RATING"));
                movieList.add(movie);
            }
        } catch (SQLException ex){
            Logger.getLogger(DBCinema.class.getName()).log(Level.SEVERE, null, ex);
        }

        return movieList;
    }

    public ObservableList<Movie> getMovieList() {
        return movieList;
    }
}
