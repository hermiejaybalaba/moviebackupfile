package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class HomeController implements Initializable {

    private ArrayList<String> words = new ArrayList<>(Arrays.asList("Avengers", "Popeye", "Moana 2"));

    @FXML
    private TextField searchBar;

    @FXML
    private ListView<String> listView;

    @FXML
    void search(ActionEvent event) {
        // Clear the current items in the ListView
        listView.getItems().clear();

        // If the search bar is empty, display all items
        if (searchBar.getText().trim().isEmpty()) {
            listView.getItems().addAll(words);
            listView.setVisible(true);
        } else {
            List<String> result = searchList(searchBar.getText(), words);

            if (!result.isEmpty()) {
                listView.getItems().addAll(result);
                listView.setVisible(true);
            } else {
                listView.setVisible(false);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setVisible(false);

        // Add an event listener for clicks on ListView items
        listView.setOnMouseClicked(event -> {
            // Get the selected item
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if ("Avengers".equals(selectedItem)) {
                // Navigate to AvengersMovie.fxml if "Avengers"a is clicked
                try {
                    navigateToMovie(event, "AvengersMovie.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if ("Popeye".equals(selectedItem)) {
                // Navigate to PopeyeMovie.fxml if "Popeye" is clicked
                try {
                    navigateToMovie(event, "PopeyeMovie.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Handle the log out action
    @FXML
    void handleLogout1(ActionEvent event) throws IOException {
        // Load the login.fxml scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent loginRoot = loader.load();

        // Get the current stage and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loginRoot);
        stage.setScene(scene);
        stage.show();
    }

    private void navigateToMovie(javafx.event.Event event, String fxmlFile) throws IOException {
        // Load the specified movie.fxml file
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent movieRoot = loader.load();

        // Get the current stage and set the new scene
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(movieRoot);
        stage.setScene(scene);
        stage.show();
    }

    private List<String> searchList(String searchWords, List<String> listOfStrings) {
        List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));
        return listOfStrings.stream().filter(input ->
                searchWordsArray.stream().allMatch(word ->
                        input.toLowerCase().contains(word.toLowerCase())
                )
        ).collect(Collectors.toList());
    }
}
