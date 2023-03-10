package ma.enset.javafxwithdb.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppJavaFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = FXMLLoader.load(getClass().getResource("views/productView.fxml"));
        Scene scene = new Scene(root,920,600);
        scene.getStylesheets().add(getClass().getResource("style/style.css").toString());

        primaryStage.setScene(scene);
        primaryStage.setTitle("Products Management");
        primaryStage.show();
    }
}
