package impls;

import DAO.GenreDAO;
import db.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Genre;
import utils.DialogManager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBGenre implements GenreDAO {

    private ObservableList<Genre> genreList = FXCollections.observableArrayList();

    @Override
    public boolean add(Genre object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("INSERT INTO GENRE(GENRE_NAME) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, object.getGenre_name());

            int result = statement.executeUpdate();
            if (result > 0) {
                int id = statement.getGeneratedKeys().getInt(1);// получить сгенерированный id вставленной записи
                object.setGenre_id(id);
                genreList.add(object);
                return true;
            }
        } catch (SQLException ex){
            Logger.getLogger(DBDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean update(Genre object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("UPDATE GENRE SET GENRE_NAME=? WHERE GENRE_ID=?")) {
            statement.setString(1, object.getGenre_name());
            statement.setInt(2, object.getGenre_id());
            int result = statement.executeUpdate();
            if (result > 0) {
                // обновление в коллекции происходит автоматически, после нажатия ОК в окне редактирования
                return true;
            }
        } catch (SQLException ex){
            Logger.getLogger(DBDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean delete(Genre object) {
        try (Connection con = SQLiteConnection.getConnection();
             Statement statement = con.createStatement()) {
            int result = statement.executeUpdate("DELETE FROM GENRE WHERE GENRE_ID="+object.getGenre_id());
            if (result > 0) {
                genreList.remove(object);
                return true;
            }

        } catch (SQLException ex){
            DialogManager.showErrorDialog("Ошибка", "Нарушена ссылочная целостность!");
        }

        return false;
    }

    @Override
    public ObservableList<Genre> findAll() {
        return find("");
    }

    @Override
    public ObservableList<Genre> find(String text) {
        genreList.clear();

        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM GENRE WHERE GENRE_NAME LIKE ? OR GENRE_ID LIKE ?")) {

            String searchStr = "%"+text+"%";

            statement.setString(1, searchStr);
            statement.setString(2, searchStr);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Genre genre  = new Genre();
                genre.setGenre_id(rs.getInt("GENRE_ID"));
                genre.setGenre_name(rs.getString("GENRE_NAME"));
                genreList.add(genre);
            }
        } catch (SQLException ex){
            Logger.getLogger(DBDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

        return genreList;
    }

    public ObservableList<Genre> getGenreList() {
        return genreList;
    }
}
