package model.dao.impl;

import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }
    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                   " SELECT seller.*,department.Name as DepName "
                     + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("DepartmentId"));
                department.setNome(rs.getString("DepName"));
                Seller sellerFind = new Seller();
                sellerFind.setId(rs.getInt("Id"));
                sellerFind.setName(rs.getString("Name"));
                sellerFind.setEmail(rs.getString("Email"));
                sellerFind.setBaseSalary(rs.getDouble("BaseSalary"));
                sellerFind.setBirthDate(rs.getDate("BirthDate"));
                sellerFind.setDepartment(department);
                return sellerFind;
            }
            return null;

        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
