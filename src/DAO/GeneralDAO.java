package DAO;

import javafx.collections.ObservableList;

public interface GeneralDAO<T> {

    boolean add(T object);

    boolean update(T object);

    boolean delete(T object);

    ObservableList<T> findAll();

    ObservableList<T> find(String text);
}
