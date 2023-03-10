package ma.enset.javafxwithdb.dao;

import ma.enset.javafxwithdb.dao.entities.Category;
import ma.enset.javafxwithdb.dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDAO{
    @Override
    public Category find(long id) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        Connection connection = ConnectionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from CATEGORY");
            ResultSet rs =  pstm.executeQuery();
            while (rs.next()){
                Category c = new Category();
                c.setId(rs.getLong("ID_CAT"));
                c.setName(rs.getString("NAME"));
                categories.add(c);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public void save(Category o) {
        Connection connection = ConnectionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into CATEGORY(NAME) VALUES(?) ");
            pstm.setString(1,o.getName());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Category o) {
        Connection connection = ConnectionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("delete from CATEGORY where ID_CAT = ?");
            pstm.setLong(1,o.getId());
            pstm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category o) {
        Connection connection = ConnectionDBSingleton.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("update CATEGORY set NAME=? where ID_CAT = ?");
            pstm.setString(1,o.getName());
            pstm.setLong(2,o.getId());
            pstm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
