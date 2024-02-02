package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;

public class Program2 {
    public static void main(String[] args) {

      DepartmentDao departmentDao = DaoFactory.createDepartamentDao();

        System.out.println("===== Test 1 : Department findById =====");
         Department departmentFound = departmentDao.findById(6);
        System.out.println(departmentFound);

    }
}
