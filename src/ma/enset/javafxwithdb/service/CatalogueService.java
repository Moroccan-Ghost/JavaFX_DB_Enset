package ma.enset.javafxwithdb.service;

import ma.enset.javafxwithdb.dao.entities.Category;
import ma.enset.javafxwithdb.dao.entities.Product;

import java.util.List;

public interface CatalogueService {
    List<Product> getAllProducts();
    List<Product> searchProductByQuery(String query);
    void addProduct(Product p);
    void updateProduct(Product p);
    void deleteProduct(Product p);

    List<Category> getAllCategories();
    void addCategory(Category c);
    void updateCategory(Category c);
    void deleteCategory(Category c);

}
