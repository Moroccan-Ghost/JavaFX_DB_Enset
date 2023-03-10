package ma.enset.javafxwithdb.service;

import ma.enset.javafxwithdb.dao.CategoryDAO;
import ma.enset.javafxwithdb.dao.ProductDAO;
import ma.enset.javafxwithdb.dao.entities.Category;
import ma.enset.javafxwithdb.dao.entities.Product;

import java.util.List;

public class CatalogueServiceImpl implements CatalogueService{
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    public CatalogueServiceImpl(ProductDAO productDAO, CategoryDAO categoryDAO) {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> searchProductByQuery(String query) {
        return productDAO.findByQuery(query);
    }

    @Override
    public void addProduct(Product p) {
        productDAO.save(p);
    }

    @Override
    public void updateProduct(Product p) {
        productDAO.update(p);
    }

    @Override
    public void deleteProduct(Product p) {
        productDAO.delete(p);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    @Override
    public void addCategory(Category c) {
        categoryDAO.save(c);
    }

    @Override
    public void updateCategory(Category c) {
        categoryDAO.update(c);
    }

    @Override
    public void deleteCategory(Category c) {
        categoryDAO.delete(c);
    }
}
