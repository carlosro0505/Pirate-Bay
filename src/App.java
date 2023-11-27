import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        // gets your screen's dimensions
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        // image logo pirate
        // Image image = new Image(new
        // FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate
        // Bay\\res\\pirateLogo3.png"));
        Image image = new Image("file:res/pirateLogo3.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(260);

        // background image
        Image image2 = new Image("file:res/shipcool.png");
        // Image image2 = new Image(new
        // FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate
        // Bay\\res\\shipcool.png"));
        ImageView backImage = new ImageView(image2);
        backImage.setFitWidth(1540);
        backImage.setFitHeight(785);

        // x icon in searchBar
        Image image7 = new Image("file:res/xIcon.png");
        // Image image7 = new Image(new
        // FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate
        // Bay\\res\\xIcon.png"));
        ImageView x = new ImageView(image7);
        x.setFitWidth(40);
        x.setFitHeight(40);
        Button xBtn = new Button();
        xBtn.setGraphic(x);
        xBtn.getStyleClass().addAll("transparent-background");
        xBtn.setPickOnBounds(true);

        // portal: buttons that move you on the webpage to four functions
        Image image3 = new Image("file:res/magnGlass.png");
        // Image image3 = new Image(new
        // FileInputStream("C:\\Users\\carlo\\OneDrive\\Desktop\\Pirate
        // Bay\\res\\magnGlass.png"));
        ImageView magniGlass = new ImageView(image3);
        magniGlass.setFitWidth(30);
        magniGlass.setFitHeight(37);
        Button magnBtn = new Button();
        magnBtn.setGraphic(magniGlass);
        magnBtn.getStyleClass().addAll("portals", "sBtn");
        // magnBtn.setMinSize(25,25);
        Image image4 = new Image("file:res/recents.png");
        ImageView recents = new ImageView(image4);
        recents.setFitWidth(30);
        recents.setFitHeight(37);
        Button recBtn = new Button();
        recBtn.setGraphic(recents);
        recBtn.getStyleClass().addAll("portals", "recBtn");
        Image image5 = new Image("file:res/categories.png");
        ImageView categories = new ImageView(image5);
        categories.setFitWidth(30);
        categories.setFitHeight(37);
        Button catBtn = new Button();
        catBtn.setGraphic(categories);
        catBtn.getStyleClass().addAll("portals", "browseBtn");
        Image image6 = new Image("file:res/fire.png");
        ImageView fire = new ImageView(image6);
        fire.setFitWidth(30);
        fire.setFitHeight(37);
        Button fireBtn = new Button();
        fireBtn.setGraphic(fire);
        fireBtn.getStyleClass().addAll("portals", "fireBtn");

        // 4 vboxes to add labels under them
        Label magnLabel = new Label(" Search \nTorrents");
        magnLabel.getStyleClass().add("portals");
        Label catLabel = new Label(" Browse \nTorrents");
        catLabel.getStyleClass().add("portals");
        Label recLabel = new Label(" Recent \nTorrents");
        recLabel.getStyleClass().add("portals");
        Label fireLabel = new Label("Trending");
        fireLabel.getStyleClass().add("portals");
        VBox magn = new VBox(magnBtn, magnLabel);
        VBox cat = new VBox(catBtn, catLabel);
        VBox rec = new VBox(recBtn, recLabel);
        VBox fireV = new VBox(fireBtn, fireLabel);
        magn.setSpacing(10);
        magn.setAlignment(Pos.CENTER);
        cat.setSpacing(10);
        cat.setAlignment(Pos.CENTER);
        rec.setSpacing(10);
        rec.setAlignment(Pos.CENTER);
        fireV.setSpacing(10);
        fireV.setAlignment(Pos.CENTER);

        // hbox for button portals
        HBox portal = new HBox(magn, cat, rec, fireV);
        portal.setSpacing(50);
        portal.setAlignment(Pos.CENTER);

        // Top: Search Bar
        TextField searchBar = new TextField();
        Button searchButton = new Button(" Search ");
        HBox h = new HBox(xBtn, searchButton);
        h.setSpacing(0);
        h.setAlignment(Pos.CENTER);
        h.setMaxWidth(400); // Set a maximum width
        // h.setAlignment(Pos.CENTER);
        searchBar.setPromptText("Pirate Search");
        searchBar.setPrefWidth(775);
        searchBar.setPrefHeight(58);
        searchBar.setMaxWidth(775);
        searchBar.getStyleClass().add("search-bar");
        searchButton.getStyleClass().add("search-btn");
        searchButton.setPrefHeight(55);
        searchButton.setPickOnBounds(true);

        // Event listener to toggle visibility based on text in the search bar
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                h.setVisible(false);
            } else {
                h.setVisible(true);
            }
        });

        // event handler for x button
        xBtn.setOnAction(event -> {
            searchBar.setText("");
        });
        searchButton.setOnAction(event -> {
            if (!searchBar.getText().equals("")) {
                try {
                    SceneManager.showSearchScene(searchBar.getText(), "");
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        // event handler for "recent torrents" button
        recBtn.setOnAction(event -> {
            try {
                SceneManager.showSearchScene("", "Recents");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        // event handler for "top 100" button
        fireBtn.setOnAction(event -> {
            try {
                SceneManager.showSearchScene("", "Trending");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        // event handler for "browse torrents" button
        catBtn.setOnAction(event -> {
            try {
                SceneManager.showCategoriesScene();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        // event handler for "Search Torrents" buttons
        magnBtn.setOnAction(event -> {
            try {
                // placeholder
                SceneManager.showCategoriesScene();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        // Event handler for Enter key press in the text field
        searchBar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // Simulate a button click when Enter key is pressed
                searchButton.fire();
            }
        });

        // make the buttons pop when hovering over them
        ScaleTransitionHelper.createScaleTransition(magnBtn, magnLabel);
        ScaleTransitionHelper.createScaleTransition(catBtn, catLabel);
        ScaleTransitionHelper.createScaleTransition(fireBtn, fireLabel);
        ScaleTransitionHelper.createScaleTransition(recBtn, recLabel);

        // Initial state: hide the search button
        h.setVisible(false);

        // StackPane to stack the searchButton on top of the searchBar
        StackPane searchStack = new StackPane(searchBar, h);
        StackPane.setAlignment(h, Pos.CENTER_RIGHT);
        StackPane.setMargin(h, new Insets(0, 0, 0, 622));
        searchStack.setMaxWidth(775); // Set a maximum width

        VBox searchAndBtns = new VBox(portal, searchStack);
        searchAndBtns.setSpacing(40.0);
        searchAndBtns.setAlignment(Pos.CENTER);

        AnchorPane root = new AnchorPane();

        AnchorPane.setTopAnchor(searchAndBtns, 360.0);
        AnchorPane.setLeftAnchor(searchAndBtns, 0.0);
        AnchorPane.setRightAnchor(searchAndBtns, 0.0);

        // to center the image no matter what screen

        // Set constraints for the ImageView to be horizontally centered
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            // calculates by getting width of the anchorPane then the w of the imageView,
            // subtracts, divs by 2
            double leftAnchor = ((newVal.doubleValue() - imageView.getBoundsInParent().getWidth()) / 2);
            AnchorPane.setLeftAnchor(imageView, leftAnchor);
            AnchorPane.setRightAnchor(imageView, leftAnchor);
        });

        root.getChildren().addAll(backImage, imageView, searchAndBtns);
        Scene scene = new Scene(root, 1540, 785);
        // Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
        SceneManager.setPrimaryStage(primaryStage);
        primaryStage.setOnShown(event -> searchBar.requestFocus());

        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Stack Search Button on Top");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
