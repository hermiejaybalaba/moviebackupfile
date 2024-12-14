package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void handleLogout(ActionEvent event) throws IOException {
        // Load the Login.fxml
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        // Get the stage from the event source (LOG_OUT button click)
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene (Login scene)
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
