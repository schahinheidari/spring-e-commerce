package fr.tln.univ.designPatterns.proxy;

import java.sql.SQLException;

public class DepartmentDaoImpl implements DepartmentDao{
    @Override
    public DBConnection getDbConnection() {
        return null;
    }

    @Override
    public void insertNewDepartment(int depId, String departmentName) throws SQLException {

    }

    @Override
    public String queryDepartment(int depId) throws SQLException {
        return "";
    }
}
