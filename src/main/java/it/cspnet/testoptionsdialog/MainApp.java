package it.cspnet.testoptionsdialog;

import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
        private ResourceBundle resources;
    @Override
    public void start(Stage stage) throws Exception {
        resources = ResourceBundle.getBundle("strings/Paroliere_strings");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/optionsDialog_1.fxml"), resources);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("OptionsDialog Test");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
