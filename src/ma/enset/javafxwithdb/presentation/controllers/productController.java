package ma.enset.javafxwithdb.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.javafxwithdb.dao.CategoryDaoImpl;
import ma.enset.javafxwithdb.dao.ProductDaoImpl;
import ma.enset.javafxwithdb.dao.entities.Category;
import ma.enset.javafxwithdb.dao.entities.Product;
import ma.enset.javafxwithdb.service.CatalogueService;
import ma.enset.javafxwithdb.service.CatalogueServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class productController implements Initializable {

    @FXML
    private TextField textNom;
    @FXML
    private TextField textRef;
    @FXML
    private TextField textPrix;
    @FXML
    private TextField textSearch;
    @FXML
    private ComboBox<Category> comboCategory;
    @FXML
    private TableView<Product> tableViewProd;
    @FXML
    private TableColumn<Long,Product> columnId;
    @FXML
    private TableColumn<String,Product>columnName;
    @FXML
    private TableColumn<String,Product> columnRef;
    @FXML
    private TableColumn<Float,Product> columnPrice;
    @FXML
    private TableColumn<Category,Product> columnCategory;

    private CatalogueService catalogueService;
    private Product selectedProduct;

    ObservableList<Product> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewProd.setItems(data);
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        catalogueService = new CatalogueServiceImpl(new ProductDaoImpl(),new CategoryDaoImpl());
        comboCategory.getItems().addAll(catalogueService.getAllCategories());
        loadData();

        textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            data.clear();
            if(newValue.equals("")){
                loadData();
            }else{
                data.addAll(catalogueService.searchProductByQuery(newValue));
            }
        });
    }
    private void loadData(){
        data.clear();
        data.addAll(catalogueService.getAllProducts());
    }

    public void addProduct(){
        Product p = new Product();

        p.setName(textNom.getText());
        p.setReference(textRef.getText());
        p.setPrice(Float.parseFloat(textPrix.getText()));
        p.setCategory(comboCategory.getSelectionModel().getSelectedItem());
        catalogueService.addProduct(p);

        clearAll();
        loadData();
    }

    public void updateProduct() {
        Product p = new Product();
        p.setId(selectedProduct.getId());
        p.setName(textNom.getText());
        p.setReference(textRef.getText());
        p.setPrice(Float.parseFloat(textPrix.getText()));
        p.setCategory(comboCategory.getSelectionModel().getSelectedItem());
        catalogueService.updateProduct(p);


        loadData();
    }

    public void deleteProduct() {
        Product p = tableViewProd.getSelectionModel().getSelectedItem();
        if(p==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("No Product Selected");
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you Sure you want to Delete "+p.getName()+" (REFERENCE: "+p.getReference()+" )");
            alert.setHeaderText("Confirm Deletion");
            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
            if (result == ButtonType.OK) {
                catalogueService.deleteProduct(p);
            } else {
                Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
                alertInfo.setContentText("Deletion cancelled.");
                alertInfo.show();
            }

        }

        clearAll();
        loadData();
    }

    public void loadOnClick(){
        Product p = tableViewProd.getSelectionModel().getSelectedItem();
        if(p!=null){
            textNom.setText(p.getName());
            textRef.setText(p.getReference());
            textPrix.setText(String.valueOf(p.getPrice()));
            //comboCategory.selectionModelProperty().setValue();
            selectedProduct = p;
        }

    }
    public void clearAll(){
        textNom.setText("");
        textPrix.setText("");
        textRef.setText("");
    }
}
