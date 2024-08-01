package fr.tln.univ.designPatterns.proxy;

import java.sql.SQLException;

public interface DepartmentDao {
    DBConnection getDbConnection();
    void insertNewDepartment(int depId, String departmentName) throws SQLException;
    String queryDepartment(int depId) throws SQLException;
}
