package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;

    }
    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                "INSERT INTO department "
                    + "(Name) "
                    + "VALUES (?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
        }
        catch (SQLException e){throw new DbException(e.getMessage());
        }
        finally {DB.closeStatement(st);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "UPDATE department "
                    + "SET Name= ? "
                    + "WHERE Id= ?"
            );
            st.setString(1, obj.getNome());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        }
        catch (SQLException e){ throw new DbException(e.getMessage());}
        finally {DB.closeStatement(st);
        }

    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "DELETE FROM department WHERE Id = ?"

            );

            st.setInt(1, id);
            st.executeUpdate();

        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
           st = conn.prepareStatement("SELECT * FROM department WHERE id = ?");
           st.setInt(1, id);
           rs = st.executeQuery();

           if(rs.next()){
               Department dpFound = instanciateDeparment(rs);
               return dpFound;
           }
           return null;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM department");
            rs = st.executeQuery();

            List<Department> list = new ArrayList<>();

            while (rs.next()){
                Department department = instanciateDeparment(rs);
                list.add(department);
            }
            return list;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    private Department instanciateDeparment(ResultSet rs) throws SQLException{
        Department dp = new Department();
        dp.setId(rs.getInt("Id"));
        dp.setNome(rs.getString("Name"));
        return dp;

    }
}
