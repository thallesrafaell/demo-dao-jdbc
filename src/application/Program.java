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
        System.out.println(seller);
        System.out.println();

        System.out.println("===== Test 2: seller findByDepartment =====");
        Department department = new Department(4,null);
        List<Seller> sellers = sellerDao.findByDepartment(department);
        sellers.stream().forEach(sellerFind -> System.out.println(sellerFind));
        System.out.println();

        System.out.println("===== Test 2: seller findAll =====");
        sellers = sellerDao.findAll();
        sellers.stream().forEach(sellerFind -> System.out.println(sellerFind));
        System.out.println();

        System.out.println("===== Test 4: seller insert =====");
        Seller newSeller = new Seller(null, "Gabriel Antônio", "gabriel@gmail.com", new Date(), 5000.00, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New Seller with id: " + newSeller.getId());
        System.out.println();

        System.out.println("===== Test 5: seller update =====");
        seller = sellerDao.findById(1);
        seller.setName("Marta Waine");
        sellerDao.update(seller);
        System.out.println("Update Completed!");





    }
}
