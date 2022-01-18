package DAO;

import model.SchoolSubject;

import java.sql.SQLException;
import java.util.List;

public class DAOSchoolSubject implements DAO<SchoolSubject> {
    @Override
    public List<SchoolSubject> get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<SchoolSubject> getAll() throws SQLException {
        return null;
    }

    @Override
    public void save(SchoolSubject subject) throws SQLException {

    }

    @Override
    public void update(SchoolSubject subject) throws SQLException {

    }

    @Override
    public void delete(SchoolSubject subject) throws SQLException {

    }
}
