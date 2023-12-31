import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

//this class should be called whenever the search bar and buttons needs to be used 
//returns vbox object

public class SearchBarAndButtonsHelper {

    private String filterString = "";

    public VBox createBar(String searchString, String filterString) throws FileNotFoundException {
        this.filterString = filterString;

        // logo image
        Image image = new Image("file:res/pirateLogo3.png");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(150);

        // Top: Search Bar
        // made the height and other elements scale by 0.75 compared to App.java
        TextField searchBar = new TextField();
        searchBar.setText(searchString);
        Button searchButton = new Button(" Search ");
        searchBar.setPromptText("Pirate-Search");
        // searchBar.setPrefWidth(775);
        searchBar.setPrefWidth(1530);
        searchBar.setPrefHeight(43.5);
        // searchBar.setMaxWidth(775);
        searchBar.getStyleClass().add("search-bar-minimized");
        searchButton.getStyleClass().add("search-btn");
        searchButton.setPrefHeight(40);
        searchButton.setPickOnBounds(true);
        // x icon in searchBar
        Image image7 = new Image("file:res/xIcon.png");
        ImageView x = new ImageView(image7);
        x.setFitWidth(40);
        x.setFitHeight(40);
        Button xBtn = new Button();
        xBtn.setGraphic(x);
        xBtn.getStyleClass().addAll("transparent-background");
        xBtn.setPickOnBounds(true);

        HBox h = new HBox(xBtn, searchButton);
        h.setSpacing(0);
        // h.setAlignment(Pos.BASELINE_RIGHT);
        h.setMaxWidth(400);
        h.setAlignment(Pos.CENTER);
        StackPane searchStack = new StackPane(searchBar, h);
        StackPane.setAlignment(h, Pos.CENTER_RIGHT);

        // searchStack.getStyleClass().add("anchor-pane");
        StackPane.setMargin(h, new Insets(0, -(122.88), 0, 0));
        searchStack.setMaxWidth(1505.28); // Set a maximum width
        // portal: buttons that move you on the webpage to four functions
        Image image3 = new Image("file:res/magnGlass.png");
        ImageView magniGlass = new ImageView(image3);
        magniGlass.setFitWidth(30);
        magniGlass.setFitHeight(37);
        Button magnBtn = new Button();
        magnBtn.setGraphic(magniGlass);
        magnBtn.getStyleClass().addAll("portals", "sBtn");
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
        Label fireLabel = new Label("Trending \n d");
        fireLabel.getStyleClass().add("portals");
        VBox magn = new VBox(magnBtn, magnLabel);
        VBox cat = new VBox(catBtn, catLabel);
        VBox rec = new VBox(recBtn, recLabel);
        VBox fireV = new VBox(fireBtn, fireLabel);
        magn.setSpacing(8.5);
        magn.setAlignment(Pos.CENTER);
        cat.setSpacing(8.5);
        cat.setAlignment(Pos.CENTER);
        rec.setSpacing(8.5);
        rec.setAlignment(Pos.CENTER);
        fireV.setSpacing(8.5);
        fireV.setAlignment(Pos.CENTER);

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
                    SceneManager.showSearchScene(searchBar.getText(), this.filterString);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        // event handler for "recent torrents" button
        recBtn.setOnAction(event -> {
            try {
                SceneManager.showSearchScene("", "    Recents");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        // event handler for "top 100" button
        fireBtn.setOnAction(event -> {
            try {
                SceneManager.showSearchScene("", "    Trending");
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
                SceneManager.showMainScene();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        // event handler for Enter key press in the text field
        searchBar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // simulate a button click when Enter key is pressed
                searchButton.fire();
            }
        });

        // make the buttons pop when hovering over them
        ScaleTransitionHelper.createScaleTransition(magnBtn, magnLabel);
        ScaleTransitionHelper.createScaleTransition(catBtn, catLabel);
        ScaleTransitionHelper.createScaleTransition(fireBtn, fireLabel);
        ScaleTransitionHelper.createScaleTransition(recBtn, recLabel);

        // initial state: hide the search button
        if (searchBar.getText().equals("")) {
            h.setVisible(false);
        }

        // hbox for button portals
        HBox portal = new HBox(magn, cat, rec, fireV);
        portal.setSpacing(51.408);
        HBox logoAndPortal = new HBox(imageView, portal);
        logoAndPortal.setSpacing(61.44);
        logoAndPortal.setPadding(new Insets(0, 15, 0, 15)); // Set padding for the VBox

        portal.setAlignment(Pos.CENTER);
        logoAndPortal.setAlignment(Pos.CENTER_LEFT);

        VBox searchAndBtns = new VBox(logoAndPortal, searchStack);
        searchAndBtns.setSpacing(0.0);
        searchAndBtns.setAlignment(Pos.CENTER);

        return searchAndBtns;
    }

    public void setFilterString(String filterString) {
        this.filterString = filterString;
    }
}
