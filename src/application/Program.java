package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class Program {

    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("===== Test 1: seller findById =====");
        Seller seller = sellerDao.findById(7);
        System.out.println();
        System.out.println(seller);
        System.out.println();

        System.out.println("===== Test 1: seller findById =====");
        Department department = new Department(4,null);
        List<Seller> sellers = sellerDao.findByDepartment(department);
        sellers.stream().forEach(sellerFind -> System.out.println(sellerFind));


    }
}
