package co.uniquindio.marketplacefx.marketplaceapp;

import co.uniquindio.marketplacefx.marketplaceapp.viewcontroller.MarketPlaceViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MarketPlaceApplication extends Application {
    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"),800,500);
        stage.setResizable(true);
        stage.setScene(scene);
        stage.show();
    }

    //metodo para abrir varios screen por el nombre del archivo fxml
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    //metodo para abrir varios screen por el nombre del archivo fxml
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MarketPlaceApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}