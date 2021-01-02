package DAO;

import javafx.collections.ObservableList;
import objects.MovieGenre;

import java.sql.SQLException;

public interface MovieGenreDAO{
    boolean add(MovieGenre object) throws SQLException;

    boolean update(MovieGenre object, int movie_id, int genre_id);

    boolean delete(MovieGenre object);

    ObservableList<MovieGenre> findAll();

    ObservableList<MovieGenre> find(String text);
}
