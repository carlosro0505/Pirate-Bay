import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Top: Search Bar
        TextField searchBar = new TextField();
        Button searchButton = new Button("Search");
        HBox h = new HBox(searchBar, searchButton);
        searchBar.setPromptText("Pirate Search");
        searchBar.setPrefWidth(550);
        searchBar.setPrefHeight(38);
        searchButton.setPrefHeight(38);
        h.setAlignment(Pos.CENTER);
        searchBar.getStyleClass().add("search-bar");
        searchButton.getStyleClass().add("search-btn");
        //searchBar.getStyleClass().add("search-bar-text");

        AnchorPane root = new AnchorPane();
        root.getStyleClass().add("anchor-pane");

        
        // Wrap the HBox with AnchorPane
        AnchorPane.setTopAnchor(h, 400.0); // Adjust the offset as needed
        AnchorPane.setLeftAnchor(h, 490.0);
    
        // Add the AnchorPane to the center region of the BorderPane
        root.getChildren().addAll(h);

        Scene scene = new Scene(root, 1540, 785);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Center HBox with Offset");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


        

    