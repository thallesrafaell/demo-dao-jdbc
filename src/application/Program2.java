package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
import model.entities.Department;

import java.util.List;

public class Program2 {
    public static void main(String[] args) {

      DepartmentDao departmentDao = DaoFactory.createDepartamentDao();

        System.out.println("===== Test 1 : Department findById =====");
        Department departmentFound = departmentDao.findById(6);
        System.out.println(departmentFound);
        System.out.println();

        System.out.println("===== Test 2 : Department findById =====");
        List<Department> departmentsList = departmentDao.findAll();
        departmentsList.stream().forEach(department -> System.out.println(department));
        System.out.println();

        System.out.println("===== Test 3 : Department Delete =====");
        departmentDao.deleteById(6);
        System.out.println("Deleted completed!");
        System.out.println();

        System.out.println("===== Test 4 : Department Update =====");
        Department department =new Department(4, "Computer");
        departmentDao.update(department);
        departmentFound = departmentDao.findById(4);
        System.out.println(departmentFound);
    }
}
