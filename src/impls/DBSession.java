package impls;

import DAO.SessionDAO;
import db.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Session;
import utils.DialogManager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBSession implements SessionDAO {

    private ObservableList<Session> sessionList = FXCollections.observableArrayList();

    @Override
    public boolean add(Session object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("INSERT INTO SESSION (DATE_TIME, NUMBER_FREE_PLACES, HALL_ID, MOVIE_ID) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, object.getDate_time());
            statement.setInt(2, object.getNumber_free_places());
            statement.setInt(3, object.getHall_id());
            statement.setInt(4, object.getMovie_id());

            int result = statement.executeUpdate();
            if (result > 0) {
                int id = statement.getGeneratedKeys().getInt(1);
                object.setSession_id(id);
                sessionList.add(object);
                return true;
            }
        } catch (SQLException ex){
            DialogManager.showErrorDialog("Ошибка", "Нарушена целостность данных!");
        }

        return false;
    }

    @Override
    public boolean update(Session object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("UPDATE SESSION SET DATE_TIME=?, NUMBER_FREE_PLACES=?, HALL_ID=?, MOVIE_ID=? WHERE SESSION_ID=?", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, object.getDate_time());
            statement.setInt(2, object.getNumber_free_places());
            statement.setInt(3, object.getHall_id());
            statement.setInt(4, object.getMovie_id());
            statement.setInt(5, object.getSession_id());

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
    public boolean delete(Session object) {
        try (Connection con = SQLiteConnection.getConnection();
             Statement statement = con.createStatement()) {
            int result = statement.executeUpdate("DELETE FROM SESSION WHERE SESSION_ID="+object.getSession_id());
            if (result > 0) {
                sessionList.remove(object);
                return true;
            }
        } catch (SQLException ex){
            DialogManager.showErrorDialog("Ошибка", "Нарушена ссылочная целостность!");
        }

        return false;
    }

    @Override
    public ObservableList<Session> findAll() {
        return find("");
    }

    @Override
    public ObservableList<Session> find(String text) {
        sessionList.clear();

        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM SESSION WHERE SESSION_ID LIKE ? OR DATE_TIME LIKE ?")) {

            String searchStr = "%"+text+"%";

            statement.setString(1, searchStr);
            statement.setString(2, searchStr);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Session session = new Session();
                session.setSession_id(rs.getInt("SESSION_ID"));
                session.setDate_time(rs.getString("DATE_TIME"));
                session.setNumber_free_places(rs.getInt("NUMBER_FREE_PLACES"));
                session.setHall_id(rs.getInt("HALL_ID"));
                session.setMovie_id(rs.getInt("MOVIE_ID"));
                sessionList.add(session);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBHall.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sessionList;
    }

    public boolean check(int hall_id, int number_free_places) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT CAPACITY FROM HALL WHERE HALL_ID=?")) {
            statement.setInt(1, hall_id);

            ResultSet rs = statement.executeQuery();
            rs.next();
            if(number_free_places > rs.getInt("CAPACITY")) return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHall.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public ObservableList<Session> getSessionList() {
        return sessionList;
    }
}
