package DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    List<T> get(int id) throws SQLException;

    List<T> getAll(int id) throws SQLException;

    void save(T t) throws SQLException;

    void update(String[] params) throws SQLException;

    void delete(T t) throws SQLException;
}
