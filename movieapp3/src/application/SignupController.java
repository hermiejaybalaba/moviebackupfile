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

public class SignupController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField emailField;   // Email input field
    @FXML
    private TextField usernameField; // Username input field
    @FXML
    private PasswordField passwordField; // Password input field

    // Method to handle sign-up button click
    @FXML
    public void handleSignup(ActionEvent event) {
        // Validate the fields
        if (emailField.getText().isEmpty() || usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            // If any field is empty, show an error message
            showError("All fields must be filled!");
        } else {
            // If all fields are filled, show success message and navigate to login page
            showSuccess(event);
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

    // Method to show success message and navigate to login page
    private void showSuccess(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Registration successful!");
        alert.showAndWait();

        // Automatically navigate to login page after showing success
        try {
            // Load Login.fxml
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));

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

    // Method to switch to the login page manually (optional)
    @FXML
    public void switchToLogin(ActionEvent event) throws IOException {
        try {
            // Load Login.fxml
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));

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
