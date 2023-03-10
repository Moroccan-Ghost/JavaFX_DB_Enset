package ma.enset.javafxwithdb.dao;

import ma.enset.javafxwithdb.dao.entities.Product;

import java.util.List;

public interface ProductDAO extends DAO<Product>{
    List<Product> findByQuery(String query);

}
