package DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    /** function to get object with suitable id from database
     * @param id
     * @return List of this objects
     * @throws SQLException
     */
    List<T> get(int id) throws SQLException;

    /** function to get all objects from this table from databse
     * @return List of this objects
     * @throws SQLException
     */
    List<T> getAll() throws SQLException;

    /** function to add new record in database
     * @param t as suitable object
     * @throws SQLException
     */
    void save(T t) throws SQLException;

    /** function to update record in database
     * @param t as suitable object
     * @throws SQLException
     */
    void update(T t) throws SQLException;

    /** function to delete record in database
     * @param t as suitable object
     * @throws SQLException
     */
    void delete(T t) throws SQLException;
}
