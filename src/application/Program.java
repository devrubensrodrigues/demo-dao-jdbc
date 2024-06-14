package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1: seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== TEST 2: seller findByDepartment ===");
		List<Seller> list = sellerDao.findByDepartment(new Department(2, null));
		list.forEach(System.out::println);
		
		System.out.println("\n=== TEST 3: seller findAll ===");
		List<Seller> listAll = sellerDao.findAll();
		listAll.forEach(System.out::println);
		
		System.out.println("\n=== TEST 4: seller insert ===");
		Seller sellerInsert = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, new Department(2, null));
		sellerDao.insert(sellerInsert);
		System.out.println("Inserted! New id = " + sellerInsert.getId());

		System.out.println("\n=== TEST 5: seller update ===");
		seller = sellerDao.findById(1);
		seller.setName("Marthe Waine");
		sellerDao.update(seller);
		System.out.println("Update completed!");
		
		System.out.println("\n=== TEST 6: seller delete ===");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		boolean exists = listAll.stream()
		.anyMatch(x -> x.getId() == id);
		if(exists) {
			System.out.println("The row has been deleted!!");
		}
		else {
			System.out.println("The row does not exist!!");
		}
		sc.close();
	}
}
