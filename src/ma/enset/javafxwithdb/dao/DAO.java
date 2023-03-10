package ma.enset.javafxwithdb.dao;

import java.util.List;

public interface DAO <T>{
    T find(long id);
    List<T> findAll();
    void save(T o);
    void delete(T o);
    void update(T o);

}
