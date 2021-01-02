package impls;

import DAO.HallDAO;
import db.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Hall;
import utils.DialogManager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBHall implements HallDAO {

    private ObservableList<Hall> hallList = FXCollections.observableArrayList();

    @Override
    public boolean add(Hall object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("INSERT INTO HALL(CAPACITY, CINEMA_ID) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, object.getCapacity());
            statement.setInt(2, object.getCinema_id());

            int result = statement.executeUpdate();
            if (result > 0) {
                int id = statement.getGeneratedKeys().getInt(1);// получить сгенерированный id вставленной записи
                object.setHall_id(id);
                hallList.add(object);
                return true;
            }
        } catch (SQLException ex){
            DialogManager.showErrorDialog("Ошибка", "Нарушена целостность данных!");
        }

        return false;
    }

    @Override
    public boolean update(Hall object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("UPDATE HALL SET CAPACITY=?, CINEMA_ID=? WHERE HALL_ID=?", Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, object.getCapacity());
            statement.setInt(2, object.getCinema_id());
            statement.setInt(3, object.getHall_id());

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
    public boolean delete(Hall object) {
        try (Connection con = SQLiteConnection.getConnection();
             Statement statement = con.createStatement()) {
            int result = statement.executeUpdate("DELETE FROM HALL WHERE HALL_ID="+object.getHall_id());
            if (result > 0) {
                hallList.remove(object);
                return true;
            }
        } catch (SQLException ex){
            DialogManager.showErrorDialog("Ошибка", "Нарушена ссылочная целостность!");
        }

        return false;
    }

    @Override
    public ObservableList<Hall> findAll() {
        return find("");
    }

    @Override
    public ObservableList<Hall> find(String text) {
        hallList.clear();

        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM HALL WHERE HALL_ID LIKE ? OR CAPACITY LIKE ?")) {

            String searchStr = "%"+text+"%";

            statement.setString(1, searchStr);
            statement.setString(2, searchStr);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Hall hall = new Hall();
                hall.setHall_id(rs.getInt("HALL_ID"));
                hall.setCapacity(rs.getInt("CAPACITY"));
                hall.setCinema_id(rs.getInt("CINEMA_ID"));
                hallList.add(hall);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBHall.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hallList;
    }

    public ObservableList<Hall> getHallList() {
        return hallList;
    }
}
