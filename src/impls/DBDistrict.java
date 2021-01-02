package impls;

import DAO.DistrictDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.District;
import db.SQLiteConnection;
import utils.DialogManager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBDistrict implements DistrictDAO {

    private ObservableList<District> districtList = FXCollections.observableArrayList();

    @Override
    public boolean add(District object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("INSERT INTO DISTRICT(DISTRICT_NAME) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, object.getDistrict_name());

            int result = statement.executeUpdate();
            if (result > 0) {
                int id = statement.getGeneratedKeys().getInt(1);// получить сгенерированный id вставленной записи
                object.setDistrict_id(id);
                districtList.add(object);
                return true;
            }
        } catch (SQLException ex){
            Logger.getLogger(DBDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean update(District object) {
        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("UPDATE DISTRICT SET DISTRICT_NAME=? WHERE DISTRICT_ID=?")) {
            statement.setString(1, object.getDistrict_name());
            statement.setInt(2, object.getDistrict_id());
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
    public boolean delete(District object) {
        try (Connection con = SQLiteConnection.getConnection();
             Statement statement = con.createStatement()) {
            int result = statement.executeUpdate("DELETE FROM DISTRICT WHERE DISTRICT_ID="+object.getDistrict_id());
            if (result > 0) {
                districtList.remove(object);
                return true;
            }

        } catch (SQLException ex){
            DialogManager.showErrorDialog("Ошибка", "Нарушена ссылочная целостность!");
        }

        return false;
    }

    @Override
    public ObservableList<District> findAll() {
        return find("");
    }

    @Override
    public ObservableList<District> find(String text) {
        districtList.clear();

        try (Connection con = SQLiteConnection.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM DISTRICT WHERE DISTRICT_NAME LIKE ? OR DISTRICT_ID LIKE ?")) {

            String searchStr = "%"+text+"%";

            statement.setString(1, searchStr);
            statement.setString(2, searchStr);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                District district  = new District();
                district.setDistrict_id(rs.getInt("DISTRICT_ID"));
                district.setDistrict_name(rs.getString("DISTRICT_NAME"));
                districtList.add(district);
            }
        } catch (SQLException ex){
            Logger.getLogger(DBDistrict.class.getName()).log(Level.SEVERE, null, ex);
        }

        return districtList;
    }


    public ObservableList<District> getDistrictList() {
        return districtList;
    }
}
