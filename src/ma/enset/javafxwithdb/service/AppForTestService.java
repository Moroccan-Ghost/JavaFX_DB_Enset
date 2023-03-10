package ma.enset.javafxwithdb.service;

import ma.enset.javafxwithdb.dao.CategoryDAO;
import ma.enset.javafxwithdb.dao.CategoryDaoImpl;
import ma.enset.javafxwithdb.dao.ProductDAO;
import ma.enset.javafxwithdb.dao.ProductDaoImpl;
import ma.enset.javafxwithdb.dao.entities.Category;
import ma.enset.javafxwithdb.dao.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class AppForTestService {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDaoImpl();
        CategoryDAO categoryDAO = new CategoryDaoImpl();
        CatalogueService catalogueService = new CatalogueServiceImpl(productDAO,categoryDAO);
        Category c1 = new Category();
        c1.setName("GAMING");
        c1.setId(1);

        Product p1 = new Product();
        p1.setName("MSI");
        p1.setReference("MSI-001");
        p1.setPrice(40200);
        p1.setCategory(c1);

        //catalogueService.addProduct(p1);
        List<Product> products = catalogueService.getAllProducts();
        for (Product p: products
             ) {
            System.out.println(p.toString());
        }

    }
}
