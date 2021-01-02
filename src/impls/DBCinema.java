package impls;

import DAO.CinemaDAO;
import db.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Cinema;
import utils.DialogManager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBCinema implements CinemaDAO {

    private ObservableList<Cinema> cinemaList = FXCollections.observableArrayList();

    @Override
    public boolean add(Cinema object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("INSERT INTO CINEMA(CINEMA_NAME, ADDRESS, PHONE_NUMBER, DISTRICT_ID) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, object.getCinema_name());
            statement.setString(2, object.getAddress());
            statement.setString(3, object.getPhone_number());
            statement.setInt(4, object.getDistrict_id());

            int result = statement.executeUpdate();
            if (result > 0) {
                int id = statement.getGeneratedKeys().getInt(1);// получить сгенерированный id вставленной записи
                object.setCinema_id(id);
                cinemaList.add(object);
                return true;
            }
        } catch (SQLException ex){
            Logger.getLogger(DBCinema.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean update(Cinema object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("UPDATE CINEMA SET CINEMA_NAME=?, ADDRESS=?, PHONE_NUMBER=?, DISTRICT_ID=? WHERE CINEMA_ID=?")) {
            statement.setString(1, object.getCinema_name());
            statement.setString(2, object.getAddress());
            statement.setString(3, object.getPhone_number());
            statement.setInt(4, object.getDistrict_id());
            statement.setInt(5, object.getCinema_id());
            int result = statement.executeUpdate();
            if (result > 0) {
                // обновление в коллекции происходит автоматически, после нажатия ОК в окне редактирования
                return true;
            }
        } catch (SQLException ex){
            Logger.getLogger(DBCinema.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean delete(Cinema object) {
        try (Connection con = SQLiteConnection.getConnection();
             Statement statement = con.createStatement()) {
            int result = statement.executeUpdate("DELETE FROM CINEMA WHERE CINEMA_ID="+object.getCinema_id());
            if (result > 0) {
                cinemaList.remove(object);
                return true;
            }
        } catch (SQLException ex){
            DialogManager.showErrorDialog("Ошибка", "Нарушена ссылочная целостность!");
        }

        return false;
    }

    @Override
    public ObservableList<Cinema> findAll() {
        return find("");
    }

    @Override
    public ObservableList<Cinema> find(String text) {
        cinemaList.clear();

        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM CINEMA WHERE CINEMA_ID LIKE ? OR CINEMA_NAME LIKE ?")) {

            String searchStr = "%"+text+"%";

            statement.setString(1, searchStr);
            statement.setString(2, searchStr);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Cinema cinema  = new Cinema();
                cinema.setCinema_id(rs.getInt("CINEMA_ID"));
                cinema.setCinema_name(rs.getString("CINEMA_NAME"));
                cinema.setAddress(rs.getString("ADDRESS"));
                cinema.setPhone_number(rs.getString("PHONE_NUMBER"));
                cinema.setDistrict_id(rs.getInt("DISTRICT_ID"));
                cinemaList.add(cinema);
            }
        } catch (SQLException ex){
            Logger.getLogger(DBCinema.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cinemaList;
    }

    public ObservableList<Cinema> getCinemaList() {
        return cinemaList;
    }
}
