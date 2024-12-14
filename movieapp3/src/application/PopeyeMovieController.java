package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PopeyeMovieController implements Initializable {
    @FXML private ImageView imgMovie1; 
    @FXML private Label titlemovieprint1;
    @FXML private DatePicker datePicker;
    @FXML private TextField timeTextField;
    @FXML private Button butTicketsButton;
    @FXML private ListView<String> timeListView;
    
    private ObservableList<String> timeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // List of available dates
        List<LocalDate> availableDates = Arrays.asList(
                LocalDate.of(2024, 12, 17),
                LocalDate.of(2024, 12, 18),
                LocalDate.of(2024, 12, 19),
                LocalDate.of(2024, 12, 20)
        );

        // Set the value for the DatePicker to null (no selection initially)
        datePicker.setValue(null);

        // Disable all dates except the available ones
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(!availableDates.contains(date));  // Disable non-available dates
            }
        });

        // Populate the ListView with initial available times
        timeList.addAll("8-11 AM", "1-4 PM", "5-8 PM");
        timeListView.setItems(timeList);

        // Initially hide the ListView and disable the button
        timeListView.setVisible(false);
        butTicketsButton.setDisable(true);  // Disable button initially

        // Add listener for selecting an item in the ListView
        timeListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                timeTextField.setText(newValue);  // Set selected time in TextField
                timeListView.setVisible(false);   // Hide ListView after selection
                butTicketsButton.toFront(); // Bring the button to the front after time selection
                checkButtonState(); // Check button state after selection
            }
        });

        // Detect when the TextField is cleared
        timeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                resetTimeSelection();
            }
        });

        // Detect when a date is selected in the DatePicker
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkButtonState();  // Check button state when date is selected or changed
        });
    }

    // Method to check if both date and time are selected
    private void checkButtonState() {
        LocalDate selectedDate = datePicker.getValue();
        String selectedTime = timeTextField.getText();

        // Enable the button only if both date and time are selected
        if (selectedDate != null && !selectedTime.isEmpty()) {
            butTicketsButton.setDisable(false);  // Enable button
        } else {
            butTicketsButton.setDisable(true);   // Disable button
        }
    }

    // Method to show the ListView when the TextField is clicked
    @FXML
    private void onTextFieldClick() {
        // Show the ListView when TextField is clicked
        timeListView.setVisible(true);
        butTicketsButton.toBack(); // Move the button to the back when the ListView is shown
    }

    // Method to reset time selection and return to default TextField state
    private void resetTimeSelection() {
        // Hide the ListView and reset TextField to prompt text
        timeListView.setVisible(false);
        timeTextField.setText(""); // Clear the TextField
        butTicketsButton.toFront(); // Bring the button to the front if the time selection is reset
    }

    // Method to open the BuyTickets FXML file and pass the selected date and time
    @FXML
    private void openBuyTicketsFXML2() {
        try {
            // Assuming you use FXMLLoader to load the BuyTickets FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BuyTickets.fxml"));
            Parent root = loader.load();
            BuyTicketsController buyTicketsController = loader.getController();
            
            // Pass the selected date and time to the BuyTicketsController
            buyTicketsController.setSelectedDate(datePicker.getValue().toString());
            buyTicketsController.setSelectedTime(timeTextField.getText());
            buyTicketsController.setMovieTitle("POPEYE");
            buyTicketsController.setMovieImage("mov2.png"); 
            
            // Show the BuyTickets FXML
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent homeRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(homeRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backToHome(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent homeRoot = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(homeRoot);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
