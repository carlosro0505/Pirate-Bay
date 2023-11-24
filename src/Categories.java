import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Categories extends Application{
    private Stage primaryStage;

    public Categories(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage stage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
      
        //x icon in searchBar
        Image image7 = new Image("file:res/xIcon.png");
        ImageView x = new ImageView(image7);
        x.setFitWidth(40);
        x.setFitHeight(40);
        Button xBtn = new Button();
        xBtn.setGraphic(x);
        xBtn.getStyleClass().addAll("transparent-background");
        xBtn.setPickOnBounds(true);

        //portal: buttons that move you on the webpage to four functions 
        Image image3 = new Image("file:res/magnGlass.png");
        ImageView magniGlass = new ImageView(image3);
        magniGlass.setFitWidth(30);
        magniGlass.setFitHeight(37);
        Button magnBtn = new Button();
        magnBtn.setGraphic(magniGlass);
        magnBtn.getStyleClass().addAll("portals", "sBtn");
        //magnBtn.setMinSize(25,25);
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

        //4 vboxes to add labels under them
        Label magnLabel = new Label(" Search \nTorrents"); magnLabel.getStyleClass().add("portals");
        Label catLabel = new Label(" Browse \nTorrents"); catLabel.getStyleClass().add("portals");
        Label recLabel= new Label(" Recent \nTorrents"); recLabel.getStyleClass().add("portals");
        Label fireLabel = new Label("Top \n100"); fireLabel.getStyleClass().add("portals");
        VBox magn = new VBox(magnBtn, magnLabel);
        VBox cat = new VBox(catBtn, catLabel);
        VBox rec = new VBox(recBtn, recLabel);
        VBox fireV = new VBox(fireBtn, fireLabel);
        magn.setSpacing(10); magn.setAlignment(Pos.CENTER);
        cat.setSpacing(10); cat.setAlignment(Pos.CENTER);
        rec.setSpacing(10); rec.setAlignment(Pos.CENTER);
        fireV.setSpacing(10); fireV.setAlignment(Pos.CENTER);

         //hbox for button portals
        HBox portal = new HBox(magn, cat, rec, fireV);
        portal.setSpacing(50);
        portal.setAlignment(Pos.CENTER);
        
        // Top: Search Bar
        TextField searchBar = new TextField();
        Button searchButton = new Button(" Pirate Search ");
        HBox h = new HBox(xBtn, searchButton); 
        h.setSpacing(0);
        h.setAlignment(Pos.CENTER);
        h.setMaxWidth(400); // Set a maximum width
        searchBar.setPromptText("Pirate Search");
        searchBar.setPrefWidth(775);
        searchBar.setPrefHeight(58);
        searchBar.setMaxWidth(775);
        searchBar.getStyleClass().add("search-bar");
        searchButton.getStyleClass().add("search-btn");
        searchButton.setPrefHeight(55);
        searchButton.setPickOnBounds(true);

        // StackPane to stack the searchButton on top of the searchBar
        StackPane searchStack = new StackPane(searchBar, h);
        StackPane.setAlignment(h, Pos.CENTER_RIGHT);
        StackPane.setMargin(h, new Insets(5, 0, 0, 0)); // Add space below the search bar
        searchStack.setMaxWidth(1275); // Set a maximum width

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty()) {
                h.setVisible(true);
            } else {
                h.setVisible(true);
            }
        });

        xBtn.setOnAction(event -> searchBar.setText(""));

        searchButton.setOnAction(event -> {
            if (!searchBar.getText().equals("")) {
                try {
                    SceneManager.showSearchScene();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        // Adjusted position for the search stack
        AnchorPane.setTopAnchor(searchStack, 145.0);
        AnchorPane.setLeftAnchor(searchStack, 25.0);

        //event handler for "Search Torrents" buttons
        magnBtn.setOnAction(event -> {
            App app = new App();
            try {
                app.start(new Stage());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            primaryStage.close();
        }); 

        //event handler for "recent torrents" button
        recBtn.setOnAction(event -> {
            try {
                    SceneManager.showRecentsScene();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        });
        //event handler for "top 100" button
         fireBtn.setOnAction(event -> {
            try {
                    SceneManager.showTrendingScene();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        });

        //make the buttons pop when hovering over them
        ScaleTransitionHelper.createScaleTransition(magnBtn, magnLabel);
        ScaleTransitionHelper.createScaleTransition(fireBtn, fireLabel);
        ScaleTransitionHelper.createScaleTransition(recBtn, recLabel);
        


        
    // Create table columns
    TableColumn<CategoryRow, Hyperlink> col1 = new TableColumn<>("Browse Torrents");
    col1.setCellValueFactory(cellData -> {
        Hyperlink link = new Hyperlink(cellData.getValue().getCategory1());
        link.setOnAction(event -> handleLinkClick(cellData.getValue().getCategory1()));
        return new javafx.beans.property.ReadOnlyObjectWrapper<>(link);
    });

    TableColumn<CategoryRow, Hyperlink> col2 = new TableColumn<>("     ");
    col2.setCellValueFactory(cellData -> {
        Hyperlink link = new Hyperlink(cellData.getValue().getCategory2());
        link.setOnAction(event -> handleLinkClick(cellData.getValue().getCategory2()));
        return new javafx.beans.property.ReadOnlyObjectWrapper<>(link);
    });

    // Create data for the table
    ObservableList<CategoryRow> data = FXCollections.observableArrayList(
            new CategoryRow("Audio:", "Games:"),
            new CategoryRow("Music, Audio books, Sound clips, FLAC, Other", "PC, Mac, PSx, PlayStation, XBOX, Wii, IOS, Android, Other"),
            new CategoryRow("Video:", "Applications:"),
            new CategoryRow("Movies, DVDR, Music videos, Movie clips, TV shows, HD, 3D, Other", "Windows, Mac, UNIX, IOS, Android, Other")            
    );

    // Create the table
    TableView<CategoryRow> table = new TableView<>(data);
    table.getColumns().addAll(col1, col2);
    table.setPrefWidth(0.82 * bounds.getWidth()); // Set the preferred width
    table.getStyleClass().add("table-view");
    table.setPrefHeight(0.77 * bounds.getHeight());
    // gets rid of the extra space in the table horizontally (extra column)
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    table.getStyleClass().add("no-header"); // Add a custom style to remove header borders

    // Adjusted position for the table view
    AnchorPane.setTopAnchor(table, 250.0);
    AnchorPane.setLeftAnchor(table, 100.0);



       // AnchorPane for the left-top corner
       AnchorPane leftTop = new AnchorPane(portal);
       leftTop.getStyleClass().add("left-top-corner");
       AnchorPane.setTopAnchor(portal, 15.0);
       AnchorPane.setLeftAnchor(portal, 65.0);

        // Create a horizontal line (Separator)
        Separator separator = new Separator();
        separator.setPrefWidth(2600); // Set the preferred width of the line
        separator.getStyleClass().add("separator");
        // Horizontal lane
        HBox horizontalLane = new HBox(separator);
        horizontalLane.getStyleClass().add("horizontal-lane");
        horizontalLane.setMinHeight(12); // Set your preferred height
        // Adjusted position for the table view

        VBox centerContent = new VBox(horizontalLane, searchStack);
        centerContent.setAlignment(Pos.CENTER);
            
       // AnchorPane for the entire content
       AnchorPane root = new AnchorPane(leftTop, centerContent, searchStack, table);
       root.getStyleClass().add("main-pane");

       Scene scene = new Scene(root, bounds.getWidth(), bounds.getHeight());
       scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
       primaryStage.setScene(scene);
       primaryStage.setTitle("DIMEEEEELOOOOOOOOOOOOOO");
       primaryStage.show();
    }

    private void handleLinkClick(String category) {
        // Handle the link click event (e.g., open a new scene or perform some action)
        System.out.println("Link clicked: " + category);
    }

    public void closePrimaryStage() {
        primaryStage.close();
    }
}
