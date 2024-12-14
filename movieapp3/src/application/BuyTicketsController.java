
package application;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BuyTicketsController {

    // FXML Bindings for ticketing
    @FXML
    private ComboBox<Integer> adultComboBox;

    @FXML
    private ComboBox<Integer> childComboBox;

    @FXML
    private ComboBox<Integer> seniorComboBox;

    @FXML
    private ToggleButton vipUpgradeToggle;

    @FXML
    private Label totalLabel;

    // Labels for selected date and time
    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;
    @FXML 
    private Label titlemovieprint;
    @FXML 
    private Label titlemovieprint1;
    
	@FXML
    private ImageView imgMovie;
	@FXML
    private ImageView imgMovie1;

	
    // Ticket prices
    private final double ADULT_PRICE = 10.00;
    private final double CHILD_PRICE = 8.00;
    private final double SENIOR_PRICE = 6.00;
    private final double VIP_UPGRADE_PRICE = 2.00;

    // Ticket quantities
    private int adultQuantity;
    private int childQuantity;
    private int seniorQuantity;

    // Method to initialize the ComboBoxes and set default values
    @FXML
    public void initialize() {
        // Populate ComboBoxes with quantities (0 to 5)
        for (int i = 0; i <= 5; i++) {
            adultComboBox.getItems().add(i);
            childComboBox.getItems().add(i);
            seniorComboBox.getItems().add(i);
        }

        // Set default value for ComboBoxes
        adultComboBox.setValue(0);
        childComboBox.setValue(0);
        seniorComboBox.setValue(0);

        // Add listeners to update total whenever a selection is made
        adultComboBox.setOnAction(event -> updateTotal());
        childComboBox.setOnAction(event -> updateTotal());
        seniorComboBox.setOnAction(event -> updateTotal());
        vipUpgradeToggle.setOnAction(event -> updateTotal());

        // Initialize the total calculation
        updateTotal();
    }

    // Method to set the selected date
    public void setSelectedDate(String date) {
        dateLabel.setText(date);
    }

    // Method to set the selected time
    public void setSelectedTime(String time) {
        timeLabel.setText(time);
    }
    // Method to set the movie title in the label
    public void setMovieTitle(String movieTitle) {
        titlemovieprint.setText(movieTitle);  // Update the label with the movie title
    } 
    public void setMovieTitle1(String movieTitle1) {
        titlemovieprint1.setText(movieTitle1);  // Update the label with the movie title
    
        
    } public void setMovieImage(String imagePath) {
        // Use the correct path to the image
        Image image = new Image(getClass().getResource(imagePath).toExternalForm());  // Updated path loading
        imgMovie.setImage(image);  // Set the image to the ImageView
    }   public void setMovieImage1(String imagePath1) {
        // Use the correct path to the image
        Image image = new Image(getClass().getResource(imagePath1).toExternalForm());  // Updated path loading
        imgMovie1.setImage(image);  // Set the image to the ImageView
    }
   
   

    /**
     * Updates the total cost based on the selected quantities and the VIP upgrade.
     */
    private void updateTotal() {
        // Retrieve selected quantities from ComboBoxes
        adultQuantity = adultComboBox.getValue();
        childQuantity = childComboBox.getValue();
        seniorQuantity = seniorComboBox.getValue();

        // Calculate total cost
        double total = (adultQuantity * ADULT_PRICE) +
                       (childQuantity * CHILD_PRICE) +
                       (seniorQuantity * SENIOR_PRICE);

        // Add VIP upgrade cost if selected
        if (vipUpgradeToggle.isSelected()) {
            int totalTickets = adultQuantity + childQuantity + seniorQuantity;
            total += totalTickets * VIP_UPGRADE_PRICE;
        }

        // Update the total label
        totalLabel.setText(String.format("%.2f", total));
    }

    /**
     * Resets all inputs and total label.
     */
    @FXML
    private void onReset() {
        // Reset only the ticket quantities to default (0)
        adultComboBox.setValue(0);
        childComboBox.setValue(0);
        seniorComboBox.setValue(0);

        // Reset the VIP upgrade toggle to its default (deselected)
        vipUpgradeToggle.setSelected(false);

        // The date and time labels will remain unchanged, and the total will auto-update based on ComboBox values
        updateTotal();
    }

    /**
     * Handles checkout button click event.
     */
    @FXML
    private void onCheckout() {
        // Display the final amount in the console
        System.out.println("Checkout clicked.");
        System.out.println("Total Amount: $" + totalLabel.getText());
    }
}
