package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    private Stage stage;
    private Scene scene;	
    private Parent root;

    @FXML
    private TextField usernameField;   // Username input field
    @FXML
    private PasswordField passwordField; // Password input field

    // Method to handle login button click
    @FXML
    public void handleLogin(ActionEvent event) {
        // Get the input values
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Check if the username and password match the correct ones
        if (username.equals("123") && password.equals("123")) {
            // If correct, show success message and navigate to the dashboard
            showSuccess(event);
        } else {
            // If incorrect, show error message
            showError("You are not registered. Please create an account.");
        }
    }

    // Method to show error message
    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to show success message and navigate to dashboard
    private void showSuccess(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Login Successful!");
        alert.showAndWait();

        // Navigate to Dashboard.fxml
        try {
            // Load Dashboard.fxml
            root = FXMLLoader.load(getClass().getResource("Home.fxml"));

            // Get the stage from the event source
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to switch to the signup page
    @FXML
    public void switchToSignup(ActionEvent event) throws IOException {
        try {
            // Load Signup.fxml
            root = FXMLLoader.load(getClass().getResource("Signup.fxml"));

            // Get the stage from the event source
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
